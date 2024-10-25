
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
import com.bpm.engine.entitys.InstanceTaskEmail;
import com.bpm.engine.mappers.InstanceTaskEmailMapper;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.models.InstanceTaskEmailModel;
import com.bpm.engine.validation.InstanceTaskEmailValidation;
import com.bpm.engine.service.InstanceTaskEmailService;
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
@RequestMapping("/instancetaskemail")
public class InstanceTaskEmailController {


		@Autowired
		InstanceTaskEmailService instancetaskemailService;

		@Autowired
		InstanceTaskEmailValidation instancetaskemailValidationService;

		@Autowired
		InstanceTaskEmailMapper instancetaskemailMapper;

		@Autowired
		MapperEntityRespone mapperEntityRespone;



		 @GetMapping("/Getemailto/{emailto}")
		private  ResponseEntity<EntityRespone> findByEmailTo(@PathVariable("emailto") String  emailto) {
		String busca = (String) instancetaskemailValidationService.validation(emailto);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByEmailTo(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getemailcc/{emailcc}")
		private  ResponseEntity<EntityRespone> findByEmailCC(@PathVariable("emailcc") String  emailcc) {
		String busca = (String) instancetaskemailValidationService.validation(emailcc);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByEmailCC(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getemailfrom/{emailfrom}")
		private  ResponseEntity<EntityRespone> findByEmailFrom(@PathVariable("emailfrom") String  emailfrom) {
		String busca = (String) instancetaskemailValidationService.validation(emailfrom);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByEmailFrom(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getnota/{nota}")
		private  ResponseEntity<EntityRespone> findByNota(@PathVariable("nota") String  nota) {
		String busca = (String) instancetaskemailValidationService.validation(nota);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByNota(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Gettemplecode/{templecode}")
		private  ResponseEntity<EntityRespone> findByTempleCode(@PathVariable("templecode") String  templecode) {
		String busca = (String) instancetaskemailValidationService.validation(templecode);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByTempleCode(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdatecreate/{datecreate}")
		private  ResponseEntity<EntityRespone> findByDateCreate(@PathVariable("datecreate") Date  datecreate) {
		Date busca = (Date) instancetaskemailValidationService.validation(datecreate);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByDateCreate(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdateend/{dateend}")
		private  ResponseEntity<EntityRespone> findByDateEnd(@PathVariable("dateend") Date  dateend) {
		Date busca = (Date) instancetaskemailValidationService.validation(dateend);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByDateEnd(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getdatesent/{datesent}")
		private  ResponseEntity<EntityRespone> findByDateSent(@PathVariable("datesent") Date  datesent) {
		Date busca = (Date) instancetaskemailValidationService.validation(datesent);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByDateSent(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getrepete/{repete}")
		private  ResponseEntity<EntityRespone> findByRepete(@PathVariable("repete") Boolean  repete) {
		Boolean busca = (Boolean) instancetaskemailValidationService.validation(repete);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByRepete(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}

		 @GetMapping("/Getnumberrepete/{numberrepete}")
		private  ResponseEntity<EntityRespone> findByNumberRepete(@PathVariable("numberrepete") Integer  numberrepete) {
		Integer busca = (Integer) instancetaskemailValidationService.validation(numberrepete);
		try {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findByNumberRepete(busca));
				 return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			} catch (DataAccessException e) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		 }
		}


		@GetMapping("/Getemailtocontain/{emailto}")
		private ResponseEntity<EntityRespone> findByEmailToContain(@PathVariable("emailto") String  emailto) {
			String busca = (String) instancetaskemailValidationService.validation(emailto);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByEmailToContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getemailcccontain/{emailcc}")
		private ResponseEntity<EntityRespone> findByEmailCCContain(@PathVariable("emailcc") String  emailcc) {
			String busca = (String) instancetaskemailValidationService.validation(emailcc);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByEmailCCContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getemailfromcontain/{emailfrom}")
		private ResponseEntity<EntityRespone> findByEmailFromContain(@PathVariable("emailfrom") String  emailfrom) {
			String busca = (String) instancetaskemailValidationService.validation(emailfrom);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByEmailFromContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnotacontain/{nota}")
		private ResponseEntity<EntityRespone> findByNotaContain(@PathVariable("nota") String  nota) {
			String busca = (String) instancetaskemailValidationService.validation(nota);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByNotaContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Gettemplecodecontain/{templecode}")
		private ResponseEntity<EntityRespone> findByTempleCodeContain(@PathVariable("templecode") String  templecode) {
			String busca = (String) instancetaskemailValidationService.validation(templecode);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByTempleCodeContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdatecreatecontain/{datecreate}")
		private ResponseEntity<EntityRespone> findByDateCreateContain(@PathVariable("datecreate") Date  datecreate) {
			Date busca = (Date) instancetaskemailValidationService.validation(datecreate);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByDateCreateContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdateendcontain/{dateend}")
		private ResponseEntity<EntityRespone> findByDateEndContain(@PathVariable("dateend") Date  dateend) {
			Date busca = (Date) instancetaskemailValidationService.validation(dateend);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByDateEndContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getdatesentcontain/{datesent}")
		private ResponseEntity<EntityRespone> findByDateSentContain(@PathVariable("datesent") Date  datesent) {
			Date busca = (Date) instancetaskemailValidationService.validation(datesent);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByDateSentContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getrepetecontain/{repete}")
		private ResponseEntity<EntityRespone> findByRepeteContain(@PathVariable("repete") Boolean  repete) {
			Boolean busca = (Boolean) instancetaskemailValidationService.validation(repete);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByRepeteContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

		@GetMapping("/Getnumberrepetecontain/{numberrepete}")
		private ResponseEntity<EntityRespone> findByNumberRepeteContain(@PathVariable("numberrepete") Integer  numberrepete) {
			Integer busca = (Integer) instancetaskemailValidationService.validation(numberrepete);
				EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.findByNumberRepeteContaining(busca));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}


		@GetMapping("/GetInstanceTaskEmail/{id}")
		private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskemailService.findById(instancetaskemailValidationService.valida_id(id))); 
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}


		@GetMapping("/GetAllInstanceTaskEmail")
		private  ResponseEntity<EntityRespone> getAllInstanceTaskEmail(){
			EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskemailService.getAllInstanceTaskEmail());
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



		@PostMapping("/save")
		private Boolean  saveInstanceTaskEmail(@RequestBody InstanceTaskEmailModel  instancetaskemail){ 
			return instancetaskemailService.saveInstanceTaskEmail(instancetaskemailMapper.pojoToEntity(instancetaskemailValidationService.valida(instancetaskemail)) ); }



		@PostMapping("/Update")
		private Long UpdateInstanceTaskEmail(@RequestBody InstanceTaskEmailModel  instancetaskemail){ 
			instancetaskemailService.updateInstanceTaskEmail(instancetaskemailMapper.pojoToEntity(instancetaskemailValidationService.valida(instancetaskemail)));
			return instancetaskemail.getId(); }


		@PostMapping("/saveOrUpdate")
		private boolean saveOrUpdateInstanceTaskEmail(@RequestBody InstanceTaskEmailModel  instancetaskemail){ 
			return instancetaskemailService.saveOrUpdateInstanceTaskEmail(instancetaskemailMapper.pojoToEntity(instancetaskemailValidationService.valida(instancetaskemail)) ); }


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


