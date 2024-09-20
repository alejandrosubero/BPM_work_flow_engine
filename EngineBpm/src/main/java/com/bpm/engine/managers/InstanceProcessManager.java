package com.bpm.engine.managers;

import static com.bpm.engine.utility.SystemSate.ACTIVE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.managers.ControlProcessReferentManager;
import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.service.InstanceProcessService;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;


//TODO: routed -> AssignedModel-> mach(codeEmployee) and go to -> (List<ApprovedProcessModel> approvedProcess -> ApprovedProcessModel -> processCode or idProcess are granted
//Note: resquest fron front tu the engine.


// this class handled the creation on InstanceProcess and the state of InstanceProcess.
@Service
public class InstanceProcessManager {

    private ProcessService processService;
    private InstanceProcessService instanceProcessService;
    private InstanceStageManager instanceStageManager;
    private ControlProcessReferentManager controlProcessReferentManager;
 
   
    @Autowired
    public InstanceProcessManager(ProcessService processService, InstanceProcessService instanceProcessService,
			InstanceStageManager instanceStageManager,ControlProcessReferentManager controlProcessReferentManager) {
		this.processService = processService;
		this.instanceProcessService = instanceProcessService;
		this.instanceStageManager = instanceStageManager;
		this.controlProcessReferentManager = controlProcessReferentManager;
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
	
 
}
