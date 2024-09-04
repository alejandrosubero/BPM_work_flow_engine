package com.bpm.engine.componets;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;

@Component
public class ControlProcessReferentManager {
	
	 @Autowired
	 private static ControlProcessReferentService controlProcessReferentService;
	 
	  
	 
	public static void createFromTask(List<InstanceTaskModel> listInstanceTaskModel) {
		 
		 for(InstanceTaskModel taskModel : listInstanceTaskModel) {
		
			 ControlProcessReferentModel instance = ControlProcessReferentModel.builder()
					 .name(taskModel.getName())
					 .code(taskModel.getCodeTask())
					 .title(taskModel.gettask().getTitle())
					 .status(SystemSate.ACTIVE.toString())
					 .type(Constants.TYPE_INSTANCE_TASK)
					 .idReference(taskModel.getIdInstanceTask())
					 .build();
			 
			 if (!taskModel.getassignes().isEmpty()) {
				instance.setAssignes(taskModel.getassignes());
			 }
			 controlProcessReferentService.saveOrUpdateInternalControlProcess(instance);
		 }
		 
	 }
	 
	public static ControlProcessReferentModel createFromInstanceProcess(InstanceProcessModel  instanceProcess) {
		
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
}














