package com.bpm.engine.model;


import java.util.Objects;

public class BpmAssignedModel {

    private Long idBpmAssigned;
    private Long idAssigned;
    private String taskCode;
    private Long instanciaProccesId;

    public BpmAssignedModel() {
    }

    public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId) {
        if(null!=idAssigned){
            this.idAssigned = idAssigned;
        }
        if(null!=taskCode ){
            this.taskCode = taskCode;
        }
        if(null!=instanciaProccesId){
            this.instanciaProccesId = instanciaProccesId;
        }
    }

    public BpmAssignedModel(Long idAssigned, String taskCode) {
        this.idAssigned = idAssigned;
        this.taskCode = taskCode;
    }

    public void setBpmAssignedModel(BpmAssignedModel assignedBpm) {
        if (null != assignedBpm.getIdAssigned())
            this.idAssigned = assignedBpm.getIdAssigned();

        if (null != assignedBpm.getTaskCode())
            this.taskCode = assignedBpm.getTaskCode();

        if (null != assignedBpm.getInstanciaProccesId())
            this.instanciaProccesId = assignedBpm.getInstanciaProccesId();

        this.idBpmAssigned = this.idBpmAssigned;
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
        BpmAssignedModel that = (BpmAssignedModel) o;
        return Objects.equals(idBpmAssigned, that.idBpmAssigned) && Objects.equals(idAssigned, that.idAssigned) && Objects.equals(taskCode, that.taskCode) && Objects.equals(instanciaProccesId, that.instanciaProccesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBpmAssigned, idAssigned, taskCode, instanciaProccesId);
    }
}
