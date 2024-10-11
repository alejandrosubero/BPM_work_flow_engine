
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
import com.bpm.engine.entitys.TaskEmail;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.TaskEmailMapper;
import com.bpm.engine.models.TaskEmailModel;
import com.bpm.engine.validation.TaskEmailValidation;
import com.bpm.engine.service.TaskEmailService;
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
@RequestMapping("/taskemail")
public class TaskEmailController {



		@Autowired
		TaskEmailService taskemailService;

		@Autowired
		TaskEmailValidation taskemailValidationService;

		@Autowired
		TaskEmailMapper taskemailMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getemailfrom/{emailfrom}")
		private  ResponseEntity<EntityRespone> findByEmailFrom(@PathVariable("emailfrom") String  emailfrom) {
		String busca = (String) taskemailValidationService.validation(emailfrom);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByEmailFrom(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getnota/{nota}")
		private  ResponseEntity<EntityRespone> findByNota(@PathVariable("nota") String  nota) {
		String busca = (String) taskemailValidationService.validation(nota);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByNota(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Gettemplecode/{templecode}")
		private  ResponseEntity<EntityRespone> findByTempleCode(@PathVariable("templecode") String  templecode) {
		String busca = (String) taskemailValidationService.validation(templecode);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByTempleCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getemailto/{emailto}")
		private  ResponseEntity<EntityRespone> findByEmailTo(@PathVariable("emailto") String  emailto) {
		String busca = (String) taskemailValidationService.validation(emailto);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByEmailTo(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getemailcc/{emailcc}")
		private  ResponseEntity<EntityRespone> findByEmailCC(@PathVariable("emailcc") String  emailcc) {
		String busca = (String) taskemailValidationService.validation(emailcc);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByEmailCC(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getnumberrepete/{numberrepete}")
		private  ResponseEntity<EntityRespone> findByNumberRepete(@PathVariable("numberrepete") Integer  numberrepete) {
		Integer busca = (Integer) taskemailValidationService.validation(numberrepete);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByNumberRepete(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getrepete/{repete}")
		private  ResponseEntity<EntityRespone> findByRepete(@PathVariable("repete") Boolean  repete) {
		Boolean busca = (Boolean) taskemailValidationService.validation(repete);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findByRepete(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getemailfromcontain/{emailfrom}")
		private ResponseEntity<EntityRespone> findByEmailFromContain(@PathVariable("emailfrom") String  emailfrom) {
			String busca = (String) taskemailValidationService.validation(emailfrom);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByEmailFromContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnotacontain/{nota}")
		private ResponseEntity<EntityRespone> findByNotaContain(@PathVariable("nota") String  nota) {
			String busca = (String) taskemailValidationService.validation(nota);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByNotaContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Gettemplecodecontain/{templecode}")
		private ResponseEntity<EntityRespone> findByTempleCodeContain(@PathVariable("templecode") String  templecode) {
			String busca = (String) taskemailValidationService.validation(templecode);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByTempleCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getemailtocontain/{emailto}")
		private ResponseEntity<EntityRespone> findByEmailToContain(@PathVariable("emailto") String  emailto) {
			String busca = (String) taskemailValidationService.validation(emailto);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByEmailToContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getemailcccontain/{emailcc}")
		private ResponseEntity<EntityRespone> findByEmailCCContain(@PathVariable("emailcc") String  emailcc) {
			String busca = (String) taskemailValidationService.validation(emailcc);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByEmailCCContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnumberrepetecontain/{numberrepete}")
		private ResponseEntity<EntityRespone> findByNumberRepeteContain(@PathVariable("numberrepete") Integer  numberrepete) {
			Integer busca = (Integer) taskemailValidationService.validation(numberrepete);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByNumberRepeteContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getrepetecontain/{repete}")
		private ResponseEntity<EntityRespone> findByRepeteContain(@PathVariable("repete") Boolean  repete) {
			Boolean busca = (Boolean) taskemailValidationService.validation(repete);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.findByRepeteContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetTaskEmail/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskemailService.findById(taskemailValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllTaskEmail")
		private  ResponseEntity<EntityRespone> getAllTaskEmail(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskemailService.getAllTaskEmail());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveTaskEmail(@RequestBody TaskEmailModel  taskemail){ 
			return taskemailService.saveTaskEmail(taskemailMapper.pojoToEntity(taskemailValidationService.valida(taskemail)) ); }



		@PostMapping("/Update")
		private Long UpdateTaskEmail(@RequestBody TaskEmailModel  taskemail){ 
			taskemailService.updateTaskEmail(taskemailMapper.pojoToEntity(taskemailValidationService.valida(taskemail)));
			return taskemail.getIdEmail(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateTaskEmail(@RequestBody TaskEmailModel  taskemail){ 
			return taskemailService.saveOrUpdateTaskEmail(taskemailMapper.pojoToEntity(taskemailValidationService.valida(taskemail)) ); }


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


