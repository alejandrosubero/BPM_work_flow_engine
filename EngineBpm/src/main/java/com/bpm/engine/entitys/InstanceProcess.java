
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

import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Assigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_instanceprocess")
public class InstanceProcess {

    @Id
    @GeneratedValue(generator = "sequence_mata_id_instance_process_generator")
    @SequenceGenerator(name = "sequence_mata_id_instance_process_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idInstanceProcess", updatable = true, nullable = false, length = 25)
    private Long idInstanceProcess;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "createBy", updatable = true, nullable = true, length = 200)
    private String createBy;

    @Column(name = "state", updatable = true, nullable = true, length = 200)
    private String state;

    @Column(name = "dateCreate", updatable = true, nullable = true, length = 200)
    private Date dateCreate;

    @Column(name = "dateEnd", updatable = true, nullable = true, length = 200)
    private Date dateEnd;

    @Column(name = "instanceCode", updatable = true, nullable = true, length = 200)
    private String instanceCode;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Transient
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idProcess")
	private Process process;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceProcess")
    private List<InstanceStage> instanceStage = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceProcess")
    private List<Assigned> assigned = new ArrayList<>();

    @Column(name = "controlProcessReferent_Id", updatable = true, nullable = true, length = 200)
    private Long idControlProcessReferent;


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

