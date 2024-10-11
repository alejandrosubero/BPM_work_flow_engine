
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
import com.bpm.engine.entitys.Stage;
import com.bpm.engine.validation.StageValidation;
import com.bpm.engine.service.StageService;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bpm.engine.entitys.Stage;
import com.bpm.engine.validation.StageValidation;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.validation.RoleValidation;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.RoleMapper;
import com.bpm.engine.mappers.StageMapper;
import com.bpm.engine.mappers.TaskMapper;
import com.bpm.engine.models.RoleModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.models.TaskModel;
import com.bpm.engine.validation.TaskValidation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stage")
public class StageController {



		@Autowired
		StageService stageService;

		@Autowired
		StageValidation stageValidationService;

		@Autowired
		StageMapper stageMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;





		@Autowired
		RoleValidation roleValidationService;

		@Autowired
		RoleMapper roleMapper;

		@Autowired
		TaskValidation taskValidationService;

		@Autowired
		TaskMapper taskMapper;



		 @GetMapping("/Getstagecode/{stagecode}")
		private  ResponseEntity<EntityRespone> findByStageCode(@PathVariable("stagecode") String  stagecode) {
		String busca = (String) stageValidationService.validation(stagecode);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findByStageCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getname/{name}")
		private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
		String busca = (String) stageValidationService.validation(name);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findByName(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Gettitle/{title}")
		private  ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String  title) {
		String busca = (String) stageValidationService.validation(title);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findByTitle(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdatecreate/{datecreate}")
		private  ResponseEntity<EntityRespone> findByDateCreate(@PathVariable("datecreate") Date  datecreate) {
		Date busca = (Date) stageValidationService.validation(datecreate);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findByDateCreate(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Gettype/{type}")
		private  ResponseEntity<EntityRespone> findByType(@PathVariable("type") String  type) {
		String busca = (String) stageValidationService.validation(type);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findByType(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}




		@GetMapping("/Getstagecodecontain/{stagecode}")
		private ResponseEntity<EntityRespone> findByStageCodeContain(@PathVariable("stagecode") String  stagecode) {
			String busca = (String) stageValidationService.validation(stagecode);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.findByStageCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnamecontain/{name}")
		private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
			String busca = (String) stageValidationService.validation(name);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.findByNameContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Gettitlecontain/{title}")
		private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String  title) {
			String busca = (String) stageValidationService.validation(title);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.findByTitleContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdatecreatecontain/{datecreate}")
		private ResponseEntity<EntityRespone> findByDateCreateContain(@PathVariable("datecreate") Date  datecreate) {
			Date busca = (Date) stageValidationService.validation(datecreate);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.findByDateCreateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Gettypecontain/{type}")
		private ResponseEntity<EntityRespone> findByTypeContain(@PathVariable("type") String  type) {
			String busca = (String) stageValidationService.validation(type);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.findByTypeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}



		@GetMapping("/GetStage/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stageService.findById(stageValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllStage")
		private  ResponseEntity<EntityRespone> getAllStage(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(stageService.getAllStage());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveStage(@RequestBody StageModel  stage){ 
			return stageService.saveStage(stageMapper.pojoToEntity(stageValidationService.valida(stage)) ); }



		@PostMapping("/Update")
		private Long UpdateStage(@RequestBody StageModel  stage){ 
			stageService.updateStage(stageMapper.pojoToEntity(stageValidationService.valida(stage)));
			return stage.getIdStage(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateStage(@RequestBody StageModel  stage){ 
			return stageService.saveOrUpdateStage(stageMapper.pojoToEntity(stageValidationService.valida(stage)) ); }



		@PostMapping("/Get_stages_contain/")
		private List<StageModel> findByStage(@RequestBody StageModel  stage){ 
			return stageService.findByStageContaining(stageMapper.pojoToEntity(stageValidationService.valida(stage))); }


		@PostMapping("/Get_roles_contain/")
		private List<StageModel> findByRole(@RequestBody RoleModel  role){ 
			return stageService.findByRoleContaining(roleMapper.pojoToEntity(roleValidationService.valida(role))); }


		@PostMapping("/Get_tasks_contain/")
		private List<StageModel> findByTask(@RequestBody TaskModel  task){ 
			return stageService.findByTaskContaining(taskMapper.pojoToEntity(taskValidationService.valida(task))); }

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


