
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
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.RoleModel;


@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_1_generator")
    @SequenceGenerator(name = "sequence_mat_id_1_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "id_process", updatable = true, nullable = false, length = 200)
    private Long id_process;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "createDate", updatable = true, nullable = true, length = 200)
    private Date createDate;

    @Column(name = "userCreate", updatable = true, nullable = true, length = 200)
    private String userCreate;

    @Column(name = "procesTitle", updatable = true, nullable = true, length = 200)
    private String procesTitle;

    @Column(name = "state", updatable = true, nullable = true, length = 200)
    private String state;

    @Column(name = "procesCode", updatable = true, nullable = true, length = 200)
    private String procesCode;

    @Column(name = "visible", updatable = true, nullable = true, length = 200)
    private Boolean visible;

    @Column(name = "global", updatable = true, nullable = true, length = 200)
    private Boolean global;

    @Column(name = "activo", updatable = true, nullable = true, length = 200)
    private Boolean activo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinColumn(name = "process_id")
	private List<Stage> stages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "process_id")
    private List<Role> roles = new ArrayList<>();

	public Process() {
	}

	public Long getId_process() {
        return id_process;
    }

    public void setId_process(Long id_process) {
        this.id_process = id_process;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getProcesTitle() {
        return procesTitle;
    }

    public void setProcesTitle(String procesTitle) {
        this.procesTitle = procesTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProcesCode() {
        return procesCode;
    }

    public void setProcesCode(String procesCode) {
        this.procesCode = procesCode;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Stage> getstages() {
        return stages;
    }

    public void setstages(List<Stage> stages) {
        this.stages = stages;
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
        Process process = (Process) o;
        return Objects.equals(id_process, process.id_process) && Objects.equals(name, process.name) && Objects.equals(createDate, process.createDate) && Objects.equals(userCreate, process.userCreate) && Objects.equals(procesTitle, process.procesTitle) && Objects.equals(state, process.state) && Objects.equals(procesCode, process.procesCode) && Objects.equals(visible, process.visible) && Objects.equals(global, process.global) && Objects.equals(activo, process.activo) && Objects.equals(stages, process.stages) && Objects.equals(roles, process.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_process, name, createDate, userCreate, procesTitle, state, procesCode, visible, global, activo, stages, roles);
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

