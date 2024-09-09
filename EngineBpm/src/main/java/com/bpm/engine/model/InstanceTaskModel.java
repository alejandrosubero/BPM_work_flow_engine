
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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.entitys.TaskAssigned;
import com.bpm.engine.serviceImplement.BpmAssignedServiceImplement;
import com.bpm.engine.utility.SystemSate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstanceTaskModel {

    private Long idInstanceTask;
    private String name;
    private Long instanceProcessId;
    private TaskModel task;
    private String codeTask;
    private String instanceProcessCode;
    private String processCode;
    private Date dateStart;
    private Date dateEnd;
    private Integer taskDueTime;
    private List<TaskAssignedModel> assignes = new ArrayList<>();
    private String state;
    private Long idControlProcessReferent;

 

    public InstanceTaskModel(String name, Long instanceProcessId, TaskModel task, String codeTask, String instanceProcessCode, String processCode, Date dateStart, Date dateEnd, Integer taskDueTime, List<TaskAssignedModel> assignes, String state) {
        this.name = name;
        this.instanceProcessId = instanceProcessId;
        this.instanceProcessCode = instanceProcessCode;
        this.task = task;
        this.codeTask = codeTask;
        this.processCode = processCode;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.taskDueTime = taskDueTime;
        this.assignes = assignes;
        this.state = state;
    }


    public InstanceTaskModel(TaskModel taskModel) {

        if (taskModel != null){
            this.task = taskModel;
            this.dateStart = new Date();
            this.state = SystemSate.CREATE.toString();

            if(null != taskModel ) {
                this.name = taskModel.getName();
            }

            if(null != taskModel.getTaskDueTime() ) {
                this.taskDueTime = taskModel.getTaskDueTime();
            }

            if(null != taskModel ) {
                this.codeTask = taskModel.getCode();
            }

            if(null != taskModel.getProcesCode() ){
                this.processCode = taskModel.getProcesCode();
            }

            this.setTaskAssigned(this.task.getCode());
        }
    }


//    public InstanceTaskModel(  List<TaskAssignedModel> assignes, TaskModel taskModel,Long instanceProccesId ) {
//        this.set(taskModel);
//       this.assignes = assignes;
//       this.instanceProcessId = instanceProccesId;
//       this.state = SystemSate.ASSIGNED.toString();
//    }


    public InstanceTaskModel(TaskModel taskModel, Long instanceProccesId ) {
        this.set(taskModel);
       this.instanceProcessId = instanceProccesId;
       this.state = SystemSate.CREATE.toString();
    }
    

    // HAY DOS CASOS CUANDO SE PROPORCIONA EL ASIGNADO Y CUANDO HAY QUE IR A BUSCARLO COMO APROBADOR.
    private void setTaskAssigned(String taskCode){
        BpmAssignedServiceImplement bpmAssignedServiceImplement = new BpmAssignedServiceImplement();
        bpmAssignedServiceImplement.findByTaskCode(taskCode).stream().forEach(bpmAssignedModel ->
                this.assignes.add(new TaskAssignedModel(bpmAssignedModel.getIdBpmAssigned())));
    }

    private void set(TaskModel taskModel) {

        if (taskModel != null) {
            this.task = taskModel;
            this.dateStart = new Date();
            

            if (null != taskModel.getName()) {
                this.name =  taskModel.getName();
            }

            if (null != taskModel.getTaskDueTime()) {
                this.taskDueTime = taskModel.getTaskDueTime();
            }

            if (null != taskModel) {
                this.codeTask = taskModel.getCode();
            }

            if (null != taskModel.getProcesCode()) {
                this.processCode = taskModel.getProcesCode();
            }
        }
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

