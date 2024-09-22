package com.bpm.engine.managers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.service.ProcessService;

@Component
public class InstanceProcessManagerDTO {

	
	  private ProcessService processService;
	  private InstanceManager instanceManager;
	    
	  
	  public InstanceProcessManagerDTO(ProcessService processService, InstanceManager instanceManager) {
		super();
		this.processService = processService;
		this.instanceManager = instanceManager;
	}




	public List<ProcessDTO> createInstanceAbstractionDTO (SystemRequest systemRequest) {
		  
		  
		  
		  
		  
		  
		  return null;
	  }
	
	
}
