package com.bpm.engine.models;

import java.util.Objects;

public class InstanceDataInfoModel {

    private Long id;
    private Long instanceID;
    private String instanceTitle;
    private String instanceName;
    private String instanceCode;
    private String instanceUserAssigned;
    private String instanceUserAssignedEmployeeCode;
    private String instanceDescription;
    private String state;
    private Boolean isProcess;
    private String Type;


    public InstanceDataInfoModel() {
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Boolean getProcess() {
        return isProcess;
    }

    public void setProcess(Boolean process) {
        isProcess = process;
    }

    public String getInstanceUserAssignedEmployeeCode() {
        return instanceUserAssignedEmployeeCode;
    }

    public void setInstanceUserAssignedEmployeeCode(String instanceUserAssignedEmployeeCode) {
        this.instanceUserAssignedEmployeeCode = instanceUserAssignedEmployeeCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(Long instanceID) {
        this.instanceID = instanceID;
    }

    public String getInstanceTitle() {
        return instanceTitle;
    }

    public void setInstanceTitle(String instanceTitle) {
        this.instanceTitle = instanceTitle;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getInstanceUserAssigned() {
        return instanceUserAssigned;
    }

    public void setInstanceUserAssigned(String instanceUserAssigned) {
        this.instanceUserAssigned = instanceUserAssigned;
    }

    public String getInstanceDescription() {
        return instanceDescription;
    }

    public void setInstanceDescription(String instanceDescription) {
        this.instanceDescription = instanceDescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceDataInfoModel that = (InstanceDataInfoModel) o;
        return Objects.equals(id, that.id) && Objects.equals(instanceID, that.instanceID) && Objects.equals(instanceTitle, that.instanceTitle) && Objects.equals(instanceName, that.instanceName) && Objects.equals(instanceCode, that.instanceCode) && Objects.equals(instanceUserAssigned, that.instanceUserAssigned) && Objects.equals(instanceUserAssignedEmployeeCode, that.instanceUserAssignedEmployeeCode) && Objects.equals(instanceDescription, that.instanceDescription) && Objects.equals(state, that.state) && Objects.equals(isProcess, that.isProcess) && Objects.equals(Type, that.Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instanceID, instanceTitle, instanceName, instanceCode, instanceUserAssigned, instanceUserAssignedEmployeeCode, instanceDescription, state, isProcess, Type);
    }
}
