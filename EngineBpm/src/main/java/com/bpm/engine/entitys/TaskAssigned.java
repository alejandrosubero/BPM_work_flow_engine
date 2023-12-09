package com.bpm.engine.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TaskAssigned {

    @Id
    @GeneratedValue(generator = "sequence_TaskAssigned_generator")
    @SequenceGenerator(name = "sequence_TaskAssigned_generator", initialValue = 0, allocationSize = 2000)
    @Column(name = "idTaskAssigned", updatable = true, nullable = false, length = 25)
    private Long idTaskAssigned;

    @Column(name = "idBpmAssigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "taskId", updatable = true, nullable = false, length = 200)
    private Long taskId;


    public TaskAssigned() {
    }

    public Long getIdTaskAssigned() {
        return idTaskAssigned;
    }

    public void setIdTaskAssigned(Long idTaskAssigned) {
        this.idTaskAssigned = idTaskAssigned;
    }

    public Long getIdBpmAssigned() {
        return idBpmAssigned;
    }

    public void setIdBpmAssigned(Long idBpmAssigned) {
        this.idBpmAssigned = idBpmAssigned;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskAssigned that = (TaskAssigned) o;
        return Objects.equals(idTaskAssigned, that.idTaskAssigned) && Objects.equals(idBpmAssigned, that.idBpmAssigned) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaskAssigned, idBpmAssigned, taskId);
    }
}
