package com.bpm.engine.managers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.ProcessService;


@Service
public class InstanceProcessManager {

	private static final Logger logger = LogManager.getLogger(InstanceProcessManager.class);
	
    private ProcessService processService;
    private InstanceManager instanceManager;
   
    @Autowired
    public InstanceProcessManager(ProcessService processService, InstanceManager instanceManager) {
		super();
		this.processService = processService;
		this.instanceManager = instanceManager;
	}
    
    
    public List<InstanceAbstractionModel> getInstancesProcessDTO(SystemRequest systemRequest) {
    	
    	List<InstanceAbstractionModel> listInstancesProcessDTO = instanceManager.getInstancesOfUser(systemRequest.getCodeEmployee());
    	
    	return listInstancesProcessDTO;
    }
    
    
   public InstanceAbstractionModel createInstanceProcess2 (SystemRequest systemRequest) {
		
	   logger.info("Started create Instance Process ...");
	   
        ProcessModel process = processService.findByProcesCode(systemRequest.getProcessCode());
      
        if(process == null) {
        	logger.error("Fail to find a process......");
        	return null;
        }
        
        InstanceAbstractionModel instanceProcess = instanceManager.createFromProcess(process, systemRequest.getCodeEmployee());
       
        process.getstages().parallelStream().forEach(stage->{
        	InstanceAbstractionModel instanceStageParen = instanceManager.createFromStage(instanceProcess,stage);
        	
        	if(stage.gettasks() != null && !stage.gettasks().isEmpty()) {
        		
        		instanceStageParen.addAllInstanceAbstractionModel(
   					 instanceManager.createFromListOfTask(instanceStageParen, stage.gettasks(), systemRequest)
   					 );
        	}
        	
        	if(stage.getstages() !=null && !stage.getstages().isEmpty()) {
        	
        		stage.getstages().parallelStream().forEach(stageInternal->{
        			InstanceAbstractionModel instanceStageInternal = instanceManager.createFromStage(instanceStageParen,stageInternal);
        			
        			if(stageInternal.gettasks() != null && !stageInternal.gettasks().isEmpty()) {
        				instanceStageInternal.addAllInstanceAbstractionModel(
        					 instanceManager.createFromListOfTask(instanceStageInternal, stageInternal.gettasks(), systemRequest)
        					 );
        				instanceStageParen.addInstanceAbstractionModel(instanceStageInternal);
        			}
        		});
        	}
        	instanceProcess.addInstanceAbstractionModel(instanceStageParen);
        });
        InstanceAbstractionModel finalResponse = instanceManager.saveCompliteInstance(instanceProcess);
        
		return  finalResponse;

	}

	
 
}
