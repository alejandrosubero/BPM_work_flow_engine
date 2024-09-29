package com.bpm.engine.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.ProcessModel;

@Component
public class InstanceProcessManagerDTO {

	
	
	 private ProcessManager processManager;
	 private InstanceProcessManager instanceProcessManager;
	  
	    
	@Autowired
	public InstanceProcessManagerDTO(ProcessManager processManager, InstanceProcessManager instanceProcessManager) {
		super();
		this.processManager = processManager;
		this.instanceProcessManager = instanceProcessManager;
	}






	public List<ProcessDTO> createInstanceAbstractionDTO (SystemRequest systemRequest) {
		  
		  
		List<InstanceAbstractionModel> instancesProcessOfUser	=	instanceProcessManager.getInstancesProcessDTO(systemRequest);
		List<ProcessModel> processOfUser = processManager.getProcessOfUser(systemRequest.getCodeEmployee());
		  
//		ProcessDTO processDTO =  new ProcessDTO(ProcessModel process);
		
		  
		  return null;
	  }
	
	
}
