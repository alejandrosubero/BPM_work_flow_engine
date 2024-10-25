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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StageValidation {

    @Autowired
    private TaskValidation taskValidationService;

    private String systemMenssageStage = Constants.SYSTEM_STATE_OK + "_Stage";

    public SystemInternalResponseModel validStage(StageModel stage) {
        StageModel stageNew = this.valida(stage);
        boolean aprubeValue = stageNew != null ? true : false;
        SystemInternalResponseModel SystemInternalResponse = new SystemInternalResponseModel(aprubeValue, systemMenssageStage);
        return SystemInternalResponse;
    }

    private Boolean checkNameTitleType(StageModel stage) {
        if (stage.getName() != null &&
                stage.getTitle() != null &&
                stage.getType() != null &&
                stage.getStageNumber() != null) {
            return true;
        } else {
            systemMenssageStage = "Fail during checking_Name_Title_Type in process";
            return false;
        }
    }


    public StageModel valida(StageModel stage) {
        StageModel pojo = null;
        try {
            if (stage != null) {
                if (this.checkNameTitleType(stage) && validStagesChildren(stage) && validRulerStageOrTask(stage) ) {
                    pojo = stage;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    private Boolean validStagesChildren(StageModel stage) {
        boolean response = false;
       
        if (stage.gettasks() != null && stage.gettasks().size() > 0 || stage.getstages() != null && stage.getstages().size() > 0) {

            if (stage.getstages() != null && stage.getstages().size() > 0) {
              
            	for (StageModel stagesChildren : stage.getstages()) {
                
            		if(!stagesChildren.getstages().isEmpty()) {
            			 systemMenssageStage = "Fail valid the Stages Children can't contain another Stage. (Stages Children in Stage validation)... ";
            			return false;
            		}
            		
            		if (stagesChildren.gettasks().size() > 0) {
                        for (TaskModel task : stage.gettasks()) {
                            response = validTask(task);
                        }
                        
                    }
                }
            }

            if (stage.gettasks() != null && stage.gettasks().size() > 0) {
                for (TaskModel task : stage.gettasks()) {
                    response = validTask(task);
                }
            }
        } else {
            systemMenssageStage = "Fail valid Stages Children in Stage validation";
        }
        return response;
    }

    
    private Boolean validRulerStageOrTask(StageModel stage) {
    	
    	Object result = null;
    	
        if (stage.gettasks() == null && stage.getstages() != null &&  !stage.getstages().isEmpty()) {
        	result = "yes";
        } 
        
        if (stage.gettasks() != null && stage.getstages() == null && !stage.gettasks().isEmpty()) {
        	result = "yes";
        } 
        
        if (stage.gettasks() != null && stage.getstages() != null && (!stage.gettasks().isEmpty() && stage.getstages().isEmpty()) || (stage.gettasks().isEmpty() && !stage.getstages().isEmpty())) {
        	result = "yes";
        } 
        if(result == null) {
        	 systemMenssageStage = "Fail valid Ruler only one Stage or Task for Stage ( in Stage validation)... ";
        }
       
        return result != null;
    }
    
    
    
    public boolean validTask(TaskModel task) {
        SystemInternalResponseModel taskValid = taskValidationService.validTask(task);
        systemMenssageStage = taskValid.getMensaje();
        return taskValid.getCondition();
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


