

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


package com.bpm.engine.mappers;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.RoleModel;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@Component
public class AssignedMapper {

    @Autowired
    private RoleMapper rolemapper;

    public AssignedModel entityToPojo(Assigned entity) {
        ModelMapper modelMapper = new ModelMapper();
        AssignedModel pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, AssignedModel.class);
        }
        return pojo;
    }

    public List<AssignedModel> entityListToPojoList(List<Assigned> entitys) {
        ModelMapper modelMapper = new ModelMapper();
        List<AssignedModel> pojos = new ArrayList<>();

        if (entitys != null && entitys.size()>0 ) {
            entitys.forEach(assigned -> {
                pojos.add(this.entityToPojo(assigned));
            });
        }
        return pojos;
    }


    public Assigned pojoToEntity(AssignedModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Assigned entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Assigned.class);
        }
        return entity;
    }
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


