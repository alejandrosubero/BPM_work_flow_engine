
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


package com.bpm.engine.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.models.RoleModel;


public interface RoleService{
 
		public RoleModel  findByDescription(String	description);

		public RoleModel  findByName(String	name);

		public RoleModel  findByCodeRole(String	codeRole);

		public List<RoleModel>  findByDescriptionContaining(String	description);				public List<RoleModel>  findByNameContaining(String	name);				public List<RoleModel>  findByCodeRoleContaining(String	codeRole);				public	RoleModel findById(Long id);
		public boolean saveRole(Role role);
		public List<RoleModel> getAllRole();
		public boolean updateRole(Role	role);
		public boolean saveOrUpdateRole(Role	role);

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


