
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

import java.util.Objects;


public class RoleModel {

    private Long idRole;

    private String description;

    private String name;

    private String codeRole;

    public RoleModel() {
    }

    
    
    
    public RoleModel(String description, String name, String codeRole) {
        this.description = description;
        this.name = name;
        this.codeRole = codeRole;
    }
    
    
    

    public RoleModel(Long idRole, String description, String name, String codeRole) {
	    
    	if(idRole != null) {
		this.idRole = idRole;
    	}
		this.description = description;
		this.name = name;
		this.codeRole = codeRole;
	}

    
   
    
    
	public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel rolemodel = (RoleModel) o;
        return Objects.equals(idRole, rolemodel.idRole) ||
                Objects.equals(description, rolemodel.description) ||
                Objects.equals(name, rolemodel.name) ||
                Objects.equals(codeRole, rolemodel.codeRole);

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

