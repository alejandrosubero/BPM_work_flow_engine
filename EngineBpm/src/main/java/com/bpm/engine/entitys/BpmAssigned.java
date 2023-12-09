package com.bpm.engine.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BpmAssigned {

    @Id
    @GeneratedValue(generator = "sequence_bpm_assigned")
    @SequenceGenerator(name = "sequence_bpm_assigned", initialValue = 0, allocationSize = 2000)
    @Column(name = "idBpmAssigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "idAssigned", updatable = true, nullable = true, length = 200)
    private Long idAssigned;

    @Column(name = "taskCode", updatable = true, nullable = true, length = 200)
    private String taskCode;

    @Column(name = "instanciaProccesId", updatable = true, nullable = true, length = 100)
    private Long instanciaProccesId;


    public BpmAssigned() {
    }


    public Long getIdBpmAssigned() {
        return idBpmAssigned;
    }

    public void setIdBpmAssigned(Long idBpmAssigned) {
        this.idBpmAssigned = idBpmAssigned;
    }

    public Long getIdAssigned() {
        return idAssigned;
    }

    public void setIdAssigned(Long idAssigned) {
        this.idAssigned = idAssigned;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getInstanciaProccesId() {
        return instanciaProccesId;
    }

    public void setInstanciaProccesId(Long instanciaProccesId) {
        this.instanciaProccesId = instanciaProccesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BpmAssigned that = (BpmAssigned) o;
        return Objects.equals(idBpmAssigned, that.idBpmAssigned) && Objects.equals(idAssigned, that.idAssigned) && Objects.equals(taskCode, that.taskCode) && Objects.equals(instanciaProccesId, that.instanciaProccesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBpmAssigned, idAssigned, taskCode, instanciaProccesId);
    }
}
