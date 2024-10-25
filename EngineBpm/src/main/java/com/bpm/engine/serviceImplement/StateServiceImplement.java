
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

import com.bpm.engine.service.StateService;
import com.bpm.engine.repository.StateRepository;
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
import com.bpm.engine.entitys.State;
import com.bpm.engine.mappers.StateMapper;
import com.bpm.engine.models.StateModel;

@Service
public class StateServiceImplement implements StateService {

	private static final Logger logger = LogManager.getLogger(StateServiceImplement.class);
	
	@Autowired
	private StateRepository staterepository;

	@Autowired
	private StateMapper stateMapper;

	@Override
	public StateModel findByTheState(String theState) {

		logger.info("Starting getState");
		State stateEntity = new State();
		Optional<State> fileOptional1 = staterepository.findByTheState(theState);

		if (fileOptional1.isPresent()) {

			try {
				stateEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return stateMapper.entityToPojo(stateEntity);
	}

	@Override
	public List<StateModel> getAllState() {
		logger.info("execute> getAllStateGet allProyect");
		List<StateModel> listaState = new ArrayList<StateModel>();
		staterepository.findAll().forEach(state -> listaState.add(stateMapper.entityToPojo(state)));
		return listaState;
	}

	@Override
	public boolean saveState(State state) {
		logger.info("Save Proyect");

		try {
			staterepository.save(state);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateState(State state) {
		logger.info("Update ENTITY");
		boolean clave = false;
		State empre = staterepository.findById(state.getIdState()).get();
		empre = state;

		try {
			staterepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public StateModel findById(Long id) {
		return stateMapper.entityToPojo(staterepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateState(State state) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<State> fileOptional2 = staterepository.findById(state.getIdState());
		if (fileOptional2.isPresent()) {
			clave = this.updateState(state);
			logger.info(" is update");
		} else {
			clave = this.saveState(state);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<StateModel> findByTheStateContaining(String thestate) {
		logger.info("Execute TheState Containing");
		List<StateModel> listaState = new ArrayList<StateModel>();
		List<State> listaSState = staterepository.findByTheStateContaining(thestate);
		listaSState.forEach(statex -> listaState.add(stateMapper.entityToPojo(statex)));
		return listaState;
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
