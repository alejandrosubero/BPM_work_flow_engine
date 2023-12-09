
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

import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.entitys.InstanceTask;


public interface InstanceStageService {

    public InstanceStageModel findByState(String state);

    public InstanceStageModel findByName(String name);

    public InstanceStageModel findByCode(String code);

    public InstanceStageModel findByDateStart(Date dateStart);

    public InstanceStageModel findByDateEnd(Date dateEnd);

    public InstanceStageModel findByProcesCode(String procesCode);

    public InstanceStageModel findByTitle(String title);

    public List<InstanceStageModel> findByStateContaining(String state);

    public List<InstanceStageModel> findByNameContaining(String name);

    public List<InstanceStageModel> findByCodeContaining(String code);

    public List<InstanceStageModel> findByDateStartContaining(Date dateStart);

    public List<InstanceStageModel> findByDateEndContaining(Date dateEnd);

    public List<InstanceStageModel> findByProcesCodeContaining(String procesCode);


    public List<InstanceStageModel> findByTitleContaining(String title);

    public InstanceStageModel findById(Long id);

    public boolean saveInstanceStage(InstanceStage instancestage);

    public List<InstanceStageModel> getAllInstanceStage();

    public boolean updateInstanceStage(InstanceStage instancestage);

    public boolean saveOrUpdateInstanceStage(InstanceStage instancestage);

    public List<InstanceStageModel> findByInstanceStageContaining(InstanceStage instanceStages);

    public List<InstanceStageModel> findByAssignedContaining(Assigned assigned);


    public List<InstanceStageModel> findByInstanceTaskContaining(InstanceTask instancesTasks);
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


