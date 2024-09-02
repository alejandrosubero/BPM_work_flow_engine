package com.bpm.engine.componets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.model.TaskModel;

@Component
public class TaskManager {

	private AssignmentTaskManager assignmentTaskManager;

	@Autowired
	public TaskManager(AssignmentTaskManager assignmentTaskManager) {
	this.assignmentTaskManager = assignmentTaskManager;
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
				//TODO: IN THE TASK EXIST ROLE FOR THIS TASK HOW USE?  
				if (stageModel.getStageNumber() == 1) {
					//This point select assigned for all tasks on the first stage. 
					taskList.add(
							new InstanceTaskModel(
									setAssignedToTask( taskModel.getCode(),  systemRequest,  instanceProccesId), 
									taskModel, instanceProccesId)
							);
				} else {
					
					taskList.add(new InstanceTaskModel(taskModel, instanceProccesId));
				}				
			});
		}
		return taskList;
	}

	

	public List<TaskAssignedModel> setAssignedToTask(String taskModelCode, SystemRequest systemRequest, Long instanceProccesId) {
		List<TaskAssignedModel> taskAssignedModelList = new ArrayList<>();
		try {			

			if (systemRequest.checkAssigned(taskModelCode)) {
				
				// this part is for users direct assigned router = 0		
				taskAssignedModelList.addAll(this.getUserDirectAssigned(systemRequest.getAssigned().get(taskModelCode),  taskModelCode, instanceProccesId));
			} else {
					// this part is for user create the instance Process router = 1
				taskAssignedModelList.addAll(getUseTheUserWasCreateInstanceProcess(systemRequest.getCodeEmployee(), taskModelCode,instanceProccesId));
			}
			// router = 2  no implement's   getTaskAssignedFromBpmAssigned
		} catch (Exception e){
			e.printStackTrace();
			return taskAssignedModelList;
		}
		return taskAssignedModelList;
	}
	
	
	
	private List<TaskAssignedModel> getUserDirectAssigned(List<String> systemRequestAssigned, String taskCode, Long instanceProccesId){
		List<TaskAssignedModel> list = new ArrayList<>();
		systemRequestAssigned.forEach(codeEmployee ->
		list.addAll(this.assignmentTaskManager.getTaskAssigned(taskCode, codeEmployee, instanceProccesId, 0)));
		return list;
	}
	
	
	private List<TaskAssignedModel> getUseTheUserWasCreateInstanceProcess(String codeEmployee, String taskCode, Long instanceProccesId){
		return this.assignmentTaskManager.getTaskAssigned(taskCode, codeEmployee, instanceProccesId, 1);
		
	}
	

}






























