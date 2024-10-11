
/*
Create on Sat Sep 30 10:44:39 EDT 2023
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

import com.bpm.engine.service.SystemResponseService;
import com.bpm.engine.repository.SystemResponseRepository;
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
import com.bpm.engine.entitys.SystemResponse;
import com.bpm.engine.mappers.SystemResponseMapper;
import com.bpm.engine.models.SystemResponseModel;

@Service
public class SystemResponseServiceImplement implements SystemResponseService {

	private static final Logger logger = LogManager.getLogger(SystemResponseServiceImplement.class);
	
	@Autowired
	private SystemResponseRepository systemresponserepository;

	@Autowired
	private SystemResponseMapper systemresponseMapper;

	@Override
	public SystemResponseModel findByResponse(String response) {

		logger.info("Starting getSystemResponse");
		SystemResponse systemresponseEntity = new SystemResponse();
		Optional<SystemResponse> fileOptional1 = systemresponserepository.findByResponse(response);

		if (fileOptional1.isPresent()) {

			try {
				systemresponseEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return systemresponseMapper.entityToPojo(systemresponseEntity);
	}

	@Override
	public List<SystemResponseModel> getAllSystemResponse() {
		logger.info("execute> getAllSystemResponseGet allProyect");
		List<SystemResponseModel> listaSystemResponse = new ArrayList<SystemResponseModel>();
		systemresponserepository.findAll()
				.forEach(systemresponse -> listaSystemResponse.add(systemresponseMapper.entityToPojo(systemresponse)));
		return listaSystemResponse;
	}

	@Override
	public boolean saveSystemResponse(SystemResponse systemresponse) {
		logger.info("Save Proyect");

		try {
			systemresponserepository.save(systemresponse);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateSystemResponse(SystemResponse systemresponse) {
		logger.info("Update ENTITY");
		boolean clave = false;
		SystemResponse empre = systemresponserepository.findById(systemresponse.getId()).get();
		empre = systemresponse;

		try {
			systemresponserepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public SystemResponseModel findById(Long id) {
		return systemresponseMapper.entityToPojo(systemresponserepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateSystemResponse(SystemResponse systemresponse) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<SystemResponse> fileOptional2 = systemresponserepository.findById(systemresponse.getId());
		if (fileOptional2.isPresent()) {
			clave = this.updateSystemResponse(systemresponse);
			logger.info(" is update");
		} else {
			clave = this.saveSystemResponse(systemresponse);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<SystemResponseModel> findByResponseContaining(String response) {
		logger.info("Execute Response Containing");
		List<SystemResponseModel> listaSystemResponse = new ArrayList<SystemResponseModel>();
		List<SystemResponse> listaSSystemResponse = systemresponserepository.findByResponseContaining(response);
		listaSSystemResponse.forEach(
				systemresponsex -> listaSystemResponse.add(systemresponseMapper.entityToPojo(systemresponsex)));
		return listaSystemResponse;
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
