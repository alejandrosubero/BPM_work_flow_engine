
/*
Create on Sat Sep 16 23:15:49 EDT 2023
*Copyright (C) 123.
@author Alejandro Subero
@author open
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Business Project manameng engine  </p>
*/

package com.bpm.engine.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.entitys.InstanceTask;


@Entity
@Table(name = "instancestage")
public class InstanceStage {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_instance_stage_generator")
    @SequenceGenerator(name = "sequence_mat_id_instance_stage_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "id", updatable = true, nullable = false, length = 25)
    private Long id;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "code", updatable = true, nullable = true, length = 200)
    private String code;

    @Column(name = "dateStart", updatable = true, nullable = true, length = 200)
    private Date dateStart;

    @Column(name = "dateEnd", updatable = true, nullable = true, length = 200)
    private Date dateEnd;

    @Column(name = "procesCode", updatable = true, nullable = true, length = 200)
    private String procesCode;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceStageProcess")
    private List<InstanceStage> instanceStages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceStage")
    private List<Assigned> assigned = new ArrayList<>();

    @Column(name = "instanceProcessId", updatable = true, nullable = true, length = 200)
    private Long instanceProcessId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceStage")
    private List<InstanceTask> instancesTasks = new ArrayList<>();

    @Column(name = "state", updatable = true, nullable = true, length = 200)
    private String state;

    @Column(name = "stageNumber", updatable = true, nullable = false, length = 100)
    private Integer stageNumber;

    public InstanceStage() {
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
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

    public List<InstanceStage> getinstanceStages() {
        return instanceStages;
    }

    public void setinstanceStages(List<InstanceStage> instanceStages) {
        this.instanceStages = instanceStages;
    }

    public List<Assigned> getassigned() {
        return assigned;
    }

    public void setassigned(List<Assigned> assigned) {
        this.assigned = assigned;
    }

    public Long getInstanceProcessId() {
        return instanceProcessId;
    }

    public void setInstanceProcessId(Long instanceProcessId) {
        this.instanceProcessId = instanceProcessId;
    }

    public List<InstanceTask> getinstancesTasks() {
        return instancesTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceStage that = (InstanceStage) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(procesCode, that.procesCode) && Objects.equals(title, that.title) && Objects.equals(instanceStages, that.instanceStages) && Objects.equals(assigned, that.assigned) && Objects.equals(instanceProcessId, that.instanceProcessId) && Objects.equals(instancesTasks, that.instancesTasks) && Objects.equals(state, that.state) && Objects.equals(stageNumber, that.stageNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, dateStart, dateEnd, procesCode, title, instanceStages, assigned, instanceProcessId, instancesTasks, state, stageNumber);
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

