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
            positionNameEmployee = employee.getPosition().getSubAreaOrDivision();
        }

//        if (positionCode != null) {
//        	approvedHierarchicalPojo = hierarchicalTreeService.findByAreaDivisionAndSubAreaDivisionAndPositionCode(areaEmployee, subAreaEmployee, positionCode);
//            
//        } else 
        	
        	if (employee != null && employee.getId() != null){
        	
            HierarchicalTreePojo employeeHierarchicalPosition = hierarchicalTreeService.findByPositioCode(employee.getPosition().getCode());
            Integer positionEmployee = employeeHierarchicalPosition.getPositionNumber();
            
            List<HierarchicalTreePojo> approvedHierarchical = this.approvedHierarchical(areaEmployee, subAreaEmployee, positionEmployee);

            if (approvedHierarchical != null && approvedHierarchical.size() > 0) {
            	
                AssignedEmployeeApprove = this.getEmployeeApprove(approvedHierarchical);
            }
            
           
            if(AssignedEmployeeApprove == null){
            	
                List<HierarchicalTreePojo> approvedHierarchicalUp = this.approvedHierarchical(areaEmployee, "NONE", positionEmployee);
                
                if (approvedHierarchicalUp != null && approvedHierarchicalUp.size() > 0) {
                	
                    AssignedEmployeeApprove = this.getEmployeeApprove(approvedHierarchicalUp);
                    
                } 
                
                if(AssignedEmployeeApprove == null){
                	
                    AssignedEmployeeApprove = this.getApprovedHierarchyOfAreaSuper( employeeHierarchicalPosition.getHierarchyOfAreas(), positionEmployee);
                    
                }
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



    private List<HierarchicalTreePojo> approvedHierarchical(String areaEmployee, String subAreaEmployee, Integer positionEmployee) {
        List<HierarchicalTreePojo> approvedHierarchical = null;
        try {
           
            approvedHierarchical = approvedHierarchicalUp( areaEmployee,  subAreaEmployee,  positionEmployee);
            return approvedHierarchical;
       
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            return null;
        }
    }
    
    
    private  List<HierarchicalTreePojo>  approvedHierarchicalUp(String areaEmployee, String subAreaEmployee, Integer positionEmployee){
    	
    	Integer countItrances = 0;
    	
    	Integer iteranceMax = Integer.valueOf(hierarchicalTreeService.findMaxPositionNumberAreaDivisionAndPositioCode(areaEmployee).get(0).toString());
    	
    	
    	 List<HierarchicalTreePojo> approvedHierarchical = null;
    	 Integer positionAdd = positionEmployee + 1;
    	 
        
    	 approvedHierarchical =
                 hierarchicalTreeService.findByAreaDivisionAndSubAreaDivisionAndPositionNumber(
                         areaEmployee,
                         subAreaEmployee,
                         positionAdd
                 );
    	
    	 if(approvedHierarchical == null && positionAdd <= iteranceMax) {
    		 countItrances ++;
    		 approvedHierarchicalUp( areaEmployee,  subAreaEmployee,  positionAdd);
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
