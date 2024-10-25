package com.bpm.engine.models;

import java.util.Objects;

public class RulerModel {

    private Long idRuler;
    private String condition;
    private Integer action;
    private String taskCode;
    private Integer taskNumber;

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

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
    
    

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(action, condition, idRuler, taskCode, taskNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RulerModel other = (RulerModel) obj;
		return Objects.equals(action, other.action) && Objects.equals(condition, other.condition)
				&& Objects.equals(idRuler, other.idRuler) && Objects.equals(taskCode, other.taskCode)
				&& Objects.equals(taskNumber, other.taskNumber);
	}

  
}
