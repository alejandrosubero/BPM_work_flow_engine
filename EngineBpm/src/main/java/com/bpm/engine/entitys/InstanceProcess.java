
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


@Entity
@Table(name = "instanceprocess")
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

	public InstanceProcess() {
	}

	public Long getIdInstanceProcess() {
        return idInstanceProcess;
    }

    public void setIdInstanceProcess(Long idInstanceProcess) {
        this.idInstanceProcess = idInstanceProcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InstanceStage> getinstanceStage() {
        return instanceStage;
    }

    public void setinstanceStage(List<InstanceStage> instanceStage) {
        this.instanceStage = instanceStage;
    }

    public Process getprocess() {
        return process;
    }

    public void setprocess(Process process) {
        this.process = process;
    }

    public List<Assigned> getassigned() {
        return assigned;
    }

    public void setassigned(List<Assigned> assigned) {
        this.assigned = assigned;
    }

    public Long getIdControlProcessReferent() {
        return idControlProcessReferent;
    }

    public void setIdControlProcessReferent(Long idControlProcessReferent) {
        this.idControlProcessReferent = idControlProcessReferent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceProcess that = (InstanceProcess) o;
        return Objects.equals(idInstanceProcess, that.idInstanceProcess) && Objects.equals(name, that.name) && Objects.equals(createBy, that.createBy) && Objects.equals(state, that.state) && Objects.equals(dateCreate, that.dateCreate) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(instanceCode, that.instanceCode) && Objects.equals(title, that.title) && Objects.equals(process, that.process) && Objects.equals(instanceStage, that.instanceStage) && Objects.equals(assigned, that.assigned) && Objects.equals(idControlProcessReferent, that.idControlProcessReferent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstanceProcess, name, createBy, state, dateCreate, dateEnd, instanceCode, title, process, instanceStage, assigned, idControlProcessReferent);
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

