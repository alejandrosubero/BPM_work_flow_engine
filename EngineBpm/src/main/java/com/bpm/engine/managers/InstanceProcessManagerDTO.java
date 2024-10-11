package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.mappers.ProcessDTOMapper;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ProcessModel;

@Component
public class InstanceProcessManagerDTO {

	
	 private static final Logger logger = LogManager.getLogger(InstanceProcessManagerDTO.class);
	
	 private ProcessAndInstanceFacade services;
	 private ProcessDTOMapper processDTOMapper ;
	  
	    
	@Autowired
	public InstanceProcessManagerDTO(ProcessAndInstanceFacade services,ProcessDTOMapper processDTOMapper) {
		this.services = services;
		this.processDTOMapper = processDTOMapper;
	}




	public List<ProcessDTO> getInstancesDTO (SystemRequest systemRequest) {
		  
		List<ProcessDTO> processList = new ArrayList<>();
		
		try {
			
		}catch(Exception e) {
			logger.error("Error in DTO service...",e);
			e.printStackTrace();
		}
		  
		List<InstanceAbstractionModel> instancesProcessOfUser =  services.instanceManager().getInstancesOfUser(systemRequest.getCodeEmployee());
		List<ProcessModel> processOfUser = services.processManager().getProcessOfUser(systemRequest.getCodeEmployee());
		  
		processOfUser.parallelStream().forEach(procesModel -> processList.add(new ProcessDTO(procesModel)));
		
		instancesProcessOfUser.parallelStream().forEach(InstanceAbstractionModel-> processList.add(processDTOMapper.instanceAbstractionModelToDTO(InstanceAbstractionModel)));

		  return processList;
	  }

}
