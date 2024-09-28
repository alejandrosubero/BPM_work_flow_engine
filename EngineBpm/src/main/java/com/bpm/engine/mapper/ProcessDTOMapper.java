package com.bpm.engine.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.StageDTO;
import com.bpm.engine.dto.TaskDTO;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.utility.InstanOf;

//import org.springframework.stereotype.Component;

//@Component
public class ProcessDTOMapper {

	
	public ProcessDTO InstanceAbstractionModelToDTO(InstanceAbstractionModel instance) {
		
		
		
		
		
		
		
		
		ProcessDTO processDTO = ProcessDTO.builder().build();
		
		
		
		
		
		return null;
	}
	
	
	
	public List<StageDTO> getStage(List<InstanceAbstractionModel> instances) {
		
		List<StageDTO> stages = new ArrayList<>();
		
		if(instances != null && !instances.isEmpty()) {
			
			for(InstanceAbstractionModel instance : instances) {
				
				if(instance.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
					
					if(instance.getInstances() != null && !instance.getInstances().isEmpty()) {
						
						instance.getInstances().parallelStream().forEach(internalLevel ->{
							
							if(internalLevel.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
								
							}
							
							if(internalLevel.getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue()) &&
									(internalLevel.getInstances() != null && !internalLevel.getInstances().isEmpty())
								){
								
								getTaks(List<InstanceAbstractionModel> instances)
							}
						});

						
					}
				}
			}
			
			
		}
		
		return null;
	}
	
	
	public List<TaskDTO> getTaks(List<InstanceAbstractionModel> instances) {
		
		List<TaskDTO> lisOfTaks = new ArrayList<>();
		
		if(instances != null && !instances.isEmpty()) {
			
			for(InstanceAbstractionModel instance : instances) {
				if(instance.getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue())) {
					lisOfTaks.add( TaskDTO.builder()
					.id(instance.getIdInstance())
					.title(instance.getTitle())
					.name(instance.getName())
					.userCode(instance.getUserWorked())
					.status(instance.getStatus())
					.response(instance.getResponse())
					.codeOfTask(instance.getCodeReferent())
					.codeProcess(instance.getCodeProcess())
					.instanceOf(InstanOf.INSTANCE_TASK.getValue())
					.build());
				}
			}
		}
		
		return lisOfTaks;
	}
	
	
	
	
	
}
