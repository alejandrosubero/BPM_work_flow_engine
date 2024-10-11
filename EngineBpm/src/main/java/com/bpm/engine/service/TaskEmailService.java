
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

import java.util.Optional;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.bpm.engine.entitys.TaskEmail;
import com.bpm.engine.models.TaskEmailModel;

public interface TaskEmailService {

	public TaskEmailModel findByEmailFrom(String emailFrom);

	public TaskEmailModel findByNota(String nota);

	public TaskEmailModel findByTempleCode(String templeCode);

	public TaskEmailModel findByEmailTo(String emailTo);

	public TaskEmailModel findByEmailCC(String emailCC);

	public TaskEmailModel findByNumberRepete(Integer numberRepete);

	public TaskEmailModel findByRepete(Boolean repete);

	public List<TaskEmailModel> findByEmailFromContaining(String emailFrom);

	public List<TaskEmailModel> findByNotaContaining(String nota);

	public List<TaskEmailModel> findByTempleCodeContaining(String templeCode);

	public List<TaskEmailModel> findByEmailToContaining(String emailTo);

	public List<TaskEmailModel> findByEmailCCContaining(String emailCC);

	public List<TaskEmailModel> findByNumberRepeteContaining(Integer numberRepete);

	public List<TaskEmailModel> findByRepeteContaining(Boolean repete);

	public TaskEmailModel findById(Long id);

	public boolean saveTaskEmail(TaskEmail taskemail);

	public List<TaskEmailModel> getAllTaskEmail();

	public boolean updateTaskEmail(TaskEmail taskemail);

	public boolean saveOrUpdateTaskEmail(TaskEmail taskemail);

}
/*
 * Copyright (C) 2008 Google Inc. Licensed to the Apache Software Foundation
 * (ASF) under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
