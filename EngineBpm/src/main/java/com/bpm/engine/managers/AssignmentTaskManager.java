package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.model.TaskModel;
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
	public List<BpmAssignedModel> getAssigned(String taskModelCode, SystemRequest systemRequest, Long instanceProccesId, InstanceTaskModel taskModel) {
		List<BpmAssignedModel> taskAssignedModelList = new ArrayList<>();
		try {			

			if (systemRequest.checkAssigned(taskModelCode)) {
				// this part is for users direct assigned router = 0		
				taskAssignedModelList.addAll(this.getUserDirectAssigned(systemRequest.getAssigned().get(taskModelCode),  taskModelCode, instanceProccesId));
			} else {
					// this part is for user create the instance Process router = 1
				taskAssignedModelList.addAll(getUseTheUserWasCreateInstanceProcess(systemRequest.getCodeEmployee(), taskModelCode,instanceProccesId));
			}
			
		} catch (Exception e){
			e.printStackTrace();
			return taskAssignedModelList;
		}
		return taskAssignedModelList;
	}
	
	
	
	public List<BpmAssignedModel> getAssigned(String taskModelCode, SystemRequest systemRequest, Long instanceProccesId) {
		List<BpmAssignedModel> taskAssignedModelList = new ArrayList<>();
		try {			

			if (systemRequest.checkAssigned(taskModelCode)) {
				// this part is for users direct assigned router = 0		
				taskAssignedModelList.addAll(this.getUserDirectAssigned(systemRequest.getAssigned().get(taskModelCode),  taskModelCode, instanceProccesId));
			} else {
					// this part is for user create the instance Process router = 1
				taskAssignedModelList.addAll(getUseTheUserWasCreateInstanceProcess(systemRequest.getCodeEmployee(), taskModelCode,instanceProccesId));
			}
			
		} catch (Exception e){
			e.printStackTrace();
			return taskAssignedModelList;
		}
		return taskAssignedModelList;
	}
	
	
	public BpmAssignedModel getOneUserDirectAssigned(String codeEmployee, Long instanceProccesId){
		
		return this.getOneAssigned( null,  codeEmployee,  instanceProccesId,0);
	}
	
	/***
	 * 
	 * @param systemRequestAssigned
	 * @param taskCode
	 * @param instanceProccesId
	 * @return the User's Direct Assigned (this is only for a specific task in an instance..   
	 */
	private List<BpmAssignedModel> getUserDirectAssigned(List<String> systemRequestAssigned, String taskCode, Long instanceProccesId){
		List<BpmAssignedModel> list = new ArrayList<>();
		systemRequestAssigned.forEach(codeEmployee ->
		list.addAll(this.getTaskAssigned(taskCode, codeEmployee, instanceProccesId, 0)));
		return list;
	}
	
	/***
	 * 
	 * @param codeEmployee
	 * @param taskCode
	 * @param instanceProccesId
	 * @return the assigned user for the user was create the request of instance.
	 */
	private List<BpmAssignedModel> getUseTheUserWasCreateInstanceProcess(String codeEmployee, String taskCode, Long instanceProccesId){
		return this.getTaskAssigned(taskCode, codeEmployee, instanceProccesId, 1);
		//TODO: IN THE TASK (systemRequest) EXIST ROLE FOR THIS TASK HOW USE? 
	}
	
	
	public List<BpmAssignedModel> getTaskAssigned(String taskCode, String codeEmployee, Long instanceProccesId, Integer router) {
		List<BpmAssignedModel> assignes = new ArrayList<>();
		
		if( taskCode != null &&  codeEmployee!= null &&  instanceProccesId != null && router != null) {

			try {
			
				List<BpmAssignedModel> bpmAssigned = this.getAssignedFromBpmAssigned(taskCode);
	
				if (bpmAssigned != null && bpmAssigned.size() > 0) {
					assignes.addAll(bpmAssigned);
				} 
	
				if ((bpmAssigned == null || bpmAssigned.isEmpty())) {
					assignes.addAll(getAssigned(taskCode, codeEmployee, instanceProccesId, router));
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
	public List<BpmAssignedModel> getAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		
		List<BpmAssignedModel> assignesFromRouter = new ArrayList<>();
		AssignedModel assigned = null;
		
		if (router == 1) {
			
			AssignedModel employeeCreator = this.assignedService.findByCodeEmployeeAndActive(codeEmployee, true);
			
			if (employeeCreator != null && employeeCreator.getemployeeRole().getCodeRole() != null) {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee, employeeCreator.getemployeeRole().getCodeRole());
			} else {
				assigned = conectBpmToEmployeeService.getAssigned(codeEmployee);
			}
			
			if(assigned != null) {
				assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, null));
			}
		}
		
		if (router == 0) {
			assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
			if(assigned != null) {
				assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, instanceProccesId));  
			}
		}
		
		
		return assignesFromRouter;
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

	
	public BpmAssignedModel getTaskAsigned(AssignedModel assigned, String taskCode, Long instanceProccesId) {
		
	try {
			AssignedModel assignedInSisten = assignedService.findByCodeEmployeeAndActive(assigned.getCodeEmployee(), true);
		
		if (assignedInSisten == null) {
			assigned.setActive(true);
			assignedInSisten = assignedService.save(assigned);
		}		
		return new BpmAssignedModel(assignedInSisten.getId(), taskCode, instanceProccesId, assignedInSisten.getCodeEmployee());
	}catch(Exception e) {
		e.printStackTrace();
		//TODO: registrar en el sistema de notificacion error and set logger
		return null;
	}
		
	}
	
	
	
	public BpmAssignedModel getOneAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		
		BpmAssignedModel assignesFromRouter = null;
		AssignedModel assigned = null;
			
		if (router == 0) {
			assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
			if(assigned != null) {
				BpmAssignedModel temporal = this.getTaskAsigned(assigned, taskCode, instanceProccesId);
				if(temporal != null) {
					assignesFromRouter = temporal;  
				}
			}
		}
		return assignesFromRouter;
	}
	
	
	
	
	
	
	

}
