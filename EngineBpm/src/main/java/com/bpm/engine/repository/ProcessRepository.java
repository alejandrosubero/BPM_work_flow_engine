
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

import java.util.List;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bpm.engine.entitys.Process;

public interface ProcessRepository extends CrudRepository<Process, Long> {

	public Optional<Process> findByName(String name);

	public List<Process> findByNameContaining(String name);

	public Optional<Process> findByCreateDate(Date createDate);

	public List<Process> findByCreateDateContaining(Date createDate);

	public Optional<Process> findByUserCreate(String userCreate);

	public List<Process> findByUserCreateContaining(String userCreate);

	public Optional<Process> findByProcesTitle(String procesTitle);

	public List<Process> findByProcesTitleContaining(String procesTitle);

	public Optional<Process> findByState(String state);

	public List<Process> findByStateContaining(String state);

	public Optional<Process> findByProcesCode(String procesCode);

	public List<Process> findByProcesCodeContaining(String procesCode);

	public Optional<Process> findByVisible(Boolean visible);

	public List<Process> findByVisibleContaining(Boolean visible);

	public Optional<Process> findByGlobal(Boolean global);

	public List<Process> findByGlobalContaining(Boolean global);

	public Optional<Process> findByActivo(Boolean activo);

	public List<Process> findByActivoContaining(Boolean activo);

	public Process save(Process process);

	@Query("SELECT p FROM Process p JOIN p.roles r WHERE r.codeRole = :codeRole")
	public List<Process> findAllByRoleCodeRole(@Param("codeRole") String codeRole);

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
