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

import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.models.*;
import com.bpm.engine.utility.Constants;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProcessValidation {

    @Autowired
    private StageValidation stageValidationService;

    private String systemMenssageProsecess = Constants.SYSTEM_STATE_OK+ "_Process";

    public SystemInternalResponseModel isValid(ProcessModel process) {
        ProcessModel processValid = this.valida(process);
        Boolean isValid = processValid != null ? true : false;
        SystemInternalResponseModel SystemInternalResponse = new SystemInternalResponseModel(isValid, systemMenssageProsecess);
        return SystemInternalResponse;
    }

    public ProcessModel valida(ProcessModel process) {
        ProcessModel pojo = null;
        try {
            if (process != null) {
                if (this.checkNameTitleUserCreate(process) &&
                                this.validVisibility(process) &&
                                this.validationStagesTraksContent(process)) {
                    pojo = process;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }


    private Boolean checkNameTitleUserCreate(ProcessModel process) {
        if (process.getName() != null &&
                process.getProcesTitle() != null &&
                process.getUserCreate() != null) {
            return true;
        } else {
            systemMenssageProsecess = "Fail during checking_Name_Title_User_Create in process";
            return false;
        }
    }

    /***
     *
     * @param process // Dont' create process without visibility behavior and the list of roles need to be != null
     * @return
     */
    private Boolean validVisibility(ProcessModel process) {

        if (process.getGlobal() != null && process.getVisible() != null && process.getRoles() != null) {

            if (!process.getGlobal() && process.getVisible() && process.getRoles().size() > 0) {
                return true;
            }

            if (process.getGlobal() && process.getVisible() && process.getRoles().size() == 0) {
                return true;
            }

            if (process.getGlobal() && process.getVisible() && process.getRoles().size() > 0) {
                return true;
            }


            if (process.getGlobal() && !process.getVisible() && process.getRoles().size() == 0) {
                systemMenssageProsecess = "Fail during valid Visibility in process (0 Roles, !Visible)";
                return false;
            }

            if (!process.getGlobal() && process.getVisible() && process.getRoles().size() == 0) {
                systemMenssageProsecess = "Fail during valid Visibility in process (0 Roles, !Global)";
                return false;
            }

            if (!process.getGlobal() && !process.getVisible() && process.getRoles().size() > 0) {
                systemMenssageProsecess = "Fail during valid Visibility in process (!Visible, !Global) ";
                return false;
            }

            if (process.getGlobal() && !process.getVisible() && process.getRoles().size() > 0) {
                systemMenssageProsecess = "Fail during valid Visibility in process (!Visible)";
                return false;
            }
// TODO: ANALIZAR ESTE CASO POR QUE EXISTE
//            if (process.getGlobal() && process.getVisible() && process.getRoles().size() > 0) {
//                systemMenssageProsecess = "Fail during valid Visibility in process \"(The process cant' be Visible and Global in the same time)\" ";
//                return false;
//            }

        } else {
            systemMenssageProsecess = "Fail during valid Visibility general in process";
            return false;
        }
        return false;
    }

    private boolean validationStagesTraksContent(ProcessModel process) {
        boolean aprubeValue = false;
        if (process.getstages() != null && process.getstages().size() > 0) {
            List<Boolean> verificList = new ArrayList<>();
            for (StageModel stage : process.getstages()) {
                SystemInternalResponseModel response = stageValidationService.validStage(stage);
                systemMenssageProsecess = response.getMensaje();
                verificList.add(response.getCondition());
            }
            aprubeValue = verificList.contains(false) ? false : true;
        } else {
            aprubeValue = false;
        }
        return aprubeValue;
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


