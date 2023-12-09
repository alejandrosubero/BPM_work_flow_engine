package com.bpm.engine.dto;

import com.bpm.engine.model.RoleModel;
import com.bpm.engine.model.StageModel;
import com.bpm.engine.model.TaskModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StageDTO {
    private Long id;
    private String Code;
    private String name;
    private String title;
    private String type;
    private List<StageDTO> stages = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();
    private List<TaskDTO> tasks = new ArrayList<>();

    public StageDTO() {
    }

    public StageDTO(Long id, String code, String name, String title, String type, List<StageDTO> stages, List<RoleDTO> roles, List<TaskDTO> tasks) {
        this.id = id;
        Code = code;
        this.name = name;
        this.title = title;
        this.type = type;
        this.stages = stages;
        this.roles = roles;
        this.tasks = tasks;
    }

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

        if (stageModel.getroles() != null && stageModel.getroles().size() > 0)
            this.roles = this.getRoles(stageModel.getroles());

        if (stageModel.gettasks() != null && stageModel.gettasks().size() > 0)
            this.tasks = this.getTask(stageModel.gettasks());

        if (stageModel.getstages() != null && stageModel.getstages().size() > 0) {
            List<StageDTO> stageModelList = new ArrayList<>();
            for (StageModel stage : stageModel.getstages()) {
                stageModelList.add(new StageDTO(stage, "children"));
            }
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

            if (stageModel.getroles() != null && stageModel.getroles().size() > 0)
                this.roles = this.getRoles(stageModel.getroles());

            if (stageModel.gettasks() != null && stageModel.gettasks().size() > 0)
                this.tasks = this.getTask(stageModel.gettasks());

        }



    }
    private List<TaskDTO> getTask(List<TaskModel> taskModels) {
        List<TaskDTO> dTOList = new ArrayList<>();
        for (TaskModel task : taskModels) {
            dTOList.add(new TaskDTO(task));
        }
        return dTOList;
    }

    private List<RoleDTO> getRoles(List<RoleModel> roleModel) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (RoleModel rrol : roleModel) {
            roleDTOList.add(new RoleDTO(rrol));
        }
        return roleDTOList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StageDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDTO> stages) {
        this.stages = stages;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
