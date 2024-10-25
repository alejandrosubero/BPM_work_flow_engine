
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
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.TaskTypeMapper;
import com.bpm.engine.models.TaskTypeModel;
import com.bpm.engine.validation.TaskTypeValidation;
import com.bpm.engine.service.TaskTypeService;
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
@RequestMapping("/tasktype")
public class TaskTypeController {



		@Autowired
		TaskTypeService tasktypeService;

		@Autowired
		TaskTypeValidation tasktypeValidationService;

		@Autowired
		TaskTypeMapper tasktypeMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Gettype/{type}")
		private  ResponseEntity<EntityRespone> findByType(@PathVariable("type") String  type) {
		String busca = (String) tasktypeValidationService.validation(type);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(tasktypeService.findByType(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Gettypecontain/{type}")
		private ResponseEntity<EntityRespone> findByTypeContain(@PathVariable("type") String  type) {
			String busca = (String) tasktypeValidationService.validation(type);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(tasktypeService.findByTypeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetTaskType/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(tasktypeService.findById(tasktypeValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllTaskType")
		private  ResponseEntity<EntityRespone> getAllTaskType(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(tasktypeService.getAllTaskType());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveTaskType(@RequestBody TaskTypeModel  tasktype){ 
			return tasktypeService.saveTaskType(tasktypeMapper.pojoToEntity(tasktypeValidationService.valida(tasktype)) ); }



		@PostMapping("/Update")
		private Long UpdateTaskType(@RequestBody TaskTypeModel  tasktype){ 
			tasktypeService.updateTaskType(tasktypeMapper.pojoToEntity(tasktypeValidationService.valida(tasktype)));
			return tasktype.getIdTaskType(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateTaskType(@RequestBody TaskTypeModel  tasktype){ 
			return tasktypeService.saveOrUpdateTaskType(tasktypeMapper.pojoToEntity(tasktypeValidationService.valida(tasktype)) ); }


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


