
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



package com.bpm.engine.controller;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.RoleMapper;
import com.bpm.engine.models.RoleModel;
import com.bpm.engine.validation.RoleValidation;
import com.bpm.engine.service.RoleService;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {



		@Autowired
		RoleService roleService;

		@Autowired
		RoleValidation roleValidationService;

		@Autowired
		RoleMapper roleMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getdescription/{description}")
		private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
		String busca = (String) roleValidationService.validation(description);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(roleService.findByDescription(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getname/{name}")
		private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
		String busca = (String) roleValidationService.validation(name);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(roleService.findByName(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getcoderole/{coderole}")
		private  ResponseEntity<EntityRespone> findByCodeRole(@PathVariable("coderole") String  coderole) {
		String busca = (String) roleValidationService.validation(coderole);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(roleService.findByCodeRole(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getdescriptioncontain/{description}")
		private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
			String busca = (String) roleValidationService.validation(description);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(roleService.findByDescriptionContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnamecontain/{name}")
		private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
			String busca = (String) roleValidationService.validation(name);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(roleService.findByNameContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getcoderolecontain/{coderole}")
		private ResponseEntity<EntityRespone> findByCodeRoleContain(@PathVariable("coderole") String  coderole) {
			String busca = (String) roleValidationService.validation(coderole);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(roleService.findByCodeRoleContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetRole/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(roleService.findById(roleValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllRole")
		private  ResponseEntity<EntityRespone> getAllRole(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(roleService.getAllRole());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveRole(@RequestBody RoleModel  role){ 
			return roleService.saveRole(roleMapper.pojoToEntity(roleValidationService.valida(role)) ); }



		@PostMapping("/Update")
		private Long UpdateRole(@RequestBody RoleModel  role){ 
			roleService.updateRole(roleMapper.pojoToEntity(roleValidationService.valida(role)));
			return role.getIdRole(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateRole(@RequestBody RoleModel  role){ 
			return roleService.saveOrUpdateRole(roleMapper.pojoToEntity(roleValidationService.valida(role)) ); }


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


