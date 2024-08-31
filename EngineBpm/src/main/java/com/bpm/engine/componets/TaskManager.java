package com.bpm.engine.componets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;
//import com.bpm.engine.service.TaskAssignedService;

@Component
public class TaskManager {

	private BpmAssignedService bpmAssignedService;
	private AssignedService assignedService;
	private ConectBpmToEmployeeService conectBpmToEmployeeService;
//	private TaskAssignedService taskAssignedService;

//	@Autowired
//	public TaskManager(BpmAssignedService bpmAssignedService, AssignedService assignedService,
//			TaskAssignedService taskAssignedService, ConectBpmToEmployeeService conectBpmToEmployeeService) {
//		this.bpmAssignedService = bpmAssignedService;
//		this.assignedService = assignedService;
//		this.taskAssignedService = taskAssignedService;
//		this.conectBpmToEmployeeService = conectBpmToEmployeeService;
//	}

	
	@Autowired
	public TaskManager(BpmAssignedService bpmAssignedService, AssignedService assignedService, ConectBpmToEmployeeService conectBpmToEmployeeService) {
		this.bpmAssignedService = bpmAssignedService;
		this.assignedService = assignedService;
		this.conectBpmToEmployeeService = conectBpmToEmployeeService;
	}

	

	/***
	 * 
	 * @param stageModel
	 * @param systemRequest
	 * @param instanceProccesId
	 * @return  List<InstanceTaskModel> Read the task into the StageModel a created the list of InstanceTaskModel
	 */
	public List<InstanceTaskModel> setTask(StageModel stageModel, SystemRequest systemRequest, Long instanceProccesId) {
		List<InstanceTaskModel> taskList = new ArrayList<>();

		if (null != stageModel.gettasks() && !stageModel.gettasks().isEmpty()) {

			stageModel.gettasks().forEach(taskModel -> {
				if (stageModel.getStageNumber() == 1) {
					//This point select assigned for all tasks on the first stage. 
					taskList.add(new InstanceTaskModel(setAssignedToTask( taskModel,  systemRequest,  instanceProccesId), 
									taskModel, instanceProccesId));
				} else {
					
					taskList.add(new InstanceTaskModel(taskModel, instanceProccesId));
				}				
			});
		}
		return taskList;
	}

	

	public List<TaskAssignedModel> setAssignedToTask(TaskModel taskModel, SystemRequest systemRequest, Long instanceProccesId) {
		List<TaskAssignedModel> taskAssignedModelList = new ArrayList<>();
		try {			

			if (systemRequest.getAssigned() != null && systemRequest.getAssigned().containsKey(taskModel.getCode())
					&& !systemRequest.getAssigned().get(taskModel.getCode()).isEmpty()) {
				
				// this part is for users direct assigned router = 0
				List<String> systemRequestAssigned = systemRequest.getAssigned().get(taskModel.getCode());
				systemRequestAssigned.forEach(codeEmployee ->
						taskAssignedModelList.addAll(setTaskAssigned(taskModel.getCode(), codeEmployee, instanceProccesId, 0)));
				
			} else {
					// this part is for user create the instance Process router = 1
				taskAssignedModelList.addAll(setTaskAssigned(taskModel.getCode(), systemRequest.getCodeEmployee(), instanceProccesId, 1));
			}
			
			// router = 2  no implement's   getTaskAssignedFromBpmAssigned
			
			
		} catch (Exception e){
			e.printStackTrace();
			return taskAssignedModelList;
		}
		return taskAssignedModelList;
	}
	
	
	private List<TaskAssignedModel> setTaskAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		List<TaskAssignedModel> assignes = new ArrayList<>();
		try {
			
			List<TaskAssignedModel> bpmAssigned = this.getTaskAssignedFromBpmAssigned(taskCode);

			if (bpmAssigned != null && bpmAssigned.size() > 0) {
				assignes.addAll(bpmAssigned);
			} 

			if ((bpmAssigned == null || bpmAssigned.isEmpty()) && router == 0 || router == 1) {
				assignes.addAll(getAssigned(taskCode, codeEmployee, instanceProccesId, router));
			}

			if ((bpmAssigned == null || bpmAssigned.isEmpty()) && router == 2) {
				assignes.addAll(this.getTaskAssignedFromBpmAssigned(taskCode));
			}
				
					
		} catch (Exception e) {
			e.printStackTrace();
			return assignes;
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
	private List<TaskAssignedModel> getAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		
		List<TaskAssignedModel> assignesFromRouter = new ArrayList<>();
		AssignedModel assigned = null;
		
		if (router == 1) {
			
			AssignedModel employeeCreator = this.assignedService.findByCodeEmployeeAndActive(codeEmployee, true);
			
			if (employeeCreator != null && employeeCreator.getemployeeRole().getCodeRole() != null) {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee, assigned.getemployeeRole().getCodeRole());
			} else {
				assigned = conectBpmToEmployeeService.getAssigned(codeEmployee, null);
			}
		}
		
		if (router == 0) {
			assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
		}
		
		if(assigned != null) {
			bpmAssignedService.saveOrUpdateBpmAssigned(new BpmAssignedModel(this.assignedService.save(assigned).getId(), taskCode));
		}
		
		assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, instanceProccesId));
		return assignesFromRouter;
	}
	
	
	
/***
 * 
 * @param taskCode
 * @return TaskAssignedModel List from BpmAssigned for taskCode were InstanciaProccesId is null? in data base.
 */
	private List<TaskAssignedModel> getTaskAssignedFromBpmAssigned(String taskCode) {
		//TODO: require analyze for implement ...  
		List<TaskAssignedModel> bpmAssigned = new ArrayList<>();
		try {
			List<BpmAssignedModel> bpmAssignedList = bpmAssignedService.findByTaskCodeAndInstanciaProccesIdNull(taskCode,true);
			
			if (bpmAssignedList != null && bpmAssignedList.size() > 0) {
				bpmAssignedList.stream().forEach(bpmAssignedModel -> 
				bpmAssigned.add(new TaskAssignedModel(bpmAssignedModel.getIdBpmAssigned())));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return bpmAssigned;
		}
		return bpmAssigned;
	}

	
	private TaskAssignedModel getTaskAsigned(AssignedModel assigned, String taskCode, Long instanceProccesId) {
		AssignedModel assignedInSisten = assignedService.findByCodeEmployeeAndActive(assigned.getCodeEmployee(), true);
		if (assignedInSisten == null) {
			assignedInSisten = assignedService.save(assigned);
		}
		BpmAssignedModel bpmAssigned = bpmAssignedService.instanceBpmAssigned(assignedInSisten.getId(), taskCode,
				instanceProccesId);
		return new TaskAssignedModel(bpmAssigned.getIdBpmAssigned());
	}

}
