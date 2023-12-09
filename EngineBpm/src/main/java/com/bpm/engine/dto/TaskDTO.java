package com.bpm.engine.dto;

import com.bpm.engine.model.RoleModel;
import com.bpm.engine.model.RulerModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.model.TaskTypeModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDTO {

    private Long id;

    private String title;

    private String name;

    private String codeProcess;

    private String url;

    private String serviceUrl;

    private String codeOfTask;

    private TaskTypeModel type;

    private List<RoleDTO> roles = new ArrayList<>();

    private List<RulerDTO> rulers = new ArrayList<>();

    public TaskDTO() {
    }

    public TaskDTO(TaskModel taskModel) {

        if(taskModel.getIdTask() != null)
        this.id = taskModel.getIdTask();

        if( taskModel.getTitle() != null)
        this.title = taskModel.getTitle();

        if(taskModel.getName() != null)
        this.name = taskModel.getName();

        if( taskModel.getProcesCode() != null)
        this.codeProcess = taskModel.getProcesCode();

        if(taskModel.getTaskUrl() != null)
        this.url = taskModel.getTaskUrl();

        if( taskModel.getUrlService() != null)
        this.serviceUrl = taskModel.getUrlService();

        if( taskModel.getCode() != null)
        this.codeOfTask = taskModel.getCode();

        if( taskModel.getType() != null)
        this.type = taskModel.getType();


        if(taskModel.getRoles().size() >0)
        this.roles = this.getRoles(taskModel.getRoles());

        if(taskModel.getRulers().size()>0)
        this.rulers = this.getRules(taskModel.getRulers());
    }

    public TaskDTO(Long id, String title, String name, String codeProcess, String url, String serviceUrl, String codeOfTask, TaskTypeModel type, List<RoleDTO> roles, List<RulerDTO> rulers) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.codeProcess = codeProcess;
        this.url = url;
        this.serviceUrl = serviceUrl;
        this.codeOfTask = codeOfTask;
        this.type = type;
        this.roles = roles;
        this.rulers = rulers;
    }


    private List<RoleDTO> getRoles(List<RoleModel> roleModel){
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (RoleModel rrol : roleModel) {
            roleDTOList.add( new RoleDTO(rrol));
        }
        return roleDTOList;
    }

    private List<RulerDTO> getRules(List<RulerModel> ruleModel){
        List<RulerDTO> ruleDTOList = new ArrayList<>();
        for (RulerModel rule : ruleModel) {
            ruleDTOList.add(new RulerDTO(rule));
        }
        return ruleDTOList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeProcess() {
        return codeProcess;
    }

    public void setCodeProcess(String codeProcess) {
        this.codeProcess = codeProcess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getCodeOfTask() {
        return codeOfTask;
    }

    public void setCodeOfTask(String codeOfTask) {
        this.codeOfTask = codeOfTask;
    }

    public TaskTypeModel getType() {
        return type;
    }

    public void setType(TaskTypeModel type) {
        this.type = type;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<RulerDTO> getRulers() {
        return rulers;
    }

    public void setRulers(List<RulerDTO> rulers) {
        this.rulers = rulers;
    }
}
