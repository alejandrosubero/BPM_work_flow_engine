
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


package com.bpm.engine.repository;

import java.util.List;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.bpm.engine.entitys.ControlProcessReferent;

public interface ControlProcessReferentRepository extends CrudRepository<ControlProcessReferent, Long> {
    public ControlProcessReferent save(ControlProcessReferent controlProcessReferent);

    public Optional<ControlProcessReferent> findByCode(String code);

    public List<ControlProcessReferent> findByCodeContaining(String code);

    public Optional<ControlProcessReferent> findByName(String name);

    public List<ControlProcessReferent> findByNameContaining(String name);

    public Optional<ControlProcessReferent> findByTitle(String title);

    public List<ControlProcessReferent> findByTitleContaining(String title);

    public Optional<ControlProcessReferent> findByStatus(String status);

    public List<ControlProcessReferent> findByStatusContaining(String status);

    public Optional<ControlProcessReferent> findByType(String type);

    public List<ControlProcessReferent> findByTypeContaining(String type);

    public Optional<ControlProcessReferent> findByIdReference(Long idReference);

    public List<ControlProcessReferent> findByIdReferenceContaining(Long idReference);

    public Optional<ControlProcessReferent> findByActive(Boolean active);

    public List<ControlProcessReferent> findByActiveContaining(Boolean active);

    public Optional<ControlProcessReferent> findByDateCreate(Date dateCreate);

    public List<ControlProcessReferent> findByDateCreateContaining(Date dateCreate);

    public Optional<ControlProcessReferent> findByDateClose(Date dateClose);

    public List<ControlProcessReferent> findByDateCloseContaining(Date dateClose);

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


