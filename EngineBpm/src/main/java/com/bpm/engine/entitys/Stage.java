
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

import com.bpm.engine.entitys.Stage;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Task;


@Entity
@Table(name = "stage")
public class Stage {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_4a_generator")
    @SequenceGenerator(name = "sequence_mat_id_4a_generator", initialValue = 4, allocationSize = 1000)
    @Column(name = "idStage", updatable = true, nullable = false, length = 25)
    private Long idStage;

    @Column(name = "stageCode", updatable = true, nullable = true, length = 200)
    private String stageCode;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "dateCreate", updatable = true, nullable = true, length = 200)
    private Date dateCreate;

    @Column(name = "type", updatable = true, nullable = true, length = 200)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    private List<Stage> stages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    private List<Role> roles = new ArrayList<>();

    @Column(name = "stageNumber", updatable = true, nullable = false, length = 100)
    private Integer stageNumber;

    public Stage() {
    }

    public Integer getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(Integer stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Long getIdStage() {
        return idStage;
    }

    public void setIdStage(Long idStage) {
        this.idStage = idStage;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Stage> getstages() {
        return stages;
    }

    public void setstages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Role> getroles() {
        return roles;
    }

    public void setroles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Task> gettasks() {
        return tasks;
    }

    public void settasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage stage = (Stage) o;
        return Objects.equals(idStage, stage.idStage) && Objects.equals(stageCode, stage.stageCode) && Objects.equals(name, stage.name) && Objects.equals(title, stage.title) && Objects.equals(dateCreate, stage.dateCreate) && Objects.equals(type, stage.type) && Objects.equals(tasks, stage.tasks) && Objects.equals(stages, stage.stages) && Objects.equals(roles, stage.roles) && Objects.equals(stageNumber, stage.stageNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStage, stageCode, name, title, dateCreate, type, tasks, stages, roles, stageNumber);
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

