
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
import com.bpm.engine.entitys.State;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.StateMapper;
import com.bpm.engine.models.StateModel;
import com.bpm.engine.validation.StateValidation;
import com.bpm.engine.service.StateService;
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
@RequestMapping("/state")
public class StateController {



		@Autowired
		StateService stateService;

		@Autowired
		StateValidation stateValidationService;

		@Autowired
		StateMapper stateMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getthestate/{thestate}")
		private  ResponseEntity<EntityRespone> findByTheState(@PathVariable("thestate") String  thestate) {
		String busca = (String) stateValidationService.validation(thestate);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stateService.findByTheState(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getthestatecontain/{thestate}")
		private ResponseEntity<EntityRespone> findByTheStateContain(@PathVariable("thestate") String  thestate) {
			String busca = (String) stateValidationService.validation(thestate);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(stateService.findByTheStateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetState/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(stateService.findById(stateValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllState")
		private  ResponseEntity<EntityRespone> getAllState(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(stateService.getAllState());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveState(@RequestBody StateModel  state){ 
			return stateService.saveState(stateMapper.pojoToEntity(stateValidationService.valida(state)) ); }



		@PostMapping("/Update")
		private Long UpdateState(@RequestBody StateModel  state){ 
			stateService.updateState(stateMapper.pojoToEntity(stateValidationService.valida(state)));
			return state.getIdState(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateState(@RequestBody StateModel  state){ 
			return stateService.saveOrUpdateState(stateMapper.pojoToEntity(stateValidationService.valida(state)) ); }


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


