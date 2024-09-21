package com.bpm.engine.managers;

import static com.bpm.engine.utility.SystemSate.ACTIVE;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.managers.ControlProcessReferentManager;
import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.service.InstanceProcessService;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.serviceImplement.ProcessServiceImplement;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;


@Service
public class InstanceProcessManager {

	private static final Logger logger = LogManager.getLogger(InstanceProcessManager.class);
	
    private ProcessService processService;
    private InstanceProcessService instanceProcessService;
    private InstanceStageManager instanceStageManager;
    private ControlProcessReferentManager controlProcessReferentManager;
    private InstanceManager instanceManager;
   
    @Autowired
    public InstanceProcessManager(ProcessService processService, InstanceProcessService instanceProcessService,
			InstanceStageManager instanceStageManager, ControlProcessReferentManager controlProcessReferentManager,
			InstanceManager instanceManager) {
		
		this.processService = processService;
		this.instanceProcessService = instanceProcessService;
		this.instanceStageManager = instanceStageManager;
		this.controlProcessReferentManager = controlProcessReferentManager;
		this.instanceManager = instanceManager;
	}
    
    


	public InstanceProcessModel createInstanceProcess(SystemRequest systemRequest) {
		
        ProcessModel processRequest = processService.findByProcesCode(systemRequest.getProcessCode());
   	 InstanceProcessModel instanceProcess =  new InstanceProcessModel();
       
        if (processRequest != null) {
        
        	instanceProcess = instanceProcessService.saveInternal(new InstanceProcessModel(processRequest, systemRequest.getCodeEmployee()));

        	processRequest.setState(ACTIVE.name());
        	//TODO: FALTA ACTUALIZAR EL STATUS EN EL CONTROLPROCESSREFERENT.. REQUIERE METODO UPDATE
        	this.processService.save(processRequest);
        	
        	instanceProcess.setprocess(processRequest);            
            
            instanceProcess.setinstanceStage(this.instanceStageManager.generate(instanceProcess, systemRequest));
           

            instanceProcess.setIdControlProcessReferent(controlProcessReferentManager.createFromInstanceProcess(instanceProcess).getId());
            instanceProcess = instanceProcessService.updateInstanceProcessII(instanceProcess);
            final Long instanceProccesId = instanceProcess.getIdInstanceProcess();
                    
            instanceProcess.getinstanceStage().forEach(instanceStageModel -> {
            	
            	instanceStageModel.getinstanceStages().forEach(internalInstanceStageModel ->{
            		
            		if (internalInstanceStageModel.getStageNumber() == 1) {
            	
            			 internalInstanceStageModel.setState( SystemSate.ASSIGNED.toString());
            			 
            			 internalInstanceStageModel.getinstancesTasks().forEach(instanceTask -> {
            				 instanceTask.setState(SystemSate.ASSIGNED.toString());
            				 instanceTask.setProcessCode(processRequest.getProcesCode());
            				 
            				 ControlProcessReferentModel instanceReferent = this.controlProcessReferentManager.createFromInstanceTask(instanceTask, systemRequest, instanceProccesId);
            				 
            				 if(instanceReferent!=null && instanceReferent.getId() != null) {
            					 instanceTask.setIdControlProcessReferent(instanceReferent.getId());
            				 }

            			 });
            			
            		 }
            	});
            	
            	if (instanceStageModel.getStageNumber() == 1) {
       		
	       			instanceStageModel.setState( SystemSate.ASSIGNED.toString());
	       			instanceStageModel.getinstancesTasks().forEach(instanceTask -> {
	       				
	       			instanceTask.setState(SystemSate.ASSIGNED.toString());
	       		    instanceTask.setIdControlProcessReferent(
						 controlProcessReferentManager.createFromInstanceTask(instanceTask, systemRequest, instanceProccesId).getId()
						 );
       			});
       		 }
            	
            });
            
            instanceProcessService.saveInternal(instanceProcess);
            
        
        if(instanceProcess != null && instanceProcess.getIdInstanceProcess() != null && instanceProcess.getIdControlProcessReferent() != null){
            return instanceProcess;
        }
    }
		return null;

	}
	
	



   public InstanceAbstractionModel createInstanceProcess2 (SystemRequest systemRequest) {
		
	   logger.info("Started create Instance Process chanel 2.......");
	   
        ProcessModel process = processService.findByProcesCode(systemRequest.getProcessCode());
      
        if(process == null) {
        	logger.error("Fail to find a process......");
        	return null;
        }
        
        InstanceAbstractionModel instanceProcess = instanceManager.createFromProcess(process);
        
    
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
