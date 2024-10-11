
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

package com.bpm.engine.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.bpm.engine.entitys.TaskAssigned;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private List<BpmAssignedModel> assignes = new ArrayList<>();

  

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


    public void clean() {
    	this.id = null;
		this.code = null;
		this.name = null;
		this.title = null;
		this.status = null;
		this.type = null;
		this.idReference = null;
		this.active = null;
		this.dateCreate = null;
		this.dateClose = null;
		this.assignes = null;
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

