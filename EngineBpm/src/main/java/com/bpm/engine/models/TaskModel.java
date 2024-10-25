
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

package com.bpm.engine.models;

//import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskModel {

    private Long idTask;

    private String title;

    private String name;

    private Date dateCreate;

    private Date taskEnd;

    private Integer taskDueTime;

    private String procesCode;

    private String taskUrl;

    private String urlService;

    private String code;

    private TaskTypeModel type;
    
    private Integer taskNumber;
    
    private Integer apprubeType;
    
    private Boolean isParallel;
    
    private Integer parallelWithTaskNumber;

    @Builder.Default
    private List<RoleModel> roles = new ArrayList<>();

    @Builder.Default
    private List<RulerModel> rulers = new ArrayList<>();



   
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

