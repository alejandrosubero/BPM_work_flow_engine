
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

import com.bpm.engine.service.RoleService;
import com.bpm.engine.repository.RoleRepository;
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
import com.bpm.engine.entitys.Role;
import com.bpm.engine.mappers.RoleMapper;
import com.bpm.engine.models.RoleModel;

@Service
public class RoleServiceImplement implements RoleService {

	 private static final Logger logger = LogManager.getLogger(RoleServiceImplement.class);
	
	@Autowired
	private RoleRepository rolerepository;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RoleModel findByDescription(String description) {

		logger.info("Starting getRole");
		Role roleEntity = new Role();
		Optional<Role> fileOptional1 = rolerepository.findByDescription(description);

		if (fileOptional1.isPresent()) {

			try {
				roleEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return roleMapper.entityToPojo(roleEntity);
	}

	@Override
	public RoleModel findByName(String name) {

		logger.info("Starting getRole");
		Role roleEntity = new Role();
		Optional<Role> fileOptional1 = rolerepository.findByName(name);

		if (fileOptional1.isPresent()) {

			try {
				roleEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return roleMapper.entityToPojo(roleEntity);
	}

	@Override
	public RoleModel findByCodeRole(String codeRole) {

		logger.info("Starting getRole");
		Role roleEntity = new Role();
		Optional<Role> fileOptional1 = rolerepository.findByCodeRole(codeRole);

		if (fileOptional1.isPresent()) {

			try {
				roleEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

			}
		}
		return roleMapper.entityToPojo(roleEntity);
	}

	@Override
	public List<RoleModel> getAllRole() {
		logger.info("execute> getAllRoleGet allProyect");
		List<RoleModel> listaRole = new ArrayList<RoleModel>();
		rolerepository.findAll().forEach(role -> listaRole.add(roleMapper.entityToPojo(role)));
		return listaRole;
	}

	@Override
	public boolean saveRole(Role role) {
		logger.info("Save Proyect");

		try {
			rolerepository.save(role);
			return true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}

	@Override
	public boolean updateRole(Role role) {
		logger.info("Update ENTITY");
		boolean clave = false;
		Role empre = rolerepository.findById(role.getIdRole()).get();
		empre = role;

		try {
			rolerepository.save(empre);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}

		return clave;
	}

	@Override
	public RoleModel findById(Long id) {
		return roleMapper.entityToPojo(rolerepository.findById(id).get());
	}

	@Override
	public boolean saveOrUpdateRole(Role role) {
		logger.info("Update Proyect");
		boolean clave = false;
		Optional<Role> fileOptional2 = rolerepository.findById(role.getIdRole());
		if (fileOptional2.isPresent()) {
			clave = this.updateRole(role);
			logger.info(" is update");
		} else {
			clave = this.saveRole(role);
			logger.info(" is save");
		}
		return clave;
	}

	@Override
	public List<RoleModel> findByDescriptionContaining(String description) {
		logger.info("Execute Description Containing");
		List<RoleModel> listaRole = new ArrayList<RoleModel>();
		List<Role> listaSRole = rolerepository.findByDescriptionContaining(description);
		listaSRole.forEach(rolex -> listaRole.add(roleMapper.entityToPojo(rolex)));
		return listaRole;
	}

	@Override
	public List<RoleModel> findByNameContaining(String name) {
		logger.info("Execute Name Containing");
		List<RoleModel> listaRole = new ArrayList<RoleModel>();
		List<Role> listaSRole = rolerepository.findByNameContaining(name);
		listaSRole.forEach(rolex -> listaRole.add(roleMapper.entityToPojo(rolex)));
		return listaRole;
	}

	@Override
	public List<RoleModel> findByCodeRoleContaining(String coderole) {
		logger.info("Execute CodeRole Containing");
		List<RoleModel> listaRole = new ArrayList<RoleModel>();
		List<Role> listaSRole = rolerepository.findByCodeRoleContaining(coderole);
		listaSRole.forEach(rolex -> listaRole.add(roleMapper.entityToPojo(rolex)));
		return listaRole;
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
