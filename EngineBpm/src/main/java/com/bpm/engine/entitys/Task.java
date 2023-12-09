
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.bpm.engine.entitys.TaskType;


@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_task_generator")
    @SequenceGenerator(name = "sequence_mat_id_task_generator", initialValue = 100, allocationSize = 2000)
    @Column(name = "idTask", updatable = true, nullable = false, length = 25)
    private Long idTask;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "taskCreate", updatable = true, nullable = true, length = 200)
    private Date dateCreate;

    @Column(name = "taskEnd", updatable = true, nullable = true, length = 200)
    private Date taskEnd;

    @Column(name = "taskDueTime", updatable = true, nullable = true, length = 40)
    private Integer taskDueTime;

    @Column(name = "procesCode", updatable = true, nullable = true, length = 200)
    private String procesCode;

    @Column(name = "taskUrl", updatable = true, nullable = true, length = 200)
    private String taskUrl;

    @Column(name = "urlService", updatable = true, nullable = true, length = 200)
    private String urlService;

    @Column(name = "code", updatable = true, nullable = true, length = 200)
    private String code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idTaskType")
    private TaskType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idTask")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idTask")
    private List<Ruler> rulers = new ArrayList<>();


    public Task() {
    }

    public List<Ruler> getRulerss() {
        return rulers;
    }

    public void setRulerss(List<Ruler> rulerss) {
        this.rulers = rulerss;
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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(idTask, task.idTask) && Objects.equals(title, task.title) && Objects.equals(name, task.name) && Objects.equals(dateCreate, task.dateCreate) && Objects.equals(taskEnd, task.taskEnd) && Objects.equals(taskDueTime, task.taskDueTime) && Objects.equals(procesCode, task.procesCode) && Objects.equals(taskUrl, task.taskUrl) && Objects.equals(urlService, task.urlService) && Objects.equals(code, task.code) && Objects.equals(type, task.type) && Objects.equals(roles, task.roles) && Objects.equals(rulers, task.rulers);
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

