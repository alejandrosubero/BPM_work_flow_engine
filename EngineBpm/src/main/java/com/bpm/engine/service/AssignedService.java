
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


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.TaskAssignedModel;


public interface AssignedService {

    public boolean saveAssigned(AssignedModel assigned);

    public List<AssignedModel> getAllAssigned();


    public AssignedModel saveOrUpdateAssigned(AssignedModel AssignedModel);
    
//    public AssignedModel save(AssignedModel AssignedModel);

    public AssignedModel findByName(String name);

    public AssignedModel findByCodeEmployee(String codeEmployee);

    public List<AssignedModel> findByNameContaining(String name);

    public List<AssignedModel> findByCodeEmployeeContaining(String codeEmployee);

    public AssignedModel findById(Long id);

    public List<AssignedModel> findByRelacionRole(Role role);

    public AssignedModel findByCodeEmployeeAndActive(String codeEmployee, Boolean active);

    public List<AssignedModel> findByTaskAssignedModel(List<TaskAssignedModel> taskAssignedList);
    
    public Boolean checkCodeEmployeeExists(String codeEmployee);
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


