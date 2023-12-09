
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

import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Task;



public interface InstanceTaskService {

    public InstanceTaskModel findByName(String name);

    public InstanceTaskModel findByState(String state);

    public InstanceTaskModel findByDateStart(Date dateStart);

    public InstanceTaskModel findByDateEnd(Date dateEnd);

    public InstanceTaskModel findByProcessCode(String processCode);

    public InstanceTaskModel findByInstanceProcessCode(String instanceProcessCode);

    public InstanceTaskModel findByCodeTask(String codeTask);

    public List<InstanceTaskModel> findByNameContaining(String name);

    public List<InstanceTaskModel> findByStateContaining(String state);

    public List<InstanceTaskModel> findByDateStartContaining(Date dateStart);

    public List<InstanceTaskModel> findByDateEndContaining(Date dateEnd);

    public List<InstanceTaskModel> findByProcessCodeContaining(String processCode);

    public List<InstanceTaskModel> findByInstanceProcessCodeContaining(String instanceProcessCode);

    public List<InstanceTaskModel> findByCodeTaskContaining(String codeTask);

    public InstanceTaskModel findById(Long id);

    public boolean saveInstanceTask(InstanceTask instancetask);

    public List<InstanceTaskModel> getAllInstanceTask();

    public boolean updateInstanceTask(InstanceTask instancetask);

    public boolean saveOrUpdateInstanceTask(InstanceTask instancetask);

    public List<InstanceTaskModel> findByAssignedContaining(Assigned assignes);

    public List<InstanceTaskModel> findByRelacionTask(Task task);

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


