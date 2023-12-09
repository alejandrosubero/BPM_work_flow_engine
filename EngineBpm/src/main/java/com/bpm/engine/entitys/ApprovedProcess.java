package com.bpm.engine.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ApprovedProcess")
public class ApprovedProcess {

    @Id
    @GeneratedValue(generator = "sequence_approved_id_1_gene")
    @SequenceGenerator(name = "sequence_approved_id_1_gene", initialValue = 25, allocationSize = 1000)
    @Column(name = "idApprovedProcess", updatable = true, nullable = false, length = 25)
    private Long idApprovedProcess;

    @Column(name = "processCode", updatable = true, nullable = true, length = 200)
    private String processCode;

    @Column(name = "id_process", updatable = true, nullable = true, length = 200)
    private Long idProcess;

    @Column(name = "granted", updatable = true, nullable = true)
    private Boolean granted;

    public ApprovedProcess() {
    }

    public Long getIdApprovedProcess() {
        return idApprovedProcess;
    }

    public void setIdApprovedProcess(Long idApprovedProcess) {
        this.idApprovedProcess = idApprovedProcess;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }

    public Boolean getGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovedProcess that = (ApprovedProcess) o;
        return Objects.equals(idApprovedProcess, that.idApprovedProcess) && Objects.equals(processCode, that.processCode) && Objects.equals(idProcess, that.idProcess) && Objects.equals(granted, that.granted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApprovedProcess, processCode, idProcess, granted);
    }
}
