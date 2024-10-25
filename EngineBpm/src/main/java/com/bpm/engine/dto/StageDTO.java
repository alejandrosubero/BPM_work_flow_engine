package com.bpm.engine.dto;

import com.bpm.engine.models.RoleModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.models.TaskModel;
import com.bpm.engine.utility.InstanOf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StageDTO implements Serializable {
	
    private static final long serialVersionUID = 2L;
    
    private Long id;
    private String Code;
    private String name;
    private String title;
    private String type;
    private String instanceOf;
    
    @Builder.Default
    private List<StageDTO> stages = new ArrayList<>();
   
    @Builder.Default
    private List<TaskDTO> tasks = new ArrayList<>();
   
    @Builder.Default
    private List<RoleDTO> roles = new ArrayList<>();
    
    

    public StageDTO(StageModel stageModel) {
    
    	if (stageModel.getIdStage() != null)
            this.id = stageModel.getIdStage();

        if (stageModel.getStageCode() != null)
            Code = stageModel.getStageCode();

        if (stageModel.getName() != null)
            this.name = stageModel.getName();

        if (stageModel.getTitle() != null)
            this.title = stageModel.getTitle();

        if (stageModel.getType() != null)
            this.type = stageModel.getType();
        
        this.instanceOf = InstanOf.STAGE.getValue();

//        if (stageModel.getroles() != null && stageModel.getroles().size() > 0)
//            this.roles = this.getRoles(stageModel.getroles());

        if (stageModel.gettasks() != null && stageModel.gettasks().size() > 0)
            this.tasks = this.getTask(stageModel.gettasks());

        
        if (stageModel.getstages() != null && stageModel.getstages().size() > 0) {
            List<StageDTO> stageModelList = new ArrayList<>();
            stageModel.getstages().parallelStream().forEach(stage -> stageModelList.add(new StageDTO(stage, "children")));
//            for (StageModel stage : stageModel.getstages()) {
//                stageModelList.add(new StageDTO(stage, "children"));
//            }
            this.stages = stageModelList;
        }
    }

    
    public StageDTO(StageModel stageModel, String children) {

        if(children != null){
            if (stageModel.getIdStage() != null)
                this.id = stageModel.getIdStage();

            if (stageModel.getStageCode() != null)
                Code = stageModel.getStageCode();

            if (stageModel.getName() != null)
                this.name = stageModel.getName();

            if (stageModel.getTitle() != null)
                this.title = stageModel.getTitle();

            if (stageModel.getType() != null)
                this.type = stageModel.getType();

//            if (stageModel.getroles() != null && stageModel.getroles().size() > 0)
//                this.roles = this.getRoles(stageModel.getroles());

            if (stageModel.gettasks() != null && stageModel.gettasks().size() > 0)
                this.tasks = this.getTask(stageModel.gettasks());

        }
    }
    
    
    private List<TaskDTO> getTask(List<TaskModel> taskModels) {
        List<TaskDTO> dTOList = new ArrayList<>();
        taskModels.parallelStream().forEach(task->  dTOList.add(new TaskDTO(task)));
//        for (TaskModel task : taskModels) {
//            dTOList.add(new TaskDTO(task));
//        }
        return dTOList;
    }

    private List<RoleDTO> getRoles(List<RoleModel> roleModel) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (RoleModel rrol : roleModel) {
            roleDTOList.add(new RoleDTO(rrol));
        }
        return roleDTOList;
    }


}
