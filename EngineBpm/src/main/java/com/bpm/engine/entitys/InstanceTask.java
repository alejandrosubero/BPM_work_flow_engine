
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
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.model.TaskAssignedModel;


@Entity
@Table(name = "instancetask")
public class InstanceTask {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_instance_task_generator")
    @SequenceGenerator(name = "sequence_mat_id_instance_task_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idInstanceTask", updatable = true, nullable = false, length = 25)
    private Long idInstanceTask;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "processCode", updatable = true, nullable = true, length = 200)
    String processCode;
    @Column(name = "instanceProcessId", updatable = true, nullable = true, length = 200)
    private Long instanceProcessId;

    @Column(name = "instanceProcessCode", updatable = true, nullable = true, length = 200)
    private String instanceProcessCode;

    @Column(name = "codeTask", updatable = true, nullable = true, length = 200)
    private String codeTask;

    @Transient
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idInstanceTask")
    private Task task;

    @Column(name = "dateStart", updatable = true, nullable = true, length = 200)
    private Date dateStart;

    @Column(name = "dateEnd", updatable = true, nullable = true, length = 200)
    private Date dateEnd;

    @Column(name = "taskDueTime", updatable = true, nullable = true, length = 40)
	private Integer taskDueTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "idInstanceTask")
    private List<TaskAssigned> assignes = new ArrayList<>();
//    private List<Assigned> assignes = new ArrayList<>();

    @Column(name = "state", updatable = true, nullable = true, length = 200)
    private String state;

    @Column(name = "controlProcessReferent_Id", updatable = true, nullable = true, length = 200)
    private Long idControlProcessReferent;

	public InstanceTask() {
	}

    public Long getIdControlProcessReferent() {
        return idControlProcessReferent;
    }

    public void setIdControlProcessReferent(Long idControlProcessReferent) {
        this.idControlProcessReferent = idControlProcessReferent;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public Long getIdInstanceTask() {
        return idInstanceTask;
    }

    public void setIdInstanceTask(Long idInstanceTask) {
        this.idInstanceTask = idInstanceTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getInstanceProcessCode() {
        return instanceProcessCode;
    }

    public void setInstanceProcessCode(String instanceProcessCode) {
        this.instanceProcessCode = instanceProcessCode;
    }

    public String getCodeTask() {
        return codeTask;
    }

    public void setCodeTask(String codeTask) {
        this.codeTask = codeTask;
    }

	public Integer getTaskDueTime() {
		return taskDueTime;
	}

	public void setTaskDueTime(Integer taskDueTime) {
		this.taskDueTime = taskDueTime;
	}

	public List<TaskAssigned> getAssignes() {
		return assignes;
	}

	public void setAssignes(List<TaskAssigned> assignes) {
		this.assignes = assignes;
	}


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getInstanceProcessId() {
        return instanceProcessId;
    }

    public void setInstanceProcessId(Long instanceProcessId) {
        this.instanceProcessId = instanceProcessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceTask that = (InstanceTask) o;
        return Objects.equals(idInstanceTask, that.idInstanceTask) && Objects.equals(name, that.name) && Objects.equals(processCode, that.processCode) && Objects.equals(instanceProcessId, that.instanceProcessId) && Objects.equals(instanceProcessCode, that.instanceProcessCode) && Objects.equals(codeTask, that.codeTask) && Objects.equals(task, that.task) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(taskDueTime, that.taskDueTime) && Objects.equals(assignes, that.assignes) && Objects.equals(state, that.state) && Objects.equals(idControlProcessReferent, that.idControlProcessReferent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstanceTask, name, processCode, instanceProcessId, instanceProcessCode, codeTask, task, dateStart, dateEnd, taskDueTime, assignes, state, idControlProcessReferent);
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

