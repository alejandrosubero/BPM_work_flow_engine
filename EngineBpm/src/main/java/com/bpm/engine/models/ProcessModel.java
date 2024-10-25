
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

public class ProcessModel {
    private Long id_process;

    private String name;

    private Date createDate;

    private String userCreate;

    private String procesTitle;

    private String state;

    private String procesCode;

    private Boolean visible;

    private Boolean global;

    private Boolean activo;

    private List<StageModel> stages = new ArrayList<>();

    private List<RoleModel> roles = new ArrayList<>();


    public ProcessModel() {
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

    public List<StageModel> getstages() {
        return stages;
    }

    public void setstages(List<StageModel> stages) {
        this.stages = stages;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

 
    
    @Override
	public int hashCode() {
		return Objects.hash(activo, createDate, global, id_process, name, procesCode, procesTitle, roles, stages, state,
				userCreate, visible);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessModel other = (ProcessModel) obj;
		return Objects.equals(activo, other.activo) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(global, other.global) && Objects.equals(id_process, other.id_process)
				&& Objects.equals(name, other.name) && Objects.equals(procesCode, other.procesCode)
				&& Objects.equals(procesTitle, other.procesTitle) && Objects.equals(roles, other.roles)
				&& Objects.equals(stages, other.stages) && Objects.equals(state, other.state)
				&& Objects.equals(userCreate, other.userCreate) && Objects.equals(visible, other.visible);
	}


	/***
     * 
     * @return true is proces_code Exit and Stages are No Empty.
     */
    public Boolean codeExitStagesIsNoEmpty() {
    
    	if(this.procesCode != null && this.stages != null && !this.stages.isEmpty()) {
    		return true;
    	}
    	return false;
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

