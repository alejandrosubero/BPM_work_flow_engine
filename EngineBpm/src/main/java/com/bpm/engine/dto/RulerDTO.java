package com.bpm.engine.dto;

import java.io.Serializable;

import com.bpm.engine.models.RulerModel;

public class RulerDTO implements Serializable {
	
    private static final long serialVersionUID = 44L;

    private Long id;
    private String condition;
    private Integer action;
    private String CodeOFTask;

    public RulerDTO() {
    }

    public RulerDTO(Long id, String condition, Integer action, String codeOFTask) {
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

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

}
