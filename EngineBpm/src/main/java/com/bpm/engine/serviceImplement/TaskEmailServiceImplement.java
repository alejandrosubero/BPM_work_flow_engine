
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

import com.bpm.engine.service.TaskEmailService;
import com.bpm.engine.repository.TaskEmailRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.TaskEmail;
import com.bpm.engine.mappers.TaskEmailMapper;
import com.bpm.engine.models.TaskEmailModel;

@Service
public class TaskEmailServiceImplement implements TaskEmailService {

	private static final Logger logger = LogManager.getLogger(TaskEmailServiceImplement.class);
	
	
	@Autowired
	private TaskEmailRepository taskemailrepository;

	@Autowired
	private TaskEmailMapper taskemailMapper;

	@Override
	public TaskEmailModel findByEmailFrom(String emailFrom) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByEmailFrom(emailFrom);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByNota(String nota) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByNota(nota);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByTempleCode(String templeCode) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByTempleCode(templeCode);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByEmailTo(String emailTo) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByEmailTo(emailTo);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByEmailCC(String emailCC) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByEmailCC(emailCC);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByNumberRepete(Integer numberRepete) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByNumberRepete(numberRepete);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public TaskEmailModel findByRepete(Boolean repete) {

		logger.info("Starting getTaskEmail");
		TaskEmail taskemailEntity = new TaskEmail();
		Optional<TaskEmail> fileOptional1 = taskemailrepository.findByRepete(repete);

		if (fileOptional1.isPresent()) {

			try {
				taskemailEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return taskemailMapper.entityToPojo(taskemailEntity);
	}

	@Override
	public List<TaskEmailModel> getAllTaskEmail() {
		logger.info("execute> getAllTaskEmailGet allProyect");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		taskemailrepository.findAll().forEach(taskemail -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemail)));
		return listaTaskEmail;
	}

	@Override
	public boolean saveTaskEmail(TaskEmail taskemail) {
		logger.info("Save Proyect");

		try {
			taskemailrepository.save(taskemail);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateTaskEmail(TaskEmail taskemail) {
		logger.info("Update ENTITY");
		boolean clave = false;
		TaskEmail empre = taskemailrepository.findById(taskemail.getIdEmail()).get();
		empre = taskemail;

		try {
			taskemailrepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public TaskEmailModel findById(Long id) {
		return taskemailMapper.entityToPojo(taskemailrepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateTaskEmail(TaskEmail taskemail) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<TaskEmail> fileOptional2 = taskemailrepository.findById(taskemail.getIdEmail());
		if (fileOptional2.isPresent()) {
			clave = this.updateTaskEmail(taskemail);
			logger.info(" is update");
		} else {
			clave = this.saveTaskEmail(taskemail);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<TaskEmailModel> findByEmailFromContaining(String emailfrom) {
		logger.info("Execute EmailFrom Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByEmailFromContaining(emailfrom);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByNotaContaining(String nota) {
		logger.info("Execute Nota Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByNotaContaining(nota);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByTempleCodeContaining(String templecode) {
		logger.info("Execute TempleCode Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByTempleCodeContaining(templecode);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByEmailToContaining(String emailto) {
		logger.info("Execute EmailTo Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByEmailToContaining(emailto);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByEmailCCContaining(String emailcc) {
		logger.info("Execute EmailCC Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByEmailCCContaining(emailcc);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByNumberRepeteContaining(Integer numberrepete) {
		logger.info("Execute NumberRepete Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByNumberRepeteContaining(numberrepete);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
	}

	@Override
	public List<TaskEmailModel> findByRepeteContaining(Boolean repete) {
		logger.info("Execute Repete Containing");
		List<TaskEmailModel> listaTaskEmail = new ArrayList<TaskEmailModel>();
		List<TaskEmail> listaSTaskEmail = taskemailrepository.findByRepeteContaining(repete);
		listaSTaskEmail.forEach(taskemailx -> listaTaskEmail.add(taskemailMapper.entityToPojo(taskemailx)));
		return listaTaskEmail;
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
