
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

package com.bpm.engine.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StageModel {

    private Long idStage;
    private String stageCode;
    private String name;
    private String title;
    private Date dateCreate;
    private String type;
    private String nextStageCode;
    private List<StageModel> stages = new ArrayList<>();
    private List<RoleModel> roles = new ArrayList<>();
    private List<TaskModel> tasks = new ArrayList<>();
    private Integer stageNumber;
    
    public StageModel() {
    }
    
    /***
     * 
     * @return true if stages have elements.
     */
    
    public Boolean stagesIsNoEmpty() {
    	return (this.stages != null && !this.stages.isEmpty())? true:false;
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

    public String getNextStageCode() {
        return nextStageCode;
    }

    public void setNextStageCode(String nextStageCode) {
        this.nextStageCode = nextStageCode;
    }

    public List<StageModel> getstages() {
        return stages;
    }

    public void setstages(List<StageModel> stages) {
        this.stages = stages;
    }

    public List<RoleModel> getroles() {
        return roles;
    }

    public void setroles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public List<TaskModel> gettasks() {
        return tasks;
    }

    public void settasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageModel that = (StageModel) o;
        return Objects.equals(idStage, that.idStage) && Objects.equals(stageCode, that.stageCode) && Objects.equals(name, that.name) && Objects.equals(title, that.title) && Objects.equals(dateCreate, that.dateCreate) && Objects.equals(type, that.type) && Objects.equals(nextStageCode, that.nextStageCode) && Objects.equals(stages, that.stages) && Objects.equals(roles, that.roles) && Objects.equals(tasks, that.tasks) && Objects.equals(stageNumber, that.stageNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStage, stageCode, name, title, dateCreate, type, nextStageCode, stages, roles, tasks, stageNumber);
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

