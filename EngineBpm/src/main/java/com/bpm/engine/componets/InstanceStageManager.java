package com.bpm.engine.componets;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.service.InstanceStageService;
import com.bpm.engine.utility.SystemSate;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstanceStageManager {

    private TaskManager taskManager;

    @Autowired
    public InstanceStageManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }


    public  List<InstanceStageModel> generate(InstanceProcessModel instanceProcess, SystemRequest systemRequest) {


    	List<InstanceStageModel> stageModelList = new ArrayList<>();

    	ProcessModel processRequest = instanceProcess.getprocess();

        if (instanceProcess.getIdInstanceProcess() != null && processRequest.codeExitStagesIsNoEmpty()) {

			for (StageModel stageModel :  processRequest.getstages() ) {
				
//			}
			
//            processRequest.getstages().stream().forEach(stageModel -> {

                //this point is create the instance stage father
                InstanceStageModel instanceStage = new InstanceStageModel(stageModel, processRequest.getProcesCode());
                instanceStage.setInstanceProcessId(instanceProcess.getIdInstanceProcess());

                if (stageModel.getStageNumber() == 1) {
                	instanceStage.setState( SystemSate.ASSIGNED.toString());
                }
                
                //this point evaluate the stage internal...
                if (!stageModel.stagesIsNoEmpty()) {

                    stageModel.getstages().forEach(internalsStageModels -> {

                        //TODO: THIS POINT WORK WITH INSTANCES STAGE INTERNAL OF STAGE
                        InstanceStageModel internalInstanceStage = new InstanceStageModel(internalsStageModels, processRequest.getProcesCode());
                        internalInstanceStage.setInstanceProcessId(instanceProcess.getIdInstanceProcess());

                        
                        
                        if (internalsStageModels.gettasks() != null && !internalsStageModels.gettasks().isEmpty()) {
                            internalInstanceStage.setinstancesTasks(this.taskManager.setTask(internalsStageModels, systemRequest, instanceProcess.getIdInstanceProcess()));

                            if (internalsStageModels.getStageNumber() == 1) {
                            	internalInstanceStage.setState( SystemSate.ASSIGNED.toString());
                            }
                            
                            //TODO: THIS POINT ADD TO STAGE FATHER
                            instanceStage.getinstanceStages().add(internalInstanceStage);

                        }
                    });
                    
                    //this point set task of the stage...
                    if (stageModel.gettasks() != null && !stageModel.gettasks().isEmpty()) {
                        instanceStage.setinstancesTasks(this.taskManager.setTask(stageModel, systemRequest, instanceProcess.getIdInstanceProcess()));
                    }
                   
                    stageModelList.add(instanceStage);
                    
                }
            }
//            );
        }

        return stageModelList;
    }
    
    
    
    
}