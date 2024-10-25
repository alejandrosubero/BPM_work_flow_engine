package com.bpm.engine.models;


import java.util.Objects;

public class ApprovedProcessModel {

    private Long idApprovedProcess;
    private String processCode;
    private Long idProcess;
    private Boolean granted;

    public ApprovedProcessModel() {
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
        ApprovedProcessModel that = (ApprovedProcessModel) o;
        return Objects.equals(idApprovedProcess, that.idApprovedProcess) && Objects.equals(processCode, that.processCode) && Objects.equals(idProcess, that.idProcess) && Objects.equals(granted, that.granted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApprovedProcess, processCode, idProcess, granted);
    }

}
