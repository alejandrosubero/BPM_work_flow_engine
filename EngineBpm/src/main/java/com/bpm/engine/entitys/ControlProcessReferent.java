
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

package com.bpm.engine.entitys;

import javax.persistence.*;

import com.bpm.engine.models.BpmAssignedModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_control_process_referent")
public class ControlProcessReferent {

    @Id
    @GeneratedValue(generator = "sequence_control_id_generator")
    @SequenceGenerator(name = "sequence_control_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "id", updatable = true, nullable = false, length = 25)
    private Long id;

    @Column(name = "code", updatable = true, nullable = true, length = 200)
    private String code;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "status", updatable = true, nullable = true, length = 200)
    private String status;

    @Column(name = "type", updatable = true, nullable = true, length = 200)
    private String type;

    @Column(name = "idReference", updatable = true, nullable = true, length = 200)
    private Long idReference;

    @Column(name = "active", updatable = true, nullable = true, length = 200)
    private Boolean active;

    @Column(name = "dateCreate", updatable = true, nullable = true, length = 200)
    private Date dateCreate;

    @Column(name = "dateClose", updatable = true, nullable = true, length = 200)
    private Date dateClose;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Control_process_referent")    
    private List<BpmAssigned> assignes = new ArrayList<>();
    
    

    public ControlProcessReferent(String code, String name, String title, String status, String type, Long idReference, Boolean active, Date dateCreate) {
        this.code = code;
        this.name = name;
        this.title = title;
        this.status = status;
        this.type = type;
        this.idReference = idReference;
        this.active = active;
        this.dateCreate = dateCreate;
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

