
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

import com.bpm.engine.entitys.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bpm.engine.entitys.Assigned;

public interface AssignedRepository extends CrudRepository<Assigned, Long> {

    public Assigned save(Assigned assigned);
    
    public Optional<Assigned> findByName(String name);

    public List<Assigned> findByNameContaining(String name);

    public Optional<Assigned> findByCodeEmployee(String codeEmployee);

    public List<Assigned> findByCodeEmployeeContaining(String codeEmployee);

    public List<Assigned> findByEmployeeRole(Role role);

    public Optional<Assigned> findByCodeEmployeeAndActive(String codeEmployee, Boolean active);
    
    
    @Query("SELECT 1 FROM Assigned a WHERE a.codeEmployee = :codeEmployee")
    public Integer checkCodeEmployeeExists(@Param("codeEmployee") String codeEmployee);
    
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


