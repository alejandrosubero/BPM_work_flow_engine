
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

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.model.TaskTypeModel;

public class TaskModel {

    private Long idTask;

    private String title;

    private String name;

    private Date dateCreate;

    private Date taskEnd;

    private Integer taskDueTime;

    private String procesCode;

    private String taskUrl;

    private String urlService;

    private String code;

    private TaskTypeModel type;

    private List<RoleModel> roles = new ArrayList<>();

    private List<RulerModel> rulers = new ArrayList<>();


    public TaskModel() {
    }


    public TaskModel(String title, String name, Date dateCreate, Date taskEnd, Integer taskDueTime, String procesCode, String taskUrl, String urlService, String code, TaskTypeModel type, List<RoleModel> roles, List<RulerModel> rulers) {
        this.title = title;
        this.name = name;
        this.dateCreate = dateCreate;
        this.taskEnd = taskEnd;
        this.taskDueTime = taskDueTime;
        this.procesCode = procesCode;
        this.taskUrl = taskUrl;
        this.urlService = urlService;
        this.code = code;
        this.type = type;
        this.roles = roles;
        this.rulers = rulers;
    }



    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(Date taskEnd) {
        this.taskEnd = taskEnd;
    }

    public Integer getTaskDueTime() {
        return taskDueTime;
    }

    public void setTaskDueTime(Integer taskDueTime) {
        this.taskDueTime = taskDueTime;
    }

    public String getProcesCode() {
        return procesCode;
    }

    public void setProcesCode(String procesCode) {
        this.procesCode = procesCode;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public String getUrlService() {
        return urlService;
    }

    public void setUrlService(String urlService) {
        this.urlService = urlService;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TaskTypeModel getType() {
        return type;
    }

    public void setType(TaskTypeModel type) {
        this.type = type;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public List<RulerModel> getRulers() {
        return rulers;
    }

    public void setRulers(List<RulerModel> rulers) {
        this.rulers = rulers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        return Objects.equals(idTask, taskModel.idTask) && Objects.equals(title, taskModel.title) && Objects.equals(name, taskModel.name) && Objects.equals(dateCreate, taskModel.dateCreate) && Objects.equals(taskEnd, taskModel.taskEnd) && Objects.equals(taskDueTime, taskModel.taskDueTime) && Objects.equals(procesCode, taskModel.procesCode) && Objects.equals(taskUrl, taskModel.taskUrl) && Objects.equals(urlService, taskModel.urlService) && Objects.equals(code, taskModel.code) && Objects.equals(type, taskModel.type) && Objects.equals(roles, taskModel.roles) && Objects.equals(rulers, taskModel.rulers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, title, name, dateCreate, taskEnd, taskDueTime, procesCode, taskUrl, urlService, code, type, roles, rulers);
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

