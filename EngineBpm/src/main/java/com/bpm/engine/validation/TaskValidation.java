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


package com.bpm.engine.validation;

import com.bpm.engine.models.*;
import com.bpm.engine.utility.Constants;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


@Service
public class TaskValidation {

    private String systemMenssageTask = Constants.SYSTEM_STATE_OK + "_Task";

    public SystemInternalResponseModel validTask(TaskModel task) {
        systemMenssageTask = "Fail valid Task in task valid";
        TaskModel stageNew = this.valida(task);
        boolean aprubeValue = stageNew != null ? true : false;
        SystemInternalResponseModel SystemInternalResponse = new SystemInternalResponseModel(aprubeValue, systemMenssageTask);
        return SystemInternalResponse;
    }


    public TaskModel valida(TaskModel task) {
        TaskModel pojo = null;
        try {
            if (task != null) {
                if (task.getTitle() != null &&
                        task.getName() != null &&
                        (task.getTaskUrl() != null || task.getUrlService() != null) &&
                        task.getType() != null &&
                        task.getRulers().size() > 0) {
                    pojo = task;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    // remplace de name of variable for you proyecte
    public Long valida_id(String poder) {
        Long id_Delete = 0l;
        try {
            if (poder != null) {
                if (poder.length() > 0 && !Pattern.matches("[a-zA-Z]+", poder)) {
                    id_Delete = Long.parseLong(poder);
                }
            }
            return id_Delete;
        } catch (Exception e) {
            e.printStackTrace();
            return id_Delete;
        }
    }

    public <T> Object validation(T t) {
        T elemento = null;
        try {
            if (t != null) {
                elemento = t;
            }
            return elemento;
        } catch (Exception e) {
            e.printStackTrace();
            return elemento;
        }
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


