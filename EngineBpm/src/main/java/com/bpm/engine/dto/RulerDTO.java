package com.bpm.engine.dto;

import com.bpm.engine.model.RulerModel;

public class RulerDTO {

    private Long id;
    private String condition;
    private String action;
    private String CodeOFTask;

    public RulerDTO() {
    }

    public RulerDTO(Long id, String condition, String action, String codeOFTask) {
        this.id = id;
        this.condition = condition;
        this.action = action;
        CodeOFTask = codeOFTask;
    }

    public RulerDTO(RulerModel ruler) {
        this.id = ruler.getIdRuler();
        this.condition = ruler.getCondition();
        this.action = ruler.getAction();
        CodeOFTask = ruler.getTaskCode();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeOFTask() {
        return CodeOFTask;
    }

    public void setCodeOFTask(String codeOFTask) {
        CodeOFTask = codeOFTask;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
