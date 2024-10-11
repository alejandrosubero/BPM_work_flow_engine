package com.bpm.engine.models;


import java.util.Date;


public class TaskAssignedModel {

    private Long idTaskAssigned;
    private Long taskId;
    private Long idBpmAssigned;
    private String actionExecuted;
    private Date dateExecuted;
    private String stateOfTask;
    
  
	public TaskAssignedModel() {
	}
	
	
	
	public TaskAssignedModel(Long idBpmAssigned) {
		super();
		this.idBpmAssigned = idBpmAssigned;
	}



	public Long getIdTaskAssigned() {
		return idTaskAssigned;
	}
	public void setIdTaskAssigned(Long idTaskAssigned) {
		this.idTaskAssigned = idTaskAssigned;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getIdBpmAssigned() {
		return idBpmAssigned;
	}
	public void setIdBpmAssigned(Long idBpmAssigned) {
		this.idBpmAssigned = idBpmAssigned;
	}
	public String getActionExecuted() {
		return actionExecuted;
	}
	public void setActionExecuted(String actionExecuted) {
		this.actionExecuted = actionExecuted;
	}
	public Date getDateExecuted() {
		return dateExecuted;
	}
	public void setDateExecuted(Date dateExecuted) {
		this.dateExecuted = dateExecuted;
	}
	public String getStateOfTask() {
		return stateOfTask;
	}
	public void setStateOfTask(String stateOfTask) {
		this.stateOfTask = stateOfTask;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionExecuted == null) ? 0 : actionExecuted.hashCode());
		result = prime * result + ((dateExecuted == null) ? 0 : dateExecuted.hashCode());
		result = prime * result + ((idBpmAssigned == null) ? 0 : idBpmAssigned.hashCode());
		result = prime * result + ((idTaskAssigned == null) ? 0 : idTaskAssigned.hashCode());
		result = prime * result + ((stateOfTask == null) ? 0 : stateOfTask.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskAssignedModel other = (TaskAssignedModel) obj;
		if (actionExecuted == null) {
			if (other.actionExecuted != null)
				return false;
		} else if (!actionExecuted.equals(other.actionExecuted))
			return false;
		if (dateExecuted == null) {
			if (other.dateExecuted != null)
				return false;
		} else if (!dateExecuted.equals(other.dateExecuted))
			return false;
		if (idBpmAssigned == null) {
			if (other.idBpmAssigned != null)
				return false;
		} else if (!idBpmAssigned.equals(other.idBpmAssigned))
			return false;
		if (idTaskAssigned == null) {
			if (other.idTaskAssigned != null)
				return false;
		} else if (!idTaskAssigned.equals(other.idTaskAssigned))
			return false;
		if (stateOfTask == null) {
			if (other.stateOfTask != null)
				return false;
		} else if (!stateOfTask.equals(other.stateOfTask))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

   
}
