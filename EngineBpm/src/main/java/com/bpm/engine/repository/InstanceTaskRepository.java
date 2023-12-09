
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

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.bpm.engine.entitys.InstanceTask;

public interface InstanceTaskRepository extends CrudRepository< InstanceTask, Long> {
 
		public Optional<InstanceTask> findByName(String name);
		public List<InstanceTask> findByNameContaining(String name);
		public Optional<InstanceTask> findByState(String state);
		public List<InstanceTask> findByStateContaining(String state);
		public Optional<InstanceTask> findByDateStart(Date dateStart);
		public List<InstanceTask> findByDateStartContaining(Date dateStart);
		public Optional<InstanceTask> findByDateEnd(Date dateEnd);
		public List<InstanceTask> findByDateEndContaining(Date dateEnd);
		public Optional<InstanceTask> findByTaskDueTime(Integer DueTime);
		public List<InstanceTask> findByTaskDueTimeContaining(Integer DueTime);
		public Optional<InstanceTask> findByProcessCode(String processCode);
		public List<InstanceTask> findByProcessCodeContaining(String processCode);
		public Optional<InstanceTask> findByInstanceProcessCode(String instanceProcessCode);
		public List<InstanceTask> findByInstanceProcessCodeContaining(String instanceProcessCode);
		public Optional<InstanceTask> findByCodeTask(String codeTask);
		public List<InstanceTask> findByCodeTaskContaining(String codeTask);
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


