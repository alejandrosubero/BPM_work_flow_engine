package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.mapper.ProcessDTOMapper;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.ProcessModel;

@Component
public class InstanceProcessManagerDTO {

	
	 private static final Logger logger = LogManager.getLogger(InstanceProcessManagerDTO.class);
	
	 private ProcessManager processManager;
	 private InstanceProcessManager instanceProcessManager;
	 private ProcessDTOMapper processDTOMapper ;
	  
	    
	@Autowired
	public InstanceProcessManagerDTO(ProcessManager processManager, InstanceProcessManager instanceProcessManager,ProcessDTOMapper processDTOMapper) {
		this.processManager = processManager;
		this.instanceProcessManager = instanceProcessManager;
		this.processDTOMapper = processDTOMapper;
	}




	public List<ProcessDTO> getInstancesDTO (SystemRequest systemRequest) {
		  
		List<ProcessDTO> processList = new ArrayList<>();
		
		try {
			
		}catch(Exception e) {
			logger.error("Error in DTO service...",e);
			e.printStackTrace();
		}
		  
		List<InstanceAbstractionModel> instancesProcessOfUser = instanceProcessManager.getInstancesProcessDTO(systemRequest);
		List<ProcessModel> processOfUser = processManager.getProcessOfUser(systemRequest.getCodeEmployee());
		  
		
		processOfUser.parallelStream().forEach(procesModel -> processList.add(new ProcessDTO(procesModel)));
		
		instancesProcessOfUser.parallelStream().forEach(InstanceAbstractionModel-> processList.add(processDTOMapper.instanceAbstractionModelToDTO(InstanceAbstractionModel)));
		

		  return processList;
	  }



	
	
}
