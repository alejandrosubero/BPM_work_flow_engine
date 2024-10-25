package com.bpm.engine.dto;

import com.bpm.engine.models.RoleModel;
import com.bpm.engine.models.RulerModel;
import com.bpm.engine.models.TaskModel;
import com.bpm.engine.models.TaskTypeModel;
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
public class TaskDTO implements Serializable {
	
    private static final long serialVersionUID = 3L;

    private Long id;

    private String title;

    private String name;

    private String codeProcess;

    private String url;

    private String serviceUrl;

    private String codeOfTask;

    private TaskTypeModel type;

    private String userCode;
    
    private String status;
    
    private String response;
    
    private String instanceOf;
    
    
    private List<RoleDTO> roles = new ArrayList<>();

    private List<RulerDTO> rulers = new ArrayList<>();

 

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

        this.instanceOf = InstanOf.TASK.getValue();
        
        if(taskModel.getRoles().size() >0)
        this.roles = this.getRoles(taskModel.getRoles());

//        if(taskModel.getRulers().size()>0)
//        this.rulers = this.getRules(taskModel.getRulers());
    }
    
    
       

    public TaskDTO(Long id, String title, String name, String codeProcess, String url, String serviceUrl, String codeOfTask, TaskTypeModel type) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.codeProcess = codeProcess;
        this.url = url;
        this.serviceUrl = serviceUrl;
        this.codeOfTask = codeOfTask;
        this.type = type;
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


   
}
