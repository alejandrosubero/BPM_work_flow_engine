package com.bpm.engine.componets;

import com.bpm.engine.componets.interfaces.TaskManagerI;
import com.bpm.engine.dto.SystemRequest;
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

  
    public  List<InstanceStageModel> generate(InstanceProcessModel instanceProcess, SystemRequest systemRequest) {


    	List<InstanceStageModel> stageModelList = new ArrayList<>();

    	ProcessModel processRequest = instanceProcess.getprocess();

        if (instanceProcess.getIdInstanceProcess() != null && processRequest.codeExitStagesIsNoEmpty()) {

			for (StageModel stageModel :  processRequest.getstages() ) {
				
//			}
			
//            processRequest.getstages().stream().forEach(stageModel -> {

                //this point is create the instance stage father
                InstanceStageModel instanceStage = new InstanceStageModel(stageModel, processRequest.getProcesCode(), instanceProcess.getIdInstanceProcess());

               
                //this point evaluate the stage internal...
                if (!stageModel.stagesIsNoEmpty()) {

                	for (StageModel  internalsStageModels: stageModel.getstages()) {
                		
                	
//                    stageModel.getstages().forEach(internalsStageModels -> {

                        //TODO: THIS POINT WORK WITH INSTANCES STAGE INTERNAL OF STAGE
                        InstanceStageModel instanceInternalStage = new InstanceStageModel(internalsStageModels, processRequest.getProcesCode(), instanceProcess.getIdInstanceProcess());
             
                        if (internalsStageModels.gettasks() != null && !internalsStageModels.gettasks().isEmpty()) {
                        	instanceInternalStage.setinstancesTasks(this.setTask(internalsStageModels, systemRequest, instanceProcess.getIdInstanceProcess()));

                            if (internalsStageModels.getStageNumber() == 1) {
                            	instanceInternalStage.setState( SystemSate.ASSIGNED.toString());
                            }
                            
                            //TODO: THIS POINT ADD TO STAGE FATHER
                            instanceStage.getinstanceStages().add(instanceInternalStage);

                        }
                    }
//                    );
                    
                    //this point set task of the stage...
                    if (stageModel.gettasks() != null && !stageModel.gettasks().isEmpty()) {
                        instanceStage.setinstancesTasks(this.setTask(stageModel, systemRequest, instanceProcess.getIdInstanceProcess()));
                    }
                    
                    
                    if (stageModel.getStageNumber() == 1) {
                    	instanceStage.setState( SystemSate.ASSIGNED.toString());
                    }
                   
                    stageModelList.add(instanceStage);
                    
                }
            }
//            );
        }

        return stageModelList;
    }
    
    
    
    
    public  List<InstanceStageModel> setTaskAssignmentInStage(List<InstanceStageModel> stageModel, SystemRequest systemRequest) {
    	
    	
    	for (InstanceStageModel stage : stageModel) {
    		
    		if(stage.getStageNumber() == 1 && !stage.getinstancesTasks().isEmpty() ) {
    			List<InstanceTaskModel> newListTask = setTaskAssignment(stage.getinstancesTasks(), systemRequest);
    			stage.setinstancesTasks(newListTask);
    		}
    	}
    	return stageModel;
    }
    
    
    public  List<InstanceTaskModel> setTaskAssignment(List<InstanceTaskModel> taskModels, SystemRequest systemRequest) {
    	
    	//TODO: EN ESTE PUNTO HAY QUE LLAMAR AssignmentTaskManager PASANDOLE LA INFORMACION QUE REQUIERE.
    	// HAY QUE SEPARAR DE LA BUSQUEDA DEL ASSIGNED DE BPMASSIGNED PARA UN MEJOR CONTROL 
    	// EL MEJOR PLAN DE ACCION ES TENER UN GESTOR SEPARADO DE LOS DOS MANAGER MENCIONADOS, EL CUAL REALICE EL MERGE DE LOS DOS.
    	// EL SISTEMA ESTA FALLANDO EN EL SALVAR EL BPMASSIGNED.
    	// HAY QUE SEPARAR PENSANDO EN TODOS LOS CASOS QUE SE USAN PARA BUSCAR U SELECCIONAR AL ASSIGNED.
    	// LA COMPLEJIDAD DE TODO RECIDE EN LOS LOOPS PARA LLEGAR A LAS TAREAS Y EN SALVAR LA REFERENCIA ADECUADA EN EL OBJETO REFEERENCIA.
    	// MUY PUNTUAL EL ASSIGNER SE SALVA INDIVIDUALMENTE PARA PODER OBTENER SU REFERENCIA O ID LUEGO EL BPMASSIGNED ESTE SE DEBE DE SALVAR 
    	// CON EL ControlProcessReferent. ALMENOS QUE ME INVENTE SALVAR UNA REFENCIA PERO SERIA UN NUEVO OBJETO.
    	
    	
    	for(InstanceTaskModel task : taskModels) {
    		.systemRequest..
    	}
    	
    	
    	return null;
    }
    
    
}









