
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
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.validation.InstanceStageValidation;
import com.bpm.engine.mapper.InstanceStageMapper;
import com.bpm.engine.service.InstanceStageService;
import com.bpm.engine.mapper.MapperEntityRespone;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.validation.InstanceStageValidation;
import com.bpm.engine.mapper.InstanceStageMapper;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.validation.AssignedValidation;
import com.bpm.engine.mapper.AssignedMapper;
import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.validation.InstanceProcessValidation;
import com.bpm.engine.mapper.InstanceProcessMapper;
import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.validation.InstanceTaskValidation;
import com.bpm.engine.mapper.InstanceTaskMapper;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceTaskModel;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/instancestage")
public class InstanceStageController {



		@Autowired
		InstanceStageService instancestageService;

		@Autowired
		InstanceStageValidation instancestageValidationService;

		@Autowired
		InstanceStageMapper instancestageMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;





		@Autowired
		AssignedValidation assignedValidationService;

		@Autowired
		AssignedMapper assignedMapper;

		@Autowired
		InstanceProcessValidation instanceprocessValidationService;

		@Autowired
		InstanceProcessMapper instanceprocessMapper;

		@Autowired
		InstanceTaskValidation instancetaskValidationService;

		@Autowired
		InstanceTaskMapper instancetaskMapper;



		 @GetMapping("/Getstate/{state}")
		private  ResponseEntity<EntityRespone> findByState(@PathVariable("state") String  state) {
		String busca = (String) instancestageValidationService.validation(state);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByState(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getname/{name}")
		private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
		String busca = (String) instancestageValidationService.validation(name);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByName(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getcode/{code}")
		private  ResponseEntity<EntityRespone> findByCode(@PathVariable("code") String  code) {
		String busca = (String) instancestageValidationService.validation(code);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdatestart/{datestart}")
		private  ResponseEntity<EntityRespone> findByDateStart(@PathVariable("datestart") Date  datestart) {
		Date busca = (Date) instancestageValidationService.validation(datestart);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByDateStart(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdateend/{dateend}")
		private  ResponseEntity<EntityRespone> findByDateEnd(@PathVariable("dateend") Date  dateend) {
		Date busca = (Date) instancestageValidationService.validation(dateend);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByDateEnd(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getprocescode/{procescode}")
		private  ResponseEntity<EntityRespone> findByProcesCode(@PathVariable("procescode") String  procescode) {
		String busca = (String) instancestageValidationService.validation(procescode);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByProcesCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}



		 @GetMapping("/Gettitle/{title}")
		private  ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String  title) {
		String busca = (String) instancestageValidationService.validation(title);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findByTitle(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getstatecontain/{state}")
		private ResponseEntity<EntityRespone> findByStateContain(@PathVariable("state") String  state) {
			String busca = (String) instancestageValidationService.validation(state);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByStateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnamecontain/{name}")
		private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
			String busca = (String) instancestageValidationService.validation(name);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByNameContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getcodecontain/{code}")
		private ResponseEntity<EntityRespone> findByCodeContain(@PathVariable("code") String  code) {
			String busca = (String) instancestageValidationService.validation(code);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdatestartcontain/{datestart}")
		private ResponseEntity<EntityRespone> findByDateStartContain(@PathVariable("datestart") Date  datestart) {
			Date busca = (Date) instancestageValidationService.validation(datestart);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByDateStartContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdateendcontain/{dateend}")
		private ResponseEntity<EntityRespone> findByDateEndContain(@PathVariable("dateend") Date  dateend) {
			Date busca = (Date) instancestageValidationService.validation(dateend);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByDateEndContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getprocescodecontain/{procescode}")
		private ResponseEntity<EntityRespone> findByProcesCodeContain(@PathVariable("procescode") String  procescode) {
			String busca = (String) instancestageValidationService.validation(procescode);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByProcesCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/Gettitlecontain/{title}")
		private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String  title) {
			String busca = (String) instancestageValidationService.validation(title);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.findByTitleContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetInstanceStage/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancestageService.findById(instancestageValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllInstanceStage")
		private  ResponseEntity<EntityRespone> getAllInstanceStage(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancestageService.getAllInstanceStage());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveInstanceStage(@RequestBody InstanceStageModel  instancestage){ 
			return instancestageService.saveInstanceStage(instancestageMapper.pojoToEntity(instancestageValidationService.valida(instancestage)) ); }



		@PostMapping("/Update")
		private Long UpdateInstanceStage(@RequestBody InstanceStageModel  instancestage){ 
			instancestageService.updateInstanceStage(instancestageMapper.pojoToEntity(instancestageValidationService.valida(instancestage)));
			return instancestage.getId(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateInstanceStage(@RequestBody InstanceStageModel  instancestage){ 
			return instancestageService.saveOrUpdateInstanceStage(instancestageMapper.pojoToEntity(instancestageValidationService.valida(instancestage)) ); }



		@PostMapping("/Get_instanceStages_contain/")
		private List<InstanceStageModel> findByInstanceStage(@RequestBody InstanceStageModel  instancestage){ 
			return instancestageService.findByInstanceStageContaining(instancestageMapper.pojoToEntity(instancestageValidationService.valida(instancestage))); }


		@PostMapping("/Get_assigned_contain/")
		private List<InstanceStageModel> findByAssigned(@RequestBody AssignedModel  assigned){ 
			return instancestageService.findByAssignedContaining(assignedMapper.pojoToEntity(assignedValidationService.valida(assigned))); }


		@PostMapping("/Get_instancesTasks_contain/")
		private List<InstanceStageModel> findByInstanceTask(@RequestBody InstanceTaskModel  instancetask){ 
			return instancestageService.findByInstanceTaskContaining(instancetaskMapper.pojoToEntity(instancetaskValidationService.valida(instancetask))); }


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


