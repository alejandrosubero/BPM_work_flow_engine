
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

package com.bpm.engine.serviceImplement;

import com.bpm.engine.service.TaskTypeService;
import com.bpm.engine.repository.TaskTypeRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.mappers.TaskTypeMapper;
import com.bpm.engine.models.TaskTypeModel;

@Service
public class TaskTypeServiceImplement implements TaskTypeService {

	private static final Logger logger = LogManager.getLogger(TaskTypeServiceImplement.class);

	@Autowired
	private TaskTypeRepository tasktyperepository;

	@Autowired
	private TaskTypeMapper tasktypeMapper;

	@Override
	public TaskTypeModel findByType(String type) {

		logger.info("Starting getTaskType");
		TaskType tasktypeEntity = new TaskType();
		Optional<TaskType> fileOptional1 = tasktyperepository.findByType(type);

		if (fileOptional1.isPresent()) {

			try {
				tasktypeEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return tasktypeMapper.entityToPojo(tasktypeEntity);
	}

	@Override
	public List<TaskTypeModel> getAllTaskType() {
		logger.info("execute> getAllTaskTypeGet allProyect");
		List<TaskTypeModel> listaTaskType = new ArrayList<TaskTypeModel>();
		tasktyperepository.findAll().forEach(tasktype -> listaTaskType.add(tasktypeMapper.entityToPojo(tasktype)));
		return listaTaskType;
	}

	@Override
	public boolean saveTaskType(TaskType tasktype) {
		logger.info("Save Proyect");

		try {
			tasktyperepository.save(tasktype);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateTaskType(TaskType tasktype) {
		logger.info("Update ENTITY");
		boolean clave = false;
		TaskType empre = tasktyperepository.findById(tasktype.getIdTaskType()).get();
		empre = tasktype;

		try {
			tasktyperepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public TaskTypeModel findById(Long id) {
		return tasktypeMapper.entityToPojo(tasktyperepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateTaskType(TaskType tasktype) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<TaskType> fileOptional2 = tasktyperepository.findById(tasktype.getIdTaskType());
		if (fileOptional2.isPresent()) {
			clave = this.updateTaskType(tasktype);
			logger.info(" is update");
		} else {
			clave = this.saveTaskType(tasktype);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<TaskTypeModel> findByTypeContaining(String type) {
		logger.info("Execute Type Containing");
		List<TaskTypeModel> listaTaskType = new ArrayList<TaskTypeModel>();
		List<TaskType> listaSTaskType = tasktyperepository.findByTypeContaining(type);
		listaSTaskType.forEach(tasktypex -> listaTaskType.add(tasktypeMapper.entityToPojo(tasktypex)));
		return listaTaskType;
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

}
