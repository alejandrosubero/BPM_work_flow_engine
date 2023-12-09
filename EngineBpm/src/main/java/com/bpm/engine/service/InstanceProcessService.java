
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

import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Assigned;


public interface InstanceProcessService {

    public InstanceProcessModel saveInternal(InstanceProcessModel instanceprocess);
    public boolean saveInstanceProcess(InstanceProcess instanceprocess);

    public List<InstanceProcessModel> getAllInstanceProcess();

    public boolean updateInstanceProcess(InstanceProcess instanceprocess);
    public InstanceProcessModel updateInstanceProcessII(InstanceProcessModel instanceProcessModel);

    public boolean saveOrUpdateInstanceProcess(InstanceProcess instanceprocess);

    public InstanceProcessModel findByName(String name);

    public InstanceProcessModel findByCreateBy(String createBy);

    public InstanceProcessModel findByState(String state);

    public InstanceProcessModel findByDateCreate(Date dateCreate);

    public InstanceProcessModel findByDateEnd(Date dateEnd);

    public InstanceProcessModel findByInstanceCode(String instanceCode);

    public InstanceProcessModel findByTitle(String title);

    public List<InstanceProcessModel> findByNameContaining(String name);

    public List<InstanceProcessModel> findByCreateByContaining(String createBy);

    public List<InstanceProcessModel> findByStateContaining(String state);

    public List<InstanceProcessModel> findByDateCreateContaining(Date dateCreate);

    public List<InstanceProcessModel> findByDateEndContaining(Date dateEnd);

    public List<InstanceProcessModel> findByInstanceCodeContaining(String instanceCode);

    public List<InstanceProcessModel> findByTitleContaining(String title);

    public InstanceProcessModel findById(Long id);


    public List<InstanceProcessModel> findByInstanceStageContaining(InstanceStage instanceStage);

    public List<InstanceProcessModel> findByRelacionProcess(Process process);

    public List<InstanceProcessModel> findByAssignedContaining(Assigned assigned);
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


