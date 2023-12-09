
/*
Create on Sat Sep 30 10:44:39 EDT 2023
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

import java.util.Date;
import java.util.Objects;


public class ControlProcessReferentModel {
    private Long id;
    private String code;
    private String name;
    private String title;
    private String status;
    private String type;
    private Long idReference;
    private Boolean active;
    private Date dateCreate;
    private Date dateClose;

    public ControlProcessReferentModel() {
    }

    public ControlProcessReferentModel(String code, String name, String title, String status, String type, Long idReference) {
       if(null != code){
           this.code = code;
       }
        if(null != name){
            this.name = name;
        }
        if(null != title){
            this.title = title;
        }
        if(null != status){
            this.status = status;
        }
        if(null != type){
            this.type = type;
        }
        if(null != idReference){
            this.idReference = idReference;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdReference() {
        return idReference;
    }

    public void setIdReference(Long idReference) {
        this.idReference = idReference;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlProcessReferentModel controlprocessmodel = (ControlProcessReferentModel) o;
        return Objects.equals(id, controlprocessmodel.id) ||
                Objects.equals(code, controlprocessmodel.code) ||
                Objects.equals(name, controlprocessmodel.name) ||
                Objects.equals(title, controlprocessmodel.title) ||
                Objects.equals(status, controlprocessmodel.status) ||
                Objects.equals(type, controlprocessmodel.type) ||
                Objects.equals(idReference, controlprocessmodel.idReference) ||
                Objects.equals(active, controlprocessmodel.active) ||
                Objects.equals(dateCreate, controlprocessmodel.dateCreate) ||
                Objects.equals(dateClose, controlprocessmodel.dateClose);

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

