package com.bpm.engine.componets;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;

@Component
public class ControlProcessReferentManager {
	
	 @Autowired
	 private ControlProcessReferentService controlProcessReferentService;
	 
	 @Autowired
	 private AssignmentTaskManager assignmentTaskManager;
	 
	  
	public ControlProcessReferentModel createFromInstanceProcess(InstanceProcessModel  instanceProcess) {
		
		 ControlProcessReferentModel instance = ControlProcessReferentModel.builder()
				 .name(instanceProcess.getName())
				 .code(instanceProcess.getprocess().getProcesCode())
				 .title(instanceProcess.getTitle())
				 .status(SystemSate.ACTIVE.toString())
				 .type(Constants.TYPE_INSTANCE_PROCESS)
				 .idReference(instanceProcess.getIdInstanceProcess())
				 .build();
		 return controlProcessReferentService.saveOrUpdateInternalControlProcess(instance);
	}
	
	public ControlProcessReferentModel createFromInstanceTask(InstanceTaskModel taskModel, SystemRequest systemRequest, Long instanceProccesId) {
		
			 ControlProcessReferentModel instance = ControlProcessReferentModel.builder()
					 .name(taskModel.getName())
					 .code(taskModel.getCodeTask())
					 .title(taskModel.getTask().getTitle())
					 .status(SystemSate.ACTIVE.toString())
					 .type(Constants.TYPE_INSTANCE_TASK)
					 .idReference(taskModel.getIdInstanceTask())
					 .build();
			 
				return controlProcessReferentService.saveOrUpdateInternalControlProcess(instance);

	 }
	
	
	
	
	
	
	
	
}














