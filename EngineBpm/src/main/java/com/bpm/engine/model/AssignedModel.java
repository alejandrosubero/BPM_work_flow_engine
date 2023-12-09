
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
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.model.RoleModel;

public class AssignedModel {

    private Long id;
    private String name;
    private String codeEmployee;
    private RoleModel employeeRole;
    private String mail;
    private List<ApprovedProcessModel> approvedProcess = new ArrayList<>();
    private Boolean active;

    public AssignedModel() {
    }

    public AssignedModel(String name, String codeEmployee, RoleModel employeeRole, String mail, List<ApprovedProcessModel> approvedProcess) {

        if(null != name){
            this.name = name;
        }
        if(null !=codeEmployee){
            this.codeEmployee = codeEmployee;
        }
        if(null !=employeeRole){
            this.employeeRole = employeeRole;
        }
        if(null !=mail){
            this.mail = mail;
        }
        if(null !=approvedProcess){
            this.approvedProcess = approvedProcess;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(String codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public RoleModel getemployeeRole() {
        return employeeRole;
    }

    public void setemployeeRole(RoleModel employeeRole) {
        this.employeeRole = employeeRole;
    }

    public List<ApprovedProcessModel> getApprovedProcess() {
        return approvedProcess;
    }

    public void setApprovedProcess(List<ApprovedProcessModel> approvedProcess) {
        this.approvedProcess = approvedProcess;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignedModel that = (AssignedModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(codeEmployee, that.codeEmployee) && Objects.equals(employeeRole, that.employeeRole) && Objects.equals(mail, that.mail) && Objects.equals(approvedProcess, that.approvedProcess) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, codeEmployee, employeeRole, mail, approvedProcess, active);
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

