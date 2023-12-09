package com.bpm.engine.model;

import java.util.Objects;

public class RulerModel {

    private Long idRuler;
    private String condition;
    private String action;
    private String taskCode;

    public RulerModel() {
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getIdRuler() {
        return idRuler;
    }

    public void setIdRuler(Long idRuler) {
        this.idRuler = idRuler;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RulerModel that = (RulerModel) o;
        return Objects.equals(idRuler, that.idRuler) && Objects.equals(condition, that.condition) && Objects.equals(action, that.action) && Objects.equals(taskCode, that.taskCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRuler, condition, action, taskCode);
    }
}
