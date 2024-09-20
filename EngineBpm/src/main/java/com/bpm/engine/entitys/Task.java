
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_task")
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

    @Column(name = "taskDueTime", updatable = true, nullable = true, length = 200)
    private Integer taskDueTime;

    @Column(name = "procesCode", updatable = true, nullable = true, length = 400)
    private String procesCode;

    @Column(name = "taskUrl", updatable = true, nullable = true, length = 400)
    private String taskUrl;

    @Column(name = "urlService", updatable = true, nullable = true, length = 400)
    private String urlService;

    @Column(name = "code", updatable = true, nullable = true, length = 400)
    private String code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idTaskType")
    private TaskType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idTask")
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idTask")
    @Builder.Default
    private List<Ruler> rulers = new ArrayList<>();
        
    @Column(name = "task_number", updatable = true, nullable = true, length = 200)
    private Integer taskNumber;

    @Column(name = "apprube_Type", updatable = true, nullable = false, length = 100)
    private Integer apprubeType;

    @Column(name = "is_parallel", updatable = true, nullable = true, length = 200)
	 private Boolean isParallel;
    
    @Column(name = "parallel_with_task_Number", updatable = true, nullable = true, length = 200)
	 private Integer parallelWithTaskNumber;
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

