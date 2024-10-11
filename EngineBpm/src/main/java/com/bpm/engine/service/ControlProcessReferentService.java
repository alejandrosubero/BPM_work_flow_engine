
/*
Create on Sat Sep 30 10:44:39 EDT 2023
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

import com.bpm.engine.entitys.ControlProcessReferent;
import com.bpm.engine.models.ControlProcessReferentModel;


public interface ControlProcessReferentService {

    public ControlProcessReferentModel saveOrUpdateInternalControlProcess(ControlProcessReferentModel controlprocess);
    public ControlProcessReferentModel findByCode(String code);

    public ControlProcessReferentModel findByName(String name);

    public ControlProcessReferentModel findByTitle(String title);

    public ControlProcessReferentModel findByStatus(String status);

    public ControlProcessReferentModel findByType(String type);

    public ControlProcessReferentModel findByIdReference(Long idReference);

    public ControlProcessReferentModel findByActive(Boolean active);

    public ControlProcessReferentModel findByDateCreate(Date dateCreate);

    public ControlProcessReferentModel findByDateClose(Date dateClose);

    public List<ControlProcessReferentModel> findByCodeContaining(String code);

    public List<ControlProcessReferentModel> findByNameContaining(String name);

    public List<ControlProcessReferentModel> findByTitleContaining(String title);

    public List<ControlProcessReferentModel> findByStatusContaining(String status);

    public List<ControlProcessReferentModel> findByTypeContaining(String type);

    public List<ControlProcessReferentModel> findByIdReferenceContaining(Long idReference);

    public List<ControlProcessReferentModel> findByActiveContaining(Boolean active);

    public List<ControlProcessReferentModel> findByDateCreateContaining(Date dateCreate);

    public List<ControlProcessReferentModel> findByDateCloseContaining(Date dateClose);

    public ControlProcessReferentModel findById(Long id);

    public boolean saveControlProcess(ControlProcessReferent controlprocess);

    public List<ControlProcessReferentModel> getAllControlProcess();

    public boolean updateControlProcess(ControlProcessReferent controlprocess);

    public boolean saveOrUpdateControlProcess(ControlProcessReferent controlprocess);

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


