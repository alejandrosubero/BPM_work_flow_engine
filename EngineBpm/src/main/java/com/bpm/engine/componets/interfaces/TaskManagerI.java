package com.bpm.engine.componets.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.model.TaskModel;


public interface TaskManagerI {
	
	/***
	 * 
	 * @param stageModel
	 * @param systemRequest
	 * @param instanceProccesId
	 * @return  List<InstanceTaskModel> Read the task into the StageModel a created the list of InstanceTaskModel
	 */
	default List<InstanceTaskModel> setTask(StageModel stageModel, SystemRequest systemRequest, Long instanceProccesId) {
		List<InstanceTaskModel> taskList = new ArrayList<>();

		if (null != stageModel.gettasks() && !stageModel.gettasks().isEmpty()) {
			stageModel.gettasks().forEach(taskModel -> taskList.add(new InstanceTaskModel(taskModel, instanceProccesId)));
		}
		return taskList;
	}
}






























