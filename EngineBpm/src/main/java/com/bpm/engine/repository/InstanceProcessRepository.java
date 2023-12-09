
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


package com.bpm.engine.repository;

import java.util.List;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bpm.engine.entitys.InstanceProcess;
import org.springframework.transaction.annotation.Transactional;

public interface InstanceProcessRepository extends CrudRepository<InstanceProcess, Long> {

    public InstanceProcess save(InstanceProcess instanceProcess);
    public Optional<InstanceProcess> findByName(String name);

    public List<InstanceProcess> findByNameContaining(String name);
    public Optional<InstanceProcess> findByCreateBy(String createBy);
    public List<InstanceProcess> findByCreateByContaining(String createBy);

    public Optional<InstanceProcess> findByState(String state);

    public List<InstanceProcess> findByStateContaining(String state);

    public Optional<InstanceProcess> findByDateCreate(Date dateCreate);

    public List<InstanceProcess> findByDateCreateContaining(Date dateCreate);

    public Optional<InstanceProcess> findByDateEnd(Date dateEnd);

    public List<InstanceProcess> findByDateEndContaining(Date dateEnd);
    public Optional<InstanceProcess> findByInstanceCode(String instanceCode);
    public List<InstanceProcess> findByInstanceCodeContaining(String instanceCode);

    public Optional<InstanceProcess> findByTitle(String title);

    public List<InstanceProcess> findByTitleContaining(String title);


//    @Transactional
//    @Modifying
//    @Query("update MailSender u set u.wasSend = ?1 where u.idMailSender = ?2")
//    void update(String wasSend, Long idMailSender);


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


