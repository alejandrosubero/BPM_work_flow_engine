
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
import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.validation.InstanceProcessValidation;
import com.bpm.engine.mapper.InstanceProcessMapper;
import com.bpm.engine.service.InstanceProcessService;
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
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.validation.InstanceStageValidation;
import com.bpm.engine.mapper.InstanceStageMapper;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.validation.ProcessValidation;
import com.bpm.engine.mapper.ProcessMapper;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.validation.AssignedValidation;
import com.bpm.engine.mapper.AssignedMapper;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.AssignedModel;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/instanceprocess")
public class InstanceProcessController {



		@Autowired
		InstanceProcessService instanceprocessService;

		@Autowired
		InstanceProcessValidation instanceprocessValidationService;

		@Autowired
		InstanceProcessMapper instanceprocessMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;

		@Autowired
		InstanceStageValidation instancestageValidationService;

		@Autowired
		InstanceStageMapper instancestageMapper;

		@Autowired
		ProcessValidation processValidationService;

		@Autowired
		ProcessMapper processMapper;

		@Autowired
		AssignedValidation assignedValidationService;

		@Autowired
		AssignedMapper assignedMapper;



		 @GetMapping("/Getname/{name}")
		private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
		String busca = (String) instanceprocessValidationService.validation(name);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByName(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getcreateby/{createby}")
		private  ResponseEntity<EntityRespone> findByCreateBy(@PathVariable("createby") String  createby) {
		String busca = (String) instanceprocessValidationService.validation(createby);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByCreateBy(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getstate/{state}")
		private  ResponseEntity<EntityRespone> findByState(@PathVariable("state") String  state) {
		String busca = (String) instanceprocessValidationService.validation(state);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByState(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdatecreate/{datecreate}")
		private  ResponseEntity<EntityRespone> findByDateCreate(@PathVariable("datecreate") Date  datecreate) {
		Date busca = (Date) instanceprocessValidationService.validation(datecreate);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByDateCreate(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdateend/{dateend}")
		private  ResponseEntity<EntityRespone> findByDateEnd(@PathVariable("dateend") Date  dateend) {
		Date busca = (Date) instanceprocessValidationService.validation(dateend);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByDateEnd(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getinstancecode/{instancecode}")
		private  ResponseEntity<EntityRespone> findByInstanceCode(@PathVariable("instancecode") String  instancecode) {
		String busca = (String) instanceprocessValidationService.validation(instancecode);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByInstanceCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Gettitle/{title}")
		private  ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String  title) {
		String busca = (String) instanceprocessValidationService.validation(title);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findByTitle(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getnamecontain/{name}")
		private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
			String busca = (String) instanceprocessValidationService.validation(name);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByNameContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getcreatebycontain/{createby}")
		private ResponseEntity<EntityRespone> findByCreateByContain(@PathVariable("createby") String  createby) {
			String busca = (String) instanceprocessValidationService.validation(createby);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByCreateByContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getstatecontain/{state}")
		private ResponseEntity<EntityRespone> findByStateContain(@PathVariable("state") String  state) {
			String busca = (String) instanceprocessValidationService.validation(state);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByStateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdatecreatecontain/{datecreate}")
		private ResponseEntity<EntityRespone> findByDateCreateContain(@PathVariable("datecreate") Date  datecreate) {
			Date busca = (Date) instanceprocessValidationService.validation(datecreate);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByDateCreateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdateendcontain/{dateend}")
		private ResponseEntity<EntityRespone> findByDateEndContain(@PathVariable("dateend") Date  dateend) {
			Date busca = (Date) instanceprocessValidationService.validation(dateend);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByDateEndContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getinstancecodecontain/{instancecode}")
		private ResponseEntity<EntityRespone> findByInstanceCodeContain(@PathVariable("instancecode") String  instancecode) {
			String busca = (String) instanceprocessValidationService.validation(instancecode);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByInstanceCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Gettitlecontain/{title}")
		private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String  title) {
			String busca = (String) instanceprocessValidationService.validation(title);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByTitleContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetInstanceProcess/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceprocessService.findById(instanceprocessValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllInstanceProcess")
		private  ResponseEntity<EntityRespone> getAllInstanceProcess(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.getAllInstanceProcess());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveInstanceProcess(@RequestBody InstanceProcessModel  instanceprocess){ 
			return instanceprocessService.saveInstanceProcess(instanceprocessMapper.pojoToEntity(instanceprocessValidationService.valida(instanceprocess)) ); }



		@PostMapping("/Update")
		private Long UpdateInstanceProcess(@RequestBody InstanceProcessModel  instanceprocess){ 
			instanceprocessService.updateInstanceProcess(instanceprocessMapper.pojoToEntity(instanceprocessValidationService.valida(instanceprocess)));
			return instanceprocess.getIdInstanceProcess(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateInstanceProcess(@RequestBody InstanceProcessModel  instanceprocess){ 
			return instanceprocessService.saveOrUpdateInstanceProcess(instanceprocessMapper.pojoToEntity(instanceprocessValidationService.valida(instanceprocess)) ); }



		@PostMapping("/Get_instanceStage_contain/")
		private List<InstanceProcessModel> findByInstanceStage(@RequestBody InstanceStageModel  instancestage){ 
			return instanceprocessService.findByInstanceStageContaining(instancestageMapper.pojoToEntity(instancestageValidationService.valida(instancestage))); }


		@PostMapping("/Get_assigned_contain/")
		private List<InstanceProcessModel> findByAssigned(@RequestBody AssignedModel  assigned){ 
			return instanceprocessService.findByAssignedContaining(assignedMapper.pojoToEntity(assignedValidationService.valida(assigned))); }


		@PostMapping("/findprocess")
		private ResponseEntity<EntityRespone> findRelacionProcess(@RequestBody ProcessModel process){ 
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instanceprocessService.findByRelacionProcess(processMapper.pojoToEntity(processValidationService.valida(process))));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
}
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


