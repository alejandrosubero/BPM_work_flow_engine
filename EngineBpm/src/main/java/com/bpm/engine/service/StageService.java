
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

import com.bpm.engine.entitys.Stage;
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.models.StageModel;


public interface StageService {

    public StageModel findByStageCode(String stageCode);

    public StageModel findByName(String name);

    public StageModel findByTitle(String title);

    public StageModel findByDateCreate(Date dateCreate);

    public StageModel findByType(String type);



    public List<StageModel> findByStageCodeContaining(String stageCode);

    public List<StageModel> findByNameContaining(String name);

    public List<StageModel> findByTitleContaining(String title);

    public List<StageModel> findByDateCreateContaining(Date dateCreate);

    public List<StageModel> findByTypeContaining(String type);


    public StageModel findById(Long id);

    public boolean saveStage(Stage stage);

    public List<StageModel> getAllStage();

    public boolean updateStage(Stage stage);

    public boolean saveOrUpdateStage(Stage stage);

    public List<StageModel> findByStageContaining(Stage stages);

    public List<StageModel> findByRoleContaining(Role roles);

    public List<StageModel> findByTaskContaining(Task tasks);
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


