package com.bpm.engine.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ProcessModel;


@Service
public class InstanceProcessManager {

	private static final Logger logger = LogManager.getLogger(InstanceProcessManager.class);
	

  private ProcessAndInstanceFacade services;
  
    
   
    @Autowired
	public InstanceProcessManager(ProcessAndInstanceFacade services) {
		this.services = services;
	}
        
  

	public EntityRespone createProcess(ProcessModel processModel) {
    	return services.processManager().createProcess(processModel);
    }
    
    
   
   public InstanceAbstractionModel createInstanceProcess2 (SystemRequest systemRequest) {
		
	   logger.info("Started create Instance Process ...");
	   
        ProcessModel process = services.processManager().findByProcesCode(systemRequest.getProcessCode());
      
        if(process == null) {
        	logger.error("Fail to find a process......");
        	return null;
        }
        
        InstanceAbstractionModel instanceProcess = services.instanceManager().createFromProcess(process, systemRequest.getCodeEmployee());
       
        process.getstages().parallelStream().forEach(stage->{
        	InstanceAbstractionModel instanceStageParen = services.instanceManager().createFromStage(instanceProcess,stage);
        	
        	if(stage.gettasks() != null && !stage.gettasks().isEmpty()) {
        		
        		instanceStageParen.addAllInstanceAbstractionModel(
        				services.instanceManager().createFromListOfTask(instanceStageParen, stage.gettasks(), systemRequest)
   					 );
        	}
        	
        	if(stage.getstages() !=null && !stage.getstages().isEmpty()) {
        	
        		stage.getstages().parallelStream().forEach(stageInternal->{
        			InstanceAbstractionModel instanceStageInternal = services.instanceManager().createFromStage(instanceStageParen,stageInternal);
        			
        			if(stageInternal.gettasks() != null && !stageInternal.gettasks().isEmpty()) {
        				instanceStageInternal.addAllInstanceAbstractionModel(
        						services.instanceManager().createFromListOfTask(instanceStageInternal, stageInternal.gettasks(), systemRequest)
        					 );
        				instanceStageParen.addInstanceAbstractionModel(instanceStageInternal);
        			}
        		});
        	}
        	instanceProcess.addInstanceAbstractionModel(instanceStageParen);
        });
        InstanceAbstractionModel finalResponse = services.instanceManager().saveCompliteInstance(instanceProcess);
        
		return  finalResponse;

	}

	
 
}
