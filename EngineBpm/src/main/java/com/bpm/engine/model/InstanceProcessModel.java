
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
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.utility.SystemSate;

public class InstanceProcessModel {

    private Long idInstanceProcess;
    private String name;
    private String createBy;
    private String state;
    private Date dateCreate;
    private Date dateEnd;
    private String instanceCode;
    private String title;
    private List<InstanceStageModel> instanceStage = new ArrayList<>();
    private ProcessModel process;
    private List<AssignedModel> assigned = new ArrayList<>();

    private Long idControlProcessReferent;

    public InstanceProcessModel() {
    }

    public InstanceProcessModel(String name, String createBy, String state, Date dateCreate, Date dateEnd, String title, List<InstanceStageModel> instanceStage, ProcessModel process) {
        this.name = name;
        this.createBy = createBy;
        this.state = state;
        this.dateCreate = dateCreate;
        this.dateEnd = dateEnd;
        this.title = title;
        this.instanceStage = instanceStage;
        this.process = process;
    }


    public InstanceProcessModel(ProcessModel process, String user, Map<String, List<String> > assigned) {
        if(null != process ){
            this.process = process;
            this.state = SystemSate.CREATE.toString();
            this.dateCreate = new Date();
            if(process.getName() != null){this.name = name;}
            if(null != user ) {this.createBy = createBy;}
            if(null != process.getProcesTitle() ){this.title = process.getProcesTitle();}

            if(null != process.getstages() && process.getstages().size() > 0){
                process.getstages().stream().forEach(stageModel ->  this.instanceStage.add(new InstanceStageModel(stageModel, process.getProcesCode())));
            }
        }
    }

    public InstanceProcessModel(ProcessModel process, String user) {
        if (null != process) {
            this.process = process;
            this.state = SystemSate.CREATE.toString();
            this.dateCreate = new Date();
            if (process.getName() != null) {
                this.name = name;
            }
            if (null != user) {
                this.createBy = createBy;
            }
            if (null != process.getProcesTitle()) {
                this.title = process.getProcesTitle();
            }

//            if (null != process.getstages() && process.getstages().size() > 0) {
//                process.getstages().stream().forEach(stageModel -> this.instanceStage.add(new InstanceStageModel(stageModel, process.getProcesCode())));
//            }
        }
    }
    public Long getIdInstanceProcess() {
        return idInstanceProcess;
    }

    public void setIdInstanceProcess(Long idInstanceProcess) {
        this.idInstanceProcess = idInstanceProcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InstanceStageModel> getinstanceStage() {
        return instanceStage;
    }

    public void setinstanceStage(List<InstanceStageModel> instanceStage) {
        this.instanceStage = instanceStage;
    }

    public ProcessModel getprocess() {
        return process;
    }

    public void setprocess(ProcessModel process) {
        this.process = process;
    }

    public List<AssignedModel> getassigned() {
        return assigned;
    }

    public void setassigned(List<AssignedModel> assigned) {
        this.assigned = assigned;
    }

    public Long getIdControlProcessReferent() {
        return idControlProcessReferent;
    }

    public void setIdControlProcessReferent(Long idControlProcessReferent) {
        this.idControlProcessReferent = idControlProcessReferent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceProcessModel that = (InstanceProcessModel) o;
        return Objects.equals(idInstanceProcess, that.idInstanceProcess) && Objects.equals(name, that.name) && Objects.equals(createBy, that.createBy) && Objects.equals(state, that.state) && Objects.equals(dateCreate, that.dateCreate) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(instanceCode, that.instanceCode) && Objects.equals(title, that.title) && Objects.equals(instanceStage, that.instanceStage) && Objects.equals(process, that.process) && Objects.equals(assigned, that.assigned) && Objects.equals(idControlProcessReferent, that.idControlProcessReferent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstanceProcess, name, createBy, state, dateCreate, dateEnd, instanceCode, title, instanceStage, process, assigned, idControlProcessReferent);
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

