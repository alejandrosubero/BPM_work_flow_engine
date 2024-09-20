package com.bpm.engine.managers;

import com.bpm.engine.componets.interfaces.TaskManagerI;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.ControlProcessReferentModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.InstanceTaskModel;
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
public class InstanceStageManager implements TaskManagerI {

	@Autowired
	private ControlProcessReferentManager controlProcessReferentManager;

	public List<InstanceStageModel> generate(InstanceProcessModel instanceProcess, SystemRequest systemRequest) {

		List<InstanceStageModel> stageModelList = new ArrayList<>();

		ProcessModel processRequest = instanceProcess.getprocess();

		if (instanceProcess.getIdInstanceProcess() != null && processRequest.codeExitStagesIsNoEmpty()) {

			for (StageModel stageModel : processRequest.getstages()) {

//			}

//            processRequest.getstages().stream().forEach(stageModel -> {

				// this point is create the instance stage father
				InstanceStageModel instanceStage = new InstanceStageModel(stageModel, processRequest.getProcesCode(),
						instanceProcess.getIdInstanceProcess());

				// this point evaluate the stage internal...
				if (!stageModel.stagesIsNoEmpty()) {

					for (StageModel internalsStageModels : stageModel.getstages()) {

//                    stageModel.getstages().forEach(internalsStageModels -> {

						// TODO: THIS POINT WORK WITH INSTANCES STAGE INTERNAL OF STAGE
						InstanceStageModel instanceInternalStage = new InstanceStageModel(internalsStageModels,
								processRequest.getProcesCode(), instanceProcess.getIdInstanceProcess());

						if (internalsStageModels.gettasks() != null && !internalsStageModels.gettasks().isEmpty()) {
							instanceInternalStage.setinstancesTasks(this.setTask(internalsStageModels, systemRequest,
									instanceProcess.getIdInstanceProcess()));

							if (internalsStageModels.getStageNumber() == 1) {
								instanceInternalStage.setState(SystemSate.ASSIGNED.toString());
							}

							// TODO: THIS POINT ADD TO STAGE FATHER
							instanceStage.getinstanceStages().add(instanceInternalStage);

						}
					}
//                    );

					// this point set task of the stage...
					if (stageModel.gettasks() != null && !stageModel.gettasks().isEmpty()) {
						instanceStage.setinstancesTasks(
								this.setTask(stageModel, systemRequest, instanceProcess.getIdInstanceProcess()));
					}

					if (stageModel.getStageNumber() == 1) {
						instanceStage.setState(SystemSate.ASSIGNED.toString());
					}

					stageModelList.add(instanceStage);

				}
			}
//            );
		}

		return stageModelList;
	}

	public List<InstanceStageModel> setTaskAssignmentInStage(List<InstanceStageModel> stageModel,SystemRequest systemRequest, String processCode, Long instanceProccesId) {

		for (InstanceStageModel stage : stageModel) {

			if (stage.getStageNumber() == 1 && !stage.getinstancesTasks().isEmpty()) {
				List<InstanceTaskModel> newListTask = setTaskAssignment(stage.getinstancesTasks(), systemRequest,processCode, instanceProccesId);
				stage.setinstancesTasks(newListTask);
			}
		}
		return stageModel;
	}

	
	public List<InstanceTaskModel> setTaskAssignment(List<InstanceTaskModel> taskModels, SystemRequest systemRequest,String processCode, Long instanceProccesId) {

		List<InstanceTaskModel> setTaskAssignment = new ArrayList<>();
		
		try {
			for (InstanceTaskModel task : taskModels) {
				ControlProcessReferentModel instanceReferent = null;
				task.setState(SystemSate.ASSIGNED.toString());
				task.setProcessCode(processCode);

				instanceReferent = this.controlProcessReferentManager.createFromInstanceTask(task, systemRequest, instanceProccesId);

				if (instanceReferent != null && instanceReferent.getId() != null) {
					task.setIdControlProcessReferent(instanceReferent.getId());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		

		return taskModels;
	}

}
