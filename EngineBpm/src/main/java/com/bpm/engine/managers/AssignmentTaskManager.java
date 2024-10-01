package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;

@Component
public class AssignmentTaskManager {
	
	private BpmAssignedService bpmAssignedService;
	private AssignedService assignedService;
	private ConectBpmToEmployeeService conectBpmToEmployeeService;
	
	
	@Autowired
	public AssignmentTaskManager(BpmAssignedService bpmAssignedService, AssignedService assignedService, ConectBpmToEmployeeService conectBpmToEmployeeService) {
	
		this.bpmAssignedService = bpmAssignedService;
		this.assignedService = assignedService;
		this.conectBpmToEmployeeService = conectBpmToEmployeeService;
	}
	
	//TODO: IN THE TASK (systemRequest) EXIST ROLE FOR THIS TASK HOW USE? 

	
	/***
	 * 
	 * @param taskModelCode
	 * @param systemRequest
	 * @param instanceProccesId
	 * @return
	 */
	public List<BpmAssignedModel> getAssigned(String taskModelCode, SystemRequest systemRequest, Long instanceProccesId, Long idProcess) {
		List<BpmAssignedModel> taskAssignedModelList = new ArrayList<>();
		try {			

			if (systemRequest.checkAssigned(taskModelCode)) {
				// this part is for users direct assigned router = 0		
				taskAssignedModelList.addAll(this.getUserDirectAssigned(systemRequest.getAssigned().get(taskModelCode),  taskModelCode, instanceProccesId, idProcess));
			} else {
					// this part is for user create the instance Process router = 1
				taskAssignedModelList.addAll(getUseTheUserWasCreateInstanceProcess(systemRequest.getCodeEmployee(), taskModelCode,instanceProccesId, idProcess));
			}
			
		} catch (Exception e){
			e.printStackTrace();
			return taskAssignedModelList;
		}
		return taskAssignedModelList;
	}
	
	
	public BpmAssignedModel getOneUserDirectAssigned(String codeEmployee, Long instanceProccesId, Long idProcess){
		
		return this.getOneAssigned( null, codeEmployee,  instanceProccesId,idProcess,0);
	}
	
	/***
	 * 
	 * @param systemRequestAssigned
	 * @param taskCode
	 * @param instanceProccesId
	 * @return the User's Direct Assigned (this is only for a specific task in an instance..   
	 */
	private List<BpmAssignedModel> getUserDirectAssigned(List<String> systemRequestAssigned, String taskCode, Long instanceProccesId, Long idProcess){
		List<BpmAssignedModel> list = new ArrayList<>();
		systemRequestAssigned.forEach(codeEmployee ->
		list.addAll(this.getTaskAssigned(taskCode, codeEmployee, instanceProccesId,idProcess, 0)));
		return list;
	}
	
	/***
	 * 
	 * @param codeEmployee
	 * @param taskCode
	 * @param instanceProccesId
	 * @return the assigned user for the user was create the request of instance.
	 */
	private List<BpmAssignedModel> getUseTheUserWasCreateInstanceProcess(String codeEmployee, String taskCode, Long instanceProccesId, Long idProcess){
		return this.getTaskAssigned(taskCode, codeEmployee, instanceProccesId, idProcess,1);
		//TODO: IN THE TASK (systemRequest) EXIST ROLE FOR THIS TASK HOW USE? 
	}
	
	
	public List<BpmAssignedModel> getTaskAssigned(String taskCode, String codeEmployee, Long instanceProccesId, Long idProcess, Integer router) {
		List<BpmAssignedModel> assignes = new ArrayList<>();
		
		if( taskCode != null &&  codeEmployee!= null &&  instanceProccesId != null && router != null) {

			try {
			
				List<BpmAssignedModel> bpmAssigned = this.getAssignedFromBpmAssigned(taskCode);
	
				if (bpmAssigned != null && bpmAssigned.size() > 0) {
					assignes.addAll(bpmAssigned);
				} 
	
				if ((bpmAssigned == null || bpmAssigned.isEmpty())) {
					assignes.addAll(this.getAssigneds(taskCode, codeEmployee, instanceProccesId, idProcess, router));
				}		
					
		} catch (Exception e) {
			e.printStackTrace();
			return assignes;
		}
	}		
		return assignes;
	}

	
	
	/***
	 * 
	 * @param taskCode
	 * @param codeEmployee
	 * @param instanceProccesId
	 * @param router
	 * @service call to employeeServise need wait response and check in internal assigned
	 * @return
	 */
	public List<BpmAssignedModel> getAssigneds(String taskCode, String codeEmployee, Long instanceProccesId,Long idProcess,Integer router) {
		
		List<BpmAssignedModel> assignesFromRouter = new ArrayList<>();
		AssignedModel assigned = null;
		AssignedModel employee = getAssignedModel(codeEmployee);
		
		if (router == 1) {
				
			if (employee != null && employee.getemployeeRole().getCodeRole() != null) {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee, employee.getemployeeRole().getCodeRole());
			} else {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee);
			}
			
			if(assigned != null) {
				assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, null,idProcess));
			}
		}
		
		if (router == 0) {
			
			if (employee == null) {
				assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
			} else {
				assigned = employee;
			}
			
			if(assigned != null) {
				assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, instanceProccesId,idProcess));  
			}
		}
		return assignesFromRouter;
	}
	
	

	
	
	
/***
 * 
 * @param taskCode
 * @return BpmAssignedModel List from BpmAssigned for taskCode were InstanciaProccesId is null? in data base.
 */
	public  List<BpmAssignedModel> getAssignedFromBpmAssigned(String taskCode) { 
		List<BpmAssignedModel> bpmAssigned = new ArrayList<>();
		try {
			List<BpmAssignedModel> temporaryList = bpmAssignedService.findByTaskCodeAndInstanciaProccesIdNull(taskCode,true);
			if (temporaryList != null && !temporaryList.isEmpty()) {
				bpmAssigned.addAll(temporaryList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return bpmAssigned;
		}
		return bpmAssigned;
	}

	
	
	
	
	
	
	
	
	
	public BpmAssignedModel getOneAssigned(String taskCode, String codeEmployee, Long instanceProccesId, Long idProcess,Integer router) {
		
		BpmAssignedModel assignesFromRouter = null;
		AssignedModel assigned = null;
		
	     assigned = getAssignedModel(codeEmployee);
		
		if (assigned == null) {
			assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
		}
			
		if(assigned != null) {
			BpmAssignedModel temporal = this.getTaskAsigned(assigned, taskCode, instanceProccesId, idProcess);
			if(temporal != null) {
				assignesFromRouter = temporal;  
			}
		}
		
		return assignesFromRouter;
	}
	
	
	
	public BpmAssignedModel getTaskAsigned(AssignedModel assigned, String taskCode, Long instanceProccesId, Long idProcess) {
		
		 BpmAssignedModel bpmAssignedModel =  null;
		try {
			AssignedModel assignedInSisten = null;
			
			if(assigned.getId() == null && assigned.getCodeEmployee() != null) {
				 if (!assignedService.checkCodeEmployeeExists(assigned.getCodeEmployee())) {
						assigned.setActive(true);
						assignedInSisten = assignedService.save(assigned);
					}
			} else {
				assignedInSisten = assigned;
			}
			
					
			  bpmAssignedModel =  bpmAssignedService.findByCodeEmployeeAndTaskCode(assigned.getCodeEmployee(), taskCode); 
			
			 if(bpmAssignedModel == null) {
//				
				 if(instanceProccesId != null) {
				  bpmAssignedModel =  BpmAssignedModel.builder()
									  .taskCode(taskCode).codeEmployee(assignedInSisten.getCodeEmployee())
									  .idAssigned(assignedInSisten.getId()).instanciaProccesId(instanceProccesId).proccesId(idProcess)
									  .build();
				 } else {
					 bpmAssignedModel =  BpmAssignedModel.builder()
										  .taskCode(taskCode) .codeEmployee(assignedInSisten.getCodeEmployee())
										  .idAssigned(assignedInSisten.getId()).proccesId(idProcess)
										  .build();
				 }
			 }
			 
			if(assignedInSisten.getId() != null && bpmAssignedModel != null && bpmAssignedModel.getIdBpmAssigned() == null) {
				bpmAssignedService.saveOrUpdateBpmAssigned(bpmAssignedModel);
			}
			
			return bpmAssignedModel;
			
		}catch(Exception e) {
			e.printStackTrace();
			//TODO: registrar en el sistema de notificacion error and set logger
			return bpmAssignedModel;
		}
		
	}
	
	public AssignedModel getAssignedModel(String codeEmployee) {
		return assignedService.findByCodeEmployeeAndActive(codeEmployee, true);
	}
	
	
	
	public void saveAndCreteBpmAssigned(String taskCode, AssignedModel assigned, Long instanceProccesId) {
		
		try {
			if(assigned != null) {
				AssignedModel assignedSave = this.assignedService.save(assigned);
				if(assignedSave.getId() != null) {
					bpmAssignedService.saveOrUpdateBpmAssigned(new BpmAssignedModel(assignedSave.getId(), taskCode, instanceProccesId));
				}
			}
	
		}catch (Exception e) {
			e.printStackTrace();
			//TODO: registrar en el sistema de notificacion error.
		}
	}
	

}
