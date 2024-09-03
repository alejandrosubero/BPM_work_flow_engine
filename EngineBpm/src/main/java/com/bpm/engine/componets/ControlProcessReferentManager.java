package com.bpm.engine.componets;


import java.util.List;

import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.service.ControlProcessReferentService;

public class ControlProcessReferentManager {
	
	
	
	 private ControlProcessReferentService controlProcessReferentService;
	 
	 
	 
	 
	 
	 public void createControlProcessReferentFromTask(List<InstanceTaskModel> listInstanceTaskModel) {
		 
		 for(InstanceTaskModel taskModel : listInstanceTaskModel) {
			 //String code, String name, String title, String status, String type, Long idReference
			 ControlProcessReferentModel instance = 
					 
					 ControlProcessReferentModel.builder()
					 .name(null)
					 .code(null)
					 .title(null)
					 .status(null)
					 .type(null)
					 .idReference(null)
					 .build();
		 }
		 
	 }
	 
	 
	 

}
