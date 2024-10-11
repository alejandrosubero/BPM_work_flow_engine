
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


package com.bpm.engine.service;

import java.util.Optional;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.entitys.Task;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.models.TaskModel;


public interface TaskService {

    public TaskModel findByTitle(String title);

    public TaskModel findByName(String name);

    public TaskModel findByDateCreate(Date dateCreate);

    public TaskModel findByProcesCode(String procesCode);

    public TaskModel findByTaskUrl(String taskUrl);

    public TaskModel findByUrlService(String urlService);

    public TaskModel findByCode(String code);

    public List<TaskModel> findByTitleContaining(String title);

    public List<TaskModel> findByNameContaining(String name);

    public List<TaskModel> findByDateCreateContaining(Date dateCreate);

    public List<TaskModel> findByProcesCodeContaining(String procesCode);

    public List<TaskModel> findByTaskUrlContaining(String taskUrl);

    public List<TaskModel> findByUrlServiceContaining(String urlService);

    public List<TaskModel> findByCodeContaining(String code);

    public TaskModel findById(Long id);

    public boolean saveTask(Task task);

    public List<TaskModel> getAllTask();

    public boolean updateTask(Task task);

    public boolean saveOrUpdateTask(Task task);

    public List<TaskModel> findByRelacionTaskType(TaskType tasktype);
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


