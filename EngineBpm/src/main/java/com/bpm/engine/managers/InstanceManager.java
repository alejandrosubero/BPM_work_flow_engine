package com.bpm.engine.managers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.service.InstanceAbstractionService;
import com.bpm.engine.utility.InstanOf;
import com.bpm.engine.utility.SystemSate;

@Service
public class InstanceManager {
	
	@Autowired
	private InstanceAbstractionService instanceAbstractionService;

	

	public InstanceAbstractionModel createFromProcess(ProcessModel processModel) {
		
		return  InstanceAbstractionModel.builder()
		.name(processModel.getName())
		.instanOf(InstanOf.INSTANCE_PROCESS.getValue())
		.title(processModel.getProcesTitle())
		.idProcess(processModel.getId_process())
		.idRefenet(processModel.getId_process())
		.codeProcess(processModel.getProcesCode())
		.codeReferent(processModel.getProcesCode())
		.isParallel(false)
		.status(SystemSate.CREATE.toString())
		.active(true)
		.dateCreate(new Date())
		.userCreateInstance(processModel.getUserCreate())
		.build();
	}
	
	
	
	//TODO: RESOLVE THE PARALLEL IN STAGE 
	public InstanceAbstractionModel createFromStage(InstanceAbstractionModel parent, StageModel stage, Long idInstanceOfProcess) {
		
		return InstanceAbstractionModel.builder()
		.name(stage.getName())
		.instanOf(InstanOf.INSTANCE_STAGE.getValue())
		.title(stage.getTitle())
		.idProcess(parent.getIdProcess())
		.codeProcess(parent.getCodeProcess())
		.idInstanceOfProcess(idInstanceOfProcess)
		.idRefenet(parent.getIdInstance())
		.codeReferent(stage.getStageCode())
		.isParallel(false)
		.status(SystemSate.CREATE.toString())
		.active(true)
		.dateCreate(new Date())
		.userCreateInstance(parent.getUserCreateInstance())
		.level(stage.getStageNumber())
		.build();
		
	}
	
	
	//TODO: RESOLVE THE PARALLEL IN  TASK
	public InstanceAbstractionModel createFromTask(InstanceAbstractionModel parent, TaskModel task) {
		
		InstanceAbstractionModel instance =  InstanceAbstractionModel.builder()
		.name(task.getName())
		.instanOf(InstanOf.INSTANCE_TASK.getValue())
		.title(task.getTitle())
		.codeReferent(task.getCode())
		.idRefenet(task.getIdTask())
		.isParallel(task.getIsParallel())
		.level(task.getTaskNumber())
		.apprubeType(task.getApprubeType())
		.type(task.getType())
		.abstractFieldNumber0(task.getTaskDueTime())
		.abstractField2(task.getUrlService())
		.abstractField4(task.getTaskUrl())
		.idProcess(parent.getIdProcess())
		.codeProcess(parent.getCodeProcess())
		.idInstanceOfProcess(parent.getIdInstanceOfProcess())
		.userCreateInstance(parent.getUserCreateInstance())
		.status(SystemSate.CREATE.toString())
		.active(true)
		.dateCreate(new Date())
		.build();
		
		if(task.getIsParallel()) { 
			instance.setAbstractFieldNumber1(task.getParallelWithTaskNumber());
		}
		
		if(task.getRulers() != null && !task.getRulers().isEmpty()) {
			
			task.getRulers().forEach(ruler ->{
				
				if( ruler.getCondition() !=null && ruler.getCondition().equals(SystemSate.APPROVED.toString()) ) {
					
					if(ruler.getTaskNumber() != null) {
						instance.setAbstractFieldNumber2(ruler.getTaskNumber());
					}
					if(ruler.getAction() != null ) {
						instance.setAbstractFieldNumber4(ruler.getAction());
					}
					if(ruler.getTaskCode() !=null) {
						instance.setAbstractField5(ruler.getTaskCode());
					}
				}
				
				
				if( ruler.getCondition() !=null && ruler.getCondition().equals(SystemSate.REJECTED.toString()) ) {
					
					if(ruler.getTaskNumber() != null) {
						instance.setAbstractFieldNumber3(ruler.getTaskNumber());
					}
					if(ruler.getAction() != null ) {
						instance.setAbstractFieldNumber4(ruler.getAction());
					}
					if(ruler.getTaskCode() !=null) {
						instance.setAbstractField6(ruler.getTaskCode());
					}
				}
			});
		}
		
		
		
		if(parent.getLevel() == 1 ) {
			//TODO: IMPLEMENT THE ADSING USER.....
		}
		
		
		 
		 return null;
	}
	
}

















