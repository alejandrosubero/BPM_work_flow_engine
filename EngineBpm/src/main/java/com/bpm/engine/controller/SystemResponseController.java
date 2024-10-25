
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



package com.bpm.engine.controller;
import com.bpm.engine.entitys.SystemResponse;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.SystemResponseMapper;
import com.bpm.engine.models.SystemResponseModel;
import com.bpm.engine.validation.SystemResponseValidation;
import com.bpm.engine.service.SystemResponseService;
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
@RequestMapping("/system/response")
public class SystemResponseController {



		@Autowired
		SystemResponseService systemresponseService;

		@Autowired
		SystemResponseValidation systemresponseValidationService;

		@Autowired
		SystemResponseMapper systemresponseMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getresponse/{response}")
		private  ResponseEntity<EntityRespone> findByResponse(@PathVariable("response") String  response) {
		String busca = (String) systemresponseValidationService.validation(response);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(systemresponseService.findByResponse(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getresponsecontain/{response}")
		private ResponseEntity<EntityRespone> findByResponseContain(@PathVariable("response") String  response) {
			String busca = (String) systemresponseValidationService.validation(response);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(systemresponseService.findByResponseContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetSystemResponse/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(systemresponseService.findById(systemresponseValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllSystemResponse")
		private  ResponseEntity<EntityRespone> getAllSystemResponse(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(systemresponseService.getAllSystemResponse());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveSystemResponse(@RequestBody SystemResponseModel  systemresponse){ 
			return systemresponseService.saveSystemResponse(systemresponseMapper.pojoToEntity(systemresponseValidationService.valida(systemresponse)) ); }



		@PostMapping("/Update")
		private Long UpdateSystemResponse(@RequestBody SystemResponseModel  systemresponse){ 
			systemresponseService.updateSystemResponse(systemresponseMapper.pojoToEntity(systemresponseValidationService.valida(systemresponse)));
			return systemresponse.getId(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateSystemResponse(@RequestBody SystemResponseModel  systemresponse){ 
			return systemresponseService.saveOrUpdateSystemResponse(systemresponseMapper.pojoToEntity(systemresponseValidationService.valida(systemresponse)) ); }


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


