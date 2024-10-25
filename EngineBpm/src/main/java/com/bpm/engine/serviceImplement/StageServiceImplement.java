
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

import com.bpm.engine.service.StageService;
import com.bpm.engine.repository.StageRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.mappers.StageMapper;
import com.bpm.engine.models.RoleModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.models.TaskModel;

@Service
public class StageServiceImplement implements StageService {

	private static final Logger logger = LogManager.getLogger(StageServiceImplement.class);
	
	@Autowired
	private StageRepository stagerepository;

	@Autowired
	private StageMapper stageMapper;

	@Override
	public StageModel findByStageCode(String stageCode) {

		logger.info("Starting getStage");
		Stage stageEntity = new Stage();
		Optional<Stage> fileOptional1 = stagerepository.findByStageCode(stageCode);

		if (fileOptional1.isPresent()) {

			try {
				stageEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stageMapper.entityToPojo(stageEntity);
	}

	@Override
	public StageModel findByName(String name) {

		logger.info("Starting getStage");
		Stage stageEntity = new Stage();
		Optional<Stage> fileOptional1 = stagerepository.findByName(name);

		if (fileOptional1.isPresent()) {

			try {
				stageEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stageMapper.entityToPojo(stageEntity);
	}

	@Override
	public StageModel findByTitle(String title) {

		logger.info("Starting getStage");
		Stage stageEntity = new Stage();
		Optional<Stage> fileOptional1 = stagerepository.findByTitle(title);

		if (fileOptional1.isPresent()) {

			try {
				stageEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stageMapper.entityToPojo(stageEntity);
	}

	@Override
	public StageModel findByDateCreate(Date dateCreate) {

		logger.info("Starting getStage");
		Stage stageEntity = new Stage();
		Optional<Stage> fileOptional1 = stagerepository.findByDateCreate(dateCreate);

		if (fileOptional1.isPresent()) {

			try {
				stageEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stageMapper.entityToPojo(stageEntity);
	}

	@Override
	public StageModel findByType(String type) {

		logger.info("Starting getStage");
		Stage stageEntity = new Stage();
		Optional<Stage> fileOptional1 = stagerepository.findByType(type);

		if (fileOptional1.isPresent()) {

			try {
				stageEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stageMapper.entityToPojo(stageEntity);
	}

	@Override
	public List<StageModel> getAllStage() {
		logger.info("execute> getAllStageGet allProyect");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		stagerepository.findAll().forEach(stage -> listaStage.add(stageMapper.entityToPojo(stage)));
		return listaStage;
	}

	@Override
	public boolean saveStage(Stage stage) {
		logger.info("Save Proyect");

		try {
			stagerepository.save(stage);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateStage(Stage stage) {
		logger.info("Update ENTITY");
		boolean clave = false;
		Stage empre = stagerepository.findById(stage.getIdStage()).get();
		empre = stage;

		try {
			stagerepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public StageModel findById(Long id) {
		return stageMapper.entityToPojo(stagerepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateStage(Stage stage) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<Stage> fileOptional2 = stagerepository.findById(stage.getIdStage());
		if (fileOptional2.isPresent()) {
			clave = this.updateStage(stage);
			logger.info(" is update");
		} else {
			clave = this.saveStage(stage);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<StageModel> findByStageCodeContaining(String stagecode) {
		logger.info("Execute StageCode Containing");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		List<Stage> listaSStage = stagerepository.findByStageCodeContaining(stagecode);
		listaSStage.forEach(stagex -> listaStage.add(stageMapper.entityToPojo(stagex)));
		return listaStage;
	}

	@Override
	public List<StageModel> findByNameContaining(String name) {
		logger.info("Execute Name Containing");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		List<Stage> listaSStage = stagerepository.findByNameContaining(name);
		listaSStage.forEach(stagex -> listaStage.add(stageMapper.entityToPojo(stagex)));
		return listaStage;
	}

	@Override
	public List<StageModel> findByTitleContaining(String title) {
		logger.info("Execute Title Containing");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		List<Stage> listaSStage = stagerepository.findByTitleContaining(title);
		listaSStage.forEach(stagex -> listaStage.add(stageMapper.entityToPojo(stagex)));
		return listaStage;
	}

	@Override
	public List<StageModel> findByDateCreateContaining(Date datecreate) {
		logger.info("Execute DateCreate Containing");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		List<Stage> listaSStage = stagerepository.findByDateCreateContaining(datecreate);
		listaSStage.forEach(stagex -> listaStage.add(stageMapper.entityToPojo(stagex)));
		return listaStage;
	}

	@Override
	public List<StageModel> findByTypeContaining(String type) {
		logger.info("Execute Type Containing");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		List<Stage> listaSStage = stagerepository.findByTypeContaining(type);
		listaSStage.forEach(stagex -> listaStage.add(stageMapper.entityToPojo(stagex)));
		return listaStage;
	}

	@Override
	public List<StageModel> findByStageContaining(Stage stages) {
		logger.info(" Execute Stage Containing ");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		for (StageModel stage : this.getAllStage()) {
			for (StageModel stagesx : stage.getstages()) {
				if (stagesx.equals(stages)) {
					listaStage.add(stage);
				}
			}
		}
		return listaStage;

	}

	@Override
	public List<StageModel> findByRoleContaining(Role roles) {
		logger.info(" Execute Role Containing ");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		for (StageModel stage : this.getAllStage()) {
			for (RoleModel rolesx : stage.getroles()) {
				if (rolesx.equals(roles)) {
					listaStage.add(stage);
				}
			}
		}
		return listaStage;

	}

	@Override
	public List<StageModel> findByTaskContaining(Task tasks) {
		logger.info(" Execute Task Containing ");
		List<StageModel> listaStage = new ArrayList<StageModel>();
		for (StageModel stage : this.getAllStage()) {
			for (TaskModel tasksx : stage.gettasks()) {
				if (tasksx.equals(tasks)) {
					listaStage.add(stage);
				}
			}
		}
		return listaStage;

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
