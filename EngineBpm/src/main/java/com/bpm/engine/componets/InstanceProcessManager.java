package com.bpm.engine.componets;

import static com.bpm.engine.utility.SystemSate.ACTIVE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.service.InstanceProcessService;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.utility.Constants;


//TODO: routed -> AssignedModel-> mach(codeEmployee) and go to -> (List<ApprovedProcessModel> approvedProcess -> ApprovedProcessModel -> processCode or idProcess are granted
//Note: resquest fron front tu the engine.


// this class handled the creation on InstanceProcess and the state of InstanceProcess.
@Service
public class InstanceProcessManager {

    private ProcessService processService;
    private InstanceProcessService instanceProcessService;
    private ControlProcessReferentService controlProcessReferentService;
    private InstanceStageManager instanceStageManager;
    
 
   
    @Autowired
    public InstanceProcessManager(ProcessService processService, InstanceProcessService instanceProcessService,
			ControlProcessReferentService controlProcessReferentService, InstanceStageManager instanceStageManager) {
		super();
		this.processService = processService;
		this.instanceProcessService = instanceProcessService;
		this.controlProcessReferentService = controlProcessReferentService;
		this.instanceStageManager = instanceStageManager;
	}



	public Boolean createInstanceProcess(SystemRequest systemRequest) {
        ProcessModel processRequest = processService.findByProcesCode(systemRequest.getProcessCode());
        InstanceProcessModel instanceProcess =  null;
       
        if (processRequest != null) {
            
        	instanceProcess = instanceProcessService.saveInternal(new InstanceProcessModel(processRequest, systemRequest.getCodeEmployee()));
        	
        	//TODO: IN THIS POINT IS NESESARY UPDATE THE PROCES process.setState(ACTIVE.name()); BECAUSE is in use. 
        	
        	processRequest.setState(ACTIVE.name());
        	this.processService.save(processRequest);
        	
        	instanceProcess.setprocess(processRequest);            
            
            instanceProcess.setinstanceStage(this.instanceStageManager.generate(instanceProcess, systemRequest));
            
       
            ControlProcessReferentModel referentModel =
                    this.controlProcessReferentService.saveOrUpdateInternalControlProcess(
                            new ControlProcessReferentModel(
                            		instanceProcess.getprocess().getProcesCode(), instanceProcess.getName(),instanceProcess.getTitle(),
                                    instanceProcess.getState(), Constants.TYPE_INSTANCE_PROCESS, instanceProcess.getIdInstanceProcess())
                            );

            instanceProcess.setIdControlProcessReferent(referentModel.getIdReference());
           
            instanceProcess = instanceProcessService.updateInstanceProcessII(instanceProcess);
            
         
            List<InstanceStageModel> instancestageModel = instanceProcess.getinstanceStage();
            

            //This part create a ControlProcessReferentService for task into the principal Stage
            
            instancestageModel.forEach(instanceStageModel -> {
                instanceStageModel.getinstancesTasks().forEach(instanceTaskModel -> {
                    this.controlProcessReferentService.saveOrUpdateInternalControlProcess(
                            new ControlProcessReferentModel(
                                    instanceTaskModel.getCodeTask(),
                                    instanceTaskModel.getName(),
                                    instanceTaskModel.getTask().getTitle(),
                                    Constants.TYPE_INSTANCE_TASK,
                                    instanceTaskModel.gettask().getType().getType(),
                                    instanceTaskModel.getIdInstanceTask()));
                });
            });
        }
        
        if(instanceProcess.getIdInstanceProcess() != null){
            return true;
        }else {
            return false;
        }
    }






    
}
