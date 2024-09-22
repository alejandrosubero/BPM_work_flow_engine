package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.service.InstanceAbstractionService;
import com.bpm.engine.utility.InstanOf;
import com.bpm.engine.utility.SystemSate;

@Component
public class InstanceManager {
	
	@Autowired
	private InstanceAbstractionService instanceAbstractionService;
	
	 @Autowired
	 private AssignmentTaskManager assignmentTaskManager;

	
	 public InstanceAbstractionModel saveCompliteInstance(InstanceAbstractionModel instance) {
		 return instanceAbstractionService.saveInitial(instance);
	 }
	 
	 
	 public List<InstanceAbstractionModel> getInstanceBySearch(String keyword) {
		 return instanceAbstractionService.finBySearch(keyword);
	 }
	 
	

	public InstanceAbstractionModel createFromProcess(ProcessModel processModel) {
		
		InstanceAbstractionModel instance = InstanceAbstractionModel.builder()
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
		
		InstanceAbstractionModel instanceSave = instanceAbstractionService.save(instance);
		
		return instanceSave;
		
	}
	
	
	
	//TODO: RESOLVE THE PARALLEL IN STAGE 
	public InstanceAbstractionModel createFromStage(InstanceAbstractionModel parent, StageModel stage) {
		
		return  InstanceAbstractionModel.builder()
		.idProcess(parent.getIdProcess())
		.codeProcess(parent.getCodeProcess())
		.userCreateInstance(parent.getUserCreateInstance())
		.idInstanceOfProcess(parent.getIdInstance())
		
		.instanOf(InstanOf.INSTANCE_STAGE.getValue())
		.idRefenet(stage.getIdStage())
		.codeReferent(stage.getStageCode())
		.title(stage.getTitle())
		.name(stage.getName())
		.isParallel(false)
		.status(SystemSate.CREATE.toString())
		.active(true)
		.dateCreate(new Date())
		.level(stage.getStageNumber())
		.build();
		
//		InstanceAbstractionModel instanceSave = instanceAbstractionService.save(instance);
		
//		return instance;
	}
	
	
	public List<InstanceAbstractionModel> createFromListOfTask(InstanceAbstractionModel parent, List<TaskModel> tasksList, SystemRequest systemRequest){
		
		List<InstanceAbstractionModel> listOfInstanceAbstractionModel = new ArrayList<>();
		
		tasksList.parallelStream().forEach(task -> {
			if(parent.getLevel() == 1 ) {
				List<BpmAssignedModel> listAssigned = assignmentTaskManager.getAssigned(task.getCode(), systemRequest, parent.getIdInstanceOfProcess());	
				listOfInstanceAbstractionModel.addAll(this.createFromTaskListOfUsers(listAssigned,parent, task));
			}else {
				
				listOfInstanceAbstractionModel.add( this.createFromTask( parent,  task, null));
			}
		});
		return listOfInstanceAbstractionModel;
	}
	
	
	public  List<InstanceAbstractionModel> createFromTaskListOfUsers(List<BpmAssignedModel> listAssigned,InstanceAbstractionModel parent, TaskModel task) {
		
		List<InstanceAbstractionModel> listOfInstance = new ArrayList<>();
		listAssigned.parallelStream().forEach(bpmAssigned -> listOfInstance.add(this.createFromTask(parent,task,bpmAssigned.getCodeEmployee())));
		return listOfInstance;
	}
	
	
	public InstanceAbstractionModel createFromTask(InstanceAbstractionModel parent, TaskModel task, String userwork) {
		
		InstanceAbstractionModel instance =  InstanceAbstractionModel.builder()
		
		.idProcess(parent.getIdProcess())
		.codeProcess(parent.getCodeProcess())
		.idInstanceOfProcess(parent.getIdInstanceOfProcess())
		.userCreateInstance(parent.getUserCreateInstance())
		
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
		.abstractField3(task.getUrlService())
		.abstractField4(task.getTaskUrl())
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
						instance.setAbstractFieldNumber5(ruler.getAction());
					}
					if(ruler.getTaskCode() !=null) {
						instance.setAbstractField6(ruler.getTaskCode());
					}
				}
			});
		}
		
		if(userwork !=null && !userwork.equals("") ) {
			instance.setUserWorked(userwork);
			instance.setStatus(SystemSate.ASSIGNED.toString());
		}else {
			instance.setStatus(SystemSate.CREATE.toString());
		}
		
//		InstanceAbstractionModel instanceSave = instanceAbstractionService.save(instance);
		
		 return instance;
	}
	
	
	
}

















