package com.bpm.engine.componets;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.InstanceStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstanceStageManager {

    private InstanceStageService stageService;
    private TaskManager taskManager;

    @Autowired
    public InstanceStageManager(InstanceStageService stageService, TaskManager taskManager) {
        this.stageService = stageService;
        this.taskManager = taskManager;
    }


    public InstanceStageModel generate(InstanceProcessModel instanceProcess, SystemRequest systemRequest) {

        Long instanceProccesId = instanceProcess.getIdInstanceProcess();
        ProcessModel processRequest = instanceProcess.getprocess();
        List<InstanceStageModel> stageModelList = new ArrayList<>();

        if (instanceProccesId != null && null != processRequest.getProcesCode() && null != processRequest.getstages() && processRequest.getstages().size() > 0) {


            processRequest.getstages().stream().forEach(stageModel -> {

                //this point is create the instance stage father
                InstanceStageModel   instanceStage = new InstanceStageModel(stageModel, processRequest.getProcesCode());

                //this point evaluate the stage internal...
                if (null != stageModel.getstages() && stageModel.getstages().size() > 0 && null != processRequest.getProcesCode()) {

                    stageModel.getstages().stream().forEach(internalsStageModels -> {

                        //TODO: THIS POINT WORK WITH INSTANCES STAGE INTERNAL OF STAGE
                        InstanceStageModel internalInstanceStage = new InstanceStageModel(internalsStageModels, processRequest.getProcesCode());

                        if (internalsStageModels.gettasks().size() > 0) {

                            internalInstanceStage.setinstancesTasks(this.taskManager.setTask(stageModel, systemRequest, instanceProccesId));
                            internalInstanceStage.setInstanceProcessId(instanceProccesId);

                            //TODO: THIS POINT ADD TO STAGE FATHER
                            instanceStage.getinstanceStages().add(internalInstanceStage);

                        }
                    });
                }

                //this point set task of the stage...
                if (stageModel.gettasks().size() > 0) {
                    instanceStage.setinstancesTasks(this.taskManager.setTask(stageModel, systemRequest, instanceProccesId));
                }
                instanceStage.setInstanceProcessId(instanceProccesId);
                stageModelList.add(instanceStage);

            });
            return null;
        }

        return null;
}
}