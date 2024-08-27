
/*
Create on Sun Sep 24 00:38:17 EDT 2023
*Copyright (C) 123.
@author Alejandro Subero
@author open
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Business Project Management engine  </p>
*/

package com.bpm.engine.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.utility.SystemSate;

public class InstanceStageModel {

    private Long id;

    private String state;

    private String name;

    private String code;

    private Date dateStart;

    private Date dateEnd;

    private String procesCode;
    private Long instanceProcessId;

    private String title;

    private List<InstanceStageModel> instanceStages = new ArrayList<>();

    private List<AssignedModel> assigned = new ArrayList<>();

    private ProcessModel processModel;

    private List<InstanceTaskModel> instancesTasks = new ArrayList<>();

    private Integer stageNumber;

    public InstanceStageModel() {
    }

    public InstanceStageModel(String state, String name, Date dateStart, Date dateEnd, String procesCode, Long instanceProcessId, String title, List<InstanceStageModel> instanceStages, ProcessModel processModel, List<InstanceTaskModel> instancesTasks) {
        this.state = state;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.procesCode = procesCode;
        this.instanceProcessId = instanceProcessId;
        this.title = title;
        this.instanceStages = instanceStages;
        this.processModel = processModel;
        this.instancesTasks = instancesTasks;
    }


    public InstanceStageModel(StageModel stageModel, String procesCode) {

        this.dateStart = dateStart; // WHERE I RECIVE THIS VALUE 
        
        if(null != procesCode){
            this.procesCode = procesCode;
        }
        
        if(null != stageModel.getName()){
            this.name = stageModel.getName();
        }
        
        if(null != stageModel.getTitle()){
            this.title = stageModel.getTitle();
        }
        
        if(null != stageModel.getStageNumber()){
            this.stageNumber = stageModel.getStageNumber();
        }

        if(this.stageNumber ==1){ // WHAT HAPEN HERE THE STAGE NUNMBER IS FOR SEPARATE THE STAGE OF OTERS ESTAGE OR FOR WORK WITH STATE.
            this.state = SystemSate.ASSIGNED.toString();
        }else {
            this.state = SystemSate.CREATE.toString();
        }
        
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Long getInstanceProcessId() {
        return instanceProcessId;
    }

    public void setInstanceProcessId(Long instanceProcessId) {
        this.instanceProcessId = instanceProcessId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getProcesCode() {
        return procesCode;
    }

    public void setProcesCode(String procesCode) {
        this.procesCode = procesCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InstanceStageModel> getinstanceStages() {
        return instanceStages;
    }

    public void setinstanceStages(List<InstanceStageModel> instanceStages) {
        this.instanceStages = instanceStages;
    }

    public List<AssignedModel> getassigned() {
        return assigned;
    }

    public void setassigned(List<AssignedModel> assigned) {
        this.assigned = assigned;
    }

    public List<InstanceTaskModel> getinstancesTasks() {
        return instancesTasks;
    }

    public void setinstancesTasks(List<InstanceTaskModel> instancesTasks) {
        this.instancesTasks = instancesTasks;
    }

    public ProcessModel getProcessModel() {
        return processModel;
    }

    public void setProcessModel(ProcessModel processModel) {
        this.processModel = processModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceStageModel that = (InstanceStageModel) o;
        return Objects.equals(id, that.id) && Objects.equals(state, that.state) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(procesCode, that.procesCode) && Objects.equals(instanceProcessId, that.instanceProcessId) && Objects.equals(title, that.title) && Objects.equals(instanceStages, that.instanceStages) && Objects.equals(assigned, that.assigned) && Objects.equals(processModel, that.processModel) && Objects.equals(instancesTasks, that.instancesTasks) && Objects.equals(stageNumber, that.stageNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, name, code, dateStart, dateEnd, procesCode, instanceProcessId, title, instanceStages, assigned, processModel, instancesTasks, stageNumber);
    }
}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

