

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

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.models.StageModel;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@Component
public class ProcessMapper {

    public ProcessModel entityToPojo(Process entity) {
        ModelMapper modelMapper = new ModelMapper();
        ProcessModel pojo = null;
        try {
            if (entity != null) {
                pojo = modelMapper.map(entity, ProcessModel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return pojo;
    }

    public Process pojoToEntity(ProcessModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Process entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Process.class);
        }
        return entity;
    }
    
    
    public List<ProcessModel> entityListToPojoList(List<Process> entitys) {
        List<ProcessModel> pojos = new ArrayList<>();
        if (entitys != null && !entitys.isEmpty() ) {
            entitys.parallelStream().forEach(process -> {
                pojos.add(this.entityToPojo(process));
            });
        }
        return pojos;
    }
    
    
    public List<Process> PojoListToentityList(List<ProcessModel> pojos) {
        List<Process> entitys = new ArrayList<>();
        if (pojos != null && !pojos.isEmpty() ) {
        	pojos.parallelStream().forEach(process -> {
        		entitys.add(this.pojoToEntity(process));
            });
        }
        return entitys;
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


