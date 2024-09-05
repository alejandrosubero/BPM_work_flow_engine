package com.bpm.employee.componet;

import com.bpm.employee.model.AssignedModel;
import com.bpm.employee.pojo.EmpleadoPojo;
import com.bpm.employee.pojo.EmployeeReliefPojo;
import com.bpm.employee.pojo.HierarchicalTreePojo;
import com.bpm.employee.interfaces.IAssigned;
import com.bpm.employee.service.EmpleadoService;
import com.bpm.employee.service.EmployeeReliefService;
import com.bpm.employee.service.HierarchicalTreeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeManager implements IAssigned {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private HierarchicalTreeService hierarchicalTreeService;

    @Autowired
    private EmployeeReliefService employeeReliefService;

    protected static final Log logger = LogFactory.getLog(EmployeeManager.class);

    
    public AssignedModel findEmployee(String employeeNumber){
        EmpleadoPojo employee = empleadoService.findByNumeroEmpleado(employeeNumber);
        return generateAssigned(employee);
    }

    public AssignedModel findEmployeeByUserName(String useName){
        EmpleadoPojo employee = empleadoService.findEmployeeByUserName(useName);
        return generateAssigned(employee);
    }

    
    public AssignedModel findApprover(String employeeNumber) {
    	
          EmpleadoPojo employee = null;
          String positionCode = null;
          
          if (employeeNumber != null) {
          	employee = empleadoService.findByNumeroEmpleado(employeeNumber);        	
          }

          if(employee != null && employee.getId() != null){
        	  positionCode = employee.getPosition().getCode();
          }
    	return approver( employeeNumber,  positionCode,  employee);
    }
    
    
    public AssignedModel findApprover(String employeeNumber, String positionCode) {
    	
          EmpleadoPojo employee = null;

          if (employeeNumber != null) {
          	employee = empleadoService.findByNumeroEmpleado(employeeNumber);        	
          }

          return approver( employeeNumber,  positionCode,  employee);
    }
    
    
    
    
    public AssignedModel approver(String employeeNumber, String positionCode, EmpleadoPojo employee) {

        AssignedModel AssignedEmployeeApprove = null;
        String areaEmployee = null;
        String subAreaEmployee = null;
        String positionNameEmployee = null;
        HierarchicalTreePojo approvedHierarchicalPojo = null;

        if(employee != null && employee.getId() != null){
            areaEmployee = employee.getPosition().getAreaOrDivision();
            subAreaEmployee = employee.getPosition().getSubAreaOrDivision();
            positionNameEmployee = employee.getPosition().getName();
        }

//        if (positionCode != null) {
//        	approvedHierarchicalPojo = hierarchicalTreeService.findByAreaDivisionAndSubAreaDivisionAndPositionCode(areaEmployee, subAreaEmployee, positionCode);
//            
//        } else 
        	
        	if (employee != null && employee.getId() != null){
        	
            HierarchicalTreePojo employeeHierarchicalPosition = hierarchicalTreeService.findByPositioCode(employee.getPosition().getCode());
            Integer positionEmployeeNumber = employeeHierarchicalPosition.getPositionNumber();
            
            List<HierarchicalTreePojo> approvedHierarchical = this.approvedHierarchical(areaEmployee, subAreaEmployee, positionEmployeeNumber);

            if (approvedHierarchical != null && approvedHierarchical.size() > 0) {
            	
                AssignedEmployeeApprove = this.getEmployeeApprove(approvedHierarchical);
            }
            
           
            if(AssignedEmployeeApprove == null){
            	
                List<HierarchicalTreePojo> approvedHierarchicalUp = this.approvedHierarchical(areaEmployee, "NONE", positionEmployeeNumber);
                
                if (approvedHierarchicalUp != null && approvedHierarchicalUp.size() > 0) {
                	
                    AssignedEmployeeApprove = this.getEmployeeApprove(approvedHierarchicalUp);
                    
                } 
                
                
                //TODO: THE LOGIC IN FAIL HE SEARCH GO UP BUT IN THIS SYSTEM GO DOWN......
                
//                if(AssignedEmployeeApprove == null){
//                	
//                    AssignedEmployeeApprove = this.getApprovedHierarchyOfAreaSuper( employeeHierarchicalPosition.getHierarchyOfAreas(), positionEmployeeNumber);
//                    
//                }
            }
        }
        return AssignedEmployeeApprove;
    }

    
    
    
    
    

    private AssignedModel getApprovedHierarchyOfAreaSuper( Integer hierarchyOfAreas, Integer positionEmployee) {
        AssignedModel assignedEmployeeApprove = null;
        HierarchicalTreePojo approvedArea = null;
        List<HierarchicalTreePojo> approvedHierarchyOfArea = null;
        Integer newApproveHierarchyOfAreas = hierarchyOfAreas+1;

        try {
            List<HierarchicalTreePojo> hierarchicalHierarchyOfArea = hierarchicalTreeService.findByHierarchyOfAreas(newApproveHierarchyOfAreas);

            if(hierarchicalHierarchyOfArea != null && hierarchicalHierarchyOfArea.size() > 0){
                if(hierarchicalHierarchyOfArea.contains(newApproveHierarchyOfAreas)){
                    approvedArea = hierarchicalHierarchyOfArea.get(hierarchicalHierarchyOfArea.indexOf(newApproveHierarchyOfAreas));
                    approvedHierarchyOfArea = this.approvedHierarchical(approvedArea.getAreaOrDivision(), approvedArea.getSubAreaOrDivision(), positionEmployee);
                }
                if (approvedHierarchyOfArea != null && approvedHierarchyOfArea.size() > 0)
                    assignedEmployeeApprove = this.getEmployeeApprove(approvedHierarchyOfArea);

            } else {
                this.getApprovedHierarchyOfAreaSuper( newApproveHierarchyOfAreas, positionEmployee);
            }
        }catch (Exception e ){
            logger.error(e);
            e.printStackTrace();
            return assignedEmployeeApprove;
        }
        return assignedEmployeeApprove;
    }



//    private List<HierarchicalTreePojo> approvedHierarchical(String areaEmployee, String subAreaEmployee, Integer positionEmployeeNumber) {
//       
//        try {
//
//            return approvedHierarchicalUp( areaEmployee,  subAreaEmployee,  positionEmployeeNumber);
//       
//        } catch (Exception e) {
//            logger.error(e);
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    
//    private  List<HierarchicalTreePojo>  approvedHierarchical(String areaEmployee, String subAreaEmployee, Integer positionEmployeeNumber){
//    	
//    	List<HierarchicalTreePojo> approvedHierarchical = new ArrayList<>();
//    	Integer countItrances = 0;
//    	Integer positionAdd = positionEmployeeNumber + 1;
//    	
//    	Integer iteranceMax = Integer.valueOf(hierarchicalTreeService.findMaxPositionNumberAreaDivisionAndPositioCode(areaEmployee).get(0).toString());
//    	
//    	         
//    	 approvedHierarchical.addAll(
//                 hierarchicalTreeService.findByAreaDivisionAndSubAreaDivisionAndPositionNumber(
//                         areaEmployee,
//                         subAreaEmployee,
//                         positionAdd
//                 ));
//    	 
//    	 Boolean verificEmployee = verificEmployee(approvedHierarchical);
//    	 
//    	 if(verificEmployee) {
//    		 return approvedHierarchical;
//    	 }
//    	 
//    	
//    	 if(positionAdd <= iteranceMax && !verificEmployee) {
//    		 countItrances ++;
//    		 approvedHierarchical( areaEmployee,  subAreaEmployee,  positionAdd);
//    	 }
//    	
//    	 return approvedHierarchical;
//    }

    
    
    private Boolean verificEmployee (List<HierarchicalTreePojo> approvedHierarchical) {
    	
    	 List<String> empleadosNumbers = new ArrayList<>();
    	 
        approvedHierarchical.stream().forEach(hierarchicalTreePojo -> {
            if (hierarchicalTreePojo.isActivo()) {
                empleadosNumbers.addAll(empleadoService.findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(hierarchicalTreePojo.getAreaOrDivision(), hierarchicalTreePojo.getSubAreaOrDivision(), hierarchicalTreePojo.getPositioCode()));
            }
        });
        
        if(!empleadosNumbers.isEmpty()) {
        	return true;
        }
        return false;
    }
    
   
    
    private List<HierarchicalTreePojo> approvedHierarchical(String areaEmployee, String subAreaEmployee, Integer positionEmployeeNumber) {
       
    	List<HierarchicalTreePojo> approvedHierarchical = new ArrayList<>();
      
        Integer positionAdd = positionEmployeeNumber + 1;

        Integer iteranceMax = Integer.valueOf(hierarchicalTreeService.findMaxPositionNumberAreaDivisionAndPositioCode(areaEmployee).get(0).toString());

        approvedHierarchical.addAll(
                hierarchicalTreeService.findByAreaDivisionAndSubAreaDivisionAndPositionNumber(
                        areaEmployee,
                        subAreaEmployee,
                        positionAdd
                ));

        Boolean verificEmployee = verificEmployee(approvedHierarchical);

        if (verificEmployee) {
            return approvedHierarchical;
        }

        // Check position and call recursively only if needed
        if (positionAdd <= iteranceMax) {
            approvedHierarchical = new ArrayList<>();
            
            // Get the result from the recursive call
            List<HierarchicalTreePojo> subList = approvedHierarchical(areaEmployee, subAreaEmployee, positionAdd);
            
            // Append the sub-list retrieved from recursion
            approvedHierarchical.addAll(subList);
            
            // Filter the list to keep only elements where verificEmployee is true
            
            approvedHierarchical = approvedHierarchical.stream()
                    .filter(e -> verificEmployee(List.of(e)))
                    .collect(Collectors.toList());
        }

        return approvedHierarchical;
    }
    
    
    

    private AssignedModel getEmployeeApprove(List<HierarchicalTreePojo> approvedHierarchical) {

        List<String> empleadosNumbers = new ArrayList<>();
        List<EmpleadoPojo> employeeApprove = new ArrayList<>();
        List<EmpleadoPojo> employees = new ArrayList<>();

        approvedHierarchical.stream().forEach(hierarchicalTreePojo -> {
            if (hierarchicalTreePojo.isActivo()) {
                empleadosNumbers.addAll(empleadoService.findNumeroEmpleadoByAreaDivisionAndSubAreaDivisionAndPositioCode(hierarchicalTreePojo.getAreaOrDivision(), hierarchicalTreePojo.getSubAreaOrDivision(), hierarchicalTreePojo.getPositioCode()));
            }
        });

        if (empleadosNumbers.size() > 0) {
            empleadosNumbers.stream().forEach(numberOfEmployee -> employees.add(empleadoService.findByNumeroEmpleado(numberOfEmployee)));
        }

        if (!employees.isEmpty()) {
        	
//        	for( EmpleadoPojo empleadoPojo : employees) {
            employees.stream().forEach(empleadoPojo -> {
        		
                if (empleadoPojo.isActivo() && !empleadoPojo.isVacation() && !empleadoPojo.isMedicalRest() && !empleadoPojo.isRelief()) {
                    employeeApprove.add(empleadoPojo);
                }
            }
        	);

            if (employeeApprove.isEmpty()) {
                employees.stream().forEach(empleadoPojo -> {
                    if (empleadoPojo.isRelief()) {
                        EmployeeReliefPojo relief = employeeReliefService.findByEmployeeRelief(empleadoPojo.getNumeroEmpleado());
                        employeeApprove.add(empleadoService.findByNumeroEmpleado(relief.getEmployeeToBeRelieved()));
                    }
                });
            }
        }

        if (employeeApprove.size() > 0) {
            logger.info("the assigned is generate");
            return generateAssigned(employeeApprove.get(0));
        }
        logger.error("the assigned is not generate...");
        return null;
    }


//    private AssignedModel generateAssigned(EmpleadoPojo empleadoPojo) {
//        try {
//            RoleModel role = new RoleModel(empleadoPojo.getPosition().getDescription(), empleadoPojo.getPosition().getName(), empleadoPojo.getPosition().getCode());
//            AssignedModel assigned = new AssignedModel(empleadoPojo.getName(), empleadoPojo.getNumeroEmpleado(), role);
//            logger.info("the assigned is assigned... ");
//            return assigned;
//        } catch (Exception e) {
//            logger.error(e);
//            e.printStackTrace();
//            return null;
//        }
//
//    }


}
