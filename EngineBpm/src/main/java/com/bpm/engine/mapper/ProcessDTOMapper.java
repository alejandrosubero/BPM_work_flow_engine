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
	
	
	
	// stage 1
	public List<StageDTO> getStage(List<InstanceAbstractionModel> instancesLevel1) {
		
		List<StageDTO> stages = new ArrayList<>();
		List<StageDTO> stagesTemporal = new ArrayList<>();
		
		
		
		if(instancesLevel1 != null && !instancesLevel1.isEmpty()) {
			
			for(InstanceAbstractionModel instanceLeveFroml1 : instancesLevel1) {
				
				if(instanceLeveFroml1.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
					
					StageDTO stageL1 = getStageDTO(instanceLeveFroml1);		
					
					if(instanceLeveFroml1.getInstances() != null && !instanceLeveFroml1.getInstances().isEmpty()) {
						
						List<TaskDTO> lisOfTaks = new ArrayList<>();
						
//						instanceLeveFroml1.getInstances().parallelStream().forEach(internalLevel ->{
						
						StageDTO stageInstanceLeveFroml1 = null;
							
							if(instanceLeveFroml1.getInstances().get(0).getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
								
								
							}
							
							if(instanceLeveFroml1.getInstances().get(0).getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue())){
								
								this.getTaks(instanceLeveFroml1.getInstances());
							}
//						});
					}
				}
			}
			
			
		}
		
		return null;
	}
	
	
	public StageDTO getStageDTO(InstanceAbstractionModel instanceStage) {
		return StageDTO.builder()
		.id(instanceStage.getIdInstance())
		.name(instanceStage.getName())
		.Code(instanceStage.getCodeReferent())
		.title(instanceStage.getTitle())
		.instanceOf(InstanOf.INSTANCE_STAGE.getValue())
		.build();
	}
	
	
	public TaskDTO getTask(InstanceAbstractionModel instanceTask) {
		
			return TaskDTO.builder()
					.id(instanceTask.getIdInstance())
					.title(instanceTask.getTitle())
					.name(instanceTask.getName())
					.userCode(instanceTask.getUserWorked())
					.status(instanceTask.getStatus())
					.response(instanceTask.getResponse())
					.codeOfTask(instanceTask.getCodeReferent())
					.codeProcess(instanceTask.getCodeProcess())
					.instanceOf(InstanOf.INSTANCE_TASK.getValue())
					.build();
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
