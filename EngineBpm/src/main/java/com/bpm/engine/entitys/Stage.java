
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
import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_stage")
public class Stage {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_4a_generator")
    @SequenceGenerator(name = "sequence_mat_id_4a_generator", initialValue = 4, allocationSize = 1000)
    @Column(name = "idStage", updatable = true, nullable = false, length = 25)
    private Long idStage;

    @Column(name = "stageCode", updatable = true, nullable = true, length = 200)
    private String stageCode;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "dateCreate", updatable = true, nullable = true, length = 200)
    private Date dateCreate;

    @Column(name = "type", updatable = true, nullable = true, length = 200)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    @Builder.Default
    private List<Stage> stages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "stage_id")
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    @Column(name = "stageNumber", updatable = true, nullable = false, length = 100)
    private Integer stageNumber;

  
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

