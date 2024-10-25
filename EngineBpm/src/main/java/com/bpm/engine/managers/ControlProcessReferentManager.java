package com.bpm.engine.managers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.ControlProcessReferentModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.TaskAssignedModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;

@Component
public class ControlProcessReferentManager {
	
	 @Autowired
	 private ControlProcessReferentService controlProcessReferentService;
	 
	 @Autowired
	 private AssignmentTaskManager assignmentTaskManager;
	 
	  
	public ControlProcessReferentModel createFromInstanceProcess(InstanceAbstractionModel  instanceProcess) {
		
		 ControlProcessReferentModel instance = ControlProcessReferentModel.builder()
				 .name(instanceProcess.getName())
				 .code(instanceProcess.getCodeProcess())
				 .title(instanceProcess.getTitle())
				 .status(SystemSate.ACTIVE.toString())
				 .type(instanceProcess.getInstanOf())
				 .idReference(instanceProcess.getIdInstance())
				 .dateCreate(new Date())
				 .active(true)
				 .build();
		 
		 		List<BpmAssignedModel> assigneds = new ArrayList<>();
		 		
		 		assigneds.add(assignmentTaskManager.getOneUserDirectAssigned(instanceProcess.getUserCreateInstance(), instanceProcess.getIdInstance(),instanceProcess.getIdProcess()));
		 		
		       instance.setAssignes(assigneds);
		 
		 
		 return controlProcessReferentService.saveOrUpdateInternalControlProcess(instance);
	}
	
	public ControlProcessReferentModel createFromInstanceTask(InstanceAbstractionModel taskModel, SystemRequest systemRequest, Long instanceProccesId) {
		
			 ControlProcessReferentModel instance = ControlProcessReferentModel.builder()
					 .name(taskModel.getName())
					 .code(taskModel.getCodeReferent())
					 .title(taskModel.getTitle())
					 .status(SystemSate.ACTIVE.toString())
					 .type(taskModel.getInstanOf())
					 .idReference(taskModel.getIdRefenet())
					 .dateCreate(new Date())
					 .active(true)
					 .build();
			 
			 instance.setAssignes(assignmentTaskManager.getAssigned(taskModel.getCodeReferent(),  systemRequest, taskModel.getIdInstanceOfProcess(), taskModel.getIdProcess()));
			  
				return controlProcessReferentService.saveOrUpdateInternalControlProcess(instance);

	 }
}














