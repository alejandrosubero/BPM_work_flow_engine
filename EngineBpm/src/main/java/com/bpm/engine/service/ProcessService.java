
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

import java.util.Date;
import java.util.List;

import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.models.ProcessModel;


public interface ProcessService {

    public ProcessModel save(Process process);
    
    public ProcessModel save(ProcessModel process);

    public ProcessModel findByName(String name);

    public ProcessModel findByCreateDate(Date createDate);

    public ProcessModel findByProcesTitle(String procesTitle);

    public ProcessModel findByProcesCode(String procesCode);

    public ProcessModel findByActivo(Boolean activo);

    public List<ProcessModel> findByNameContaining(String name);

    public List<ProcessModel> findByCreateDateContaining(Date createDate);

    public List<ProcessModel> findByUserCreateContaining(String userCreate);

    public List<ProcessModel> findByProcesTitleContaining(String procesTitle);

    public List<ProcessModel> findByStateContaining(String state);

    public List<ProcessModel> findByProcesCodeContaining(String procesCode);

    public List<ProcessModel> findByVisibleContaining(Boolean visible);

    public List<ProcessModel> findByGlobalContaining(Boolean global);

    public List<ProcessModel> findByActivoContaining(Boolean activo);

    public ProcessModel findById(Long id);

    public boolean saveProcess(Process process);

    public List<ProcessModel> getAllProcess();

    public boolean updateProcess(Process process);
    public ProcessModel update(Process process);

    public boolean saveOrUpdateProcess(Process process);

    public List<ProcessModel> findByStageContaining(Stage stages);
    
    public List<ProcessModel> findAllByRoleCodeRole(String codeRole);
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


