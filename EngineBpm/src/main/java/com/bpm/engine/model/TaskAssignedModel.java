package com.bpm.engine.model;

import javax.persistence.Column;
import java.util.Objects;

public class TaskAssignedModel {

    private Long idTaskAssigned;
    private Long taskId;
    private Long idBpmAssigned;

    public TaskAssignedModel() {
    }

    public TaskAssignedModel(Long idBpmAssigned) {
        this.idBpmAssigned = idBpmAssigned;
    }

    public TaskAssignedModel(Long taskId, Long idBpmAssigned) {
        this.taskId = taskId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskAssignedModel that = (TaskAssignedModel) o;
        return Objects.equals(idTaskAssigned, that.idTaskAssigned) && Objects.equals(taskId, that.taskId) && Objects.equals(idBpmAssigned, that.idBpmAssigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaskAssigned, taskId, idBpmAssigned);
    }
}
