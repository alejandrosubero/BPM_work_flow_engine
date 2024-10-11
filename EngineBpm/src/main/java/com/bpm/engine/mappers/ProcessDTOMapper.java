package com.bpm.engine.mappers;

import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.dto.StageDTO;
import com.bpm.engine.dto.TaskDTO;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.utility.InstanOf;

import org.springframework.stereotype.Component;

@Component
public class ProcessDTOMapper {

	
	public ProcessDTO instanceAbstractionModelToDTO(InstanceAbstractionModel instance) {
		
		ProcessDTO processDTO = null;
		
		if(instance.getInstanOf().equals(InstanOf.INSTANCE_PROCESS.getValue())) {
			
			 processDTO = ProcessDTO.builder()
					.id(instance.getIdInstance())
					.name(instance.getName())
					.Title(instance.getTitle())
					.state(instance.getStatus())
					.userCreate(instance.getUserCreateInstance())
					.instanceOf(instance.getInstanOf())
					.build();
			
			if(instance.getInstances() != null && !instance.getInstances().isEmpty()) {
				processDTO.setStages(this.getStage(instance.getInstances()));
			}
		}
		
		return processDTO;
	}
	
	
	

	public List<StageDTO> getStage(List<InstanceAbstractionModel> instancesLevel1) {
		
		List<StageDTO> stages = new ArrayList<>();
			
		if(instancesLevel1 != null && !instancesLevel1.isEmpty()) {
			
//			for(InstanceAbstractionModel parent : instancesLevel1) {
				
				instancesLevel1.parallelStream().forEach(parent ->{
				
				if(parent.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
					
						StageDTO stageL1 = getStageDTO(parent);		
					
						if(parent.getInstances() != null && !parent.getInstances().isEmpty()) {
						
							if(parent.getInstances().get(0).getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
								stageL1.setStages(this.getInternalStageDTO(parent.getInstances()));// set stages list
							}
						
							if(parent.getInstances().get(0).getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue())){
								stageL1.setTasks(this.getTaks(parent.getInstances()));
							}
						}
						stages.add(stageL1);	
				}
			});
//			}
		}
		
		return stages;
	}
	
	
	public List<StageDTO> getInternalStageDTO(List<InstanceAbstractionModel> instanceStage) {
		
		List<StageDTO> internalStageDTOList= new ArrayList<>();
		
		instanceStage.parallelStream().forEach(stage ->{
			
			StageDTO stageChild = getStageDTO(stage);		
			
			if(stage.getInstances() != null && !stage.getInstances().isEmpty()) {	
				stageChild.setTasks(this.getTaks(stage.getInstances()));
			}
			
			internalStageDTOList.add(stageChild);
		});
		return internalStageDTOList;
	}
	
	
	public List<TaskDTO> getTaks(List<InstanceAbstractionModel> instances) {
		
		List<TaskDTO> lisOfTaks = new ArrayList<>();
		
		if(instances != null && !instances.isEmpty()) {
			instances.parallelStream().forEach(instance ->{
				if(instance.getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue())) {
					lisOfTaks.add( this.getTask(instance));
				}
			});
//			for(InstanceAbstractionModel instance : instances) {
//				if(instance.getInstanOf().equals(InstanOf.INSTANCE_TASK.getValue())) {
//					lisOfTaks.add( this.getTask(instance));
//				}
//			}
		}
		return lisOfTaks;
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
	
	

	
	
	
	
	
}
