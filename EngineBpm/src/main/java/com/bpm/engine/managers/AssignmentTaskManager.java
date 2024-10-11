package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.RoleModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;

@Component
public class AssignmentTaskManager {
	 private static final Logger logger = LogManager.getLogger(AssignmentTaskManager.class);
	
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
		String codeRole = null;
		
		if (router == 1) {
			
			if (employee.getemployeeRole() != null && employee.getemployeeRole().getCodeRole() != null) {
				codeRole = employee.getemployeeRole().getCodeRole();
			}
			assigned = this.getAssignedFromExternalService(codeEmployee,codeRole);
		
			if(assigned != null) {
				assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, null,idProcess));
			}
		}
		
		if (router == 0) {
			
			if (employee == null) {
				assigned = this.getEmployeeFromExternalService(codeEmployee);
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
			assigned = this.getEmployeeFromExternalService(codeEmployee);
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
	
	
	public AssignedModel saveAssigned(AssignedModel assigned) {
		return this.assignedService.save(assigned);
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
	

	@SuppressWarnings("finally")
	public AssignedModel changeRoleAssigned(String codeEmployee, RoleModel newRole) {
		
		AssignedModel assignedResponse = null;
		AssignedModel updateAssigned = null;
		
		try {
			 updateAssigned = this.getAssignedModel(codeEmployee);
			
			if(updateAssigned != null ) {
				
				if(newRole != null) {
					assignedResponse =  this.setNewRole(updateAssigned,newRole);
				} else {
					AssignedModel assigned = this.getEmployeeFromExternalService(codeEmployee);
					if(assigned != null) {
						assignedResponse = this.setNewRole(updateAssigned,assigned.getemployeeRole());
					}
				}
			}
		}catch( DataAccessException e) {
			 logger.error("Error change Role Assigned: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}finally {
			return assignedResponse;
		}
	}
	
	
	@SuppressWarnings("finally")
	private AssignedModel setNewRole(AssignedModel updateAssigned,  RoleModel newRole) {
		AssignedModel assignedResponse = null;
	
		try {
			if(updateAssigned != null && newRole != null) {
				updateAssigned.setemployeeRole(newRole);
				assignedResponse = this.saveAssigned(updateAssigned);
			}
			
			
		}catch( DataAccessException e) {
			 logger.error("Error change Role Assigned: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}finally {
			return assignedResponse;
		}
		
	}
	
	
	
	private AssignedModel getAssignedFromExternalService(String codeEmployee, String codeRole) {
		
		AssignedModel assigned = null;
		
		try {
			
			if (codeEmployee != null && codeRole != null) {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee, codeRole);
			} else {
				
				if (codeRole != null)
					assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee);
			}
			
		}catch( DataAccessException e) {
			 logger.error("Error change Role Assigned: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}
		
		return assigned;
	}
	
	
	private AssignedModel getEmployeeFromExternalService(String codeEmployee) {
		AssignedModel assigned = null;

	try {
		if (codeEmployee == null) {
			assigned = conectBpmToEmployeeService.getEmployeeFromEmployeeService(codeEmployee);
		}
			
		}catch( DataAccessException e) {
			 logger.error("Error change Role Assigned: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}
		return assigned;
	}
	
	
	
	
	
	
	
	
}
