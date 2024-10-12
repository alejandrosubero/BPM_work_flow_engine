
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

import com.bpm.engine.dto.BpmAssignedDTO;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.service.BpmAssignedService;
import com.bpm.engine.validation.AssignedValidation;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bpm.engine.entitys.Role;
import com.bpm.engine.managers.BpmAssignedManager;
import com.bpm.engine.mappers.AssignedMapper;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.RoleMapper;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.RoleModel;
import com.bpm.engine.validation.RoleValidation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/assigned")
public class AssignedController {

   
    private AssignedService assignedService;
    private AssignedValidation assignedValidationService;
    private MapperEntityRespone mapperEntityRespone;
    private RoleValidation roleValidationService;
    private RoleMapper roleMapper;
    private BpmAssignedManager bpmAssignedManager;
	private BpmAssignedService bpmAssignedService;
	
	
	
	@Autowired
    public AssignedController(AssignedService assignedService, AssignedValidation assignedValidationService,
			 MapperEntityRespone mapperEntityRespone,
			RoleValidation roleValidationService, RoleMapper roleMapper, BpmAssignedManager bpmAssignedManager,
			BpmAssignedService bpmAssignedService) {
		super();
		this.assignedService = assignedService;
		this.assignedValidationService = assignedValidationService;
		this.mapperEntityRespone = mapperEntityRespone;
		this.roleValidationService = roleValidationService;
		this.roleMapper = roleMapper;
		this.bpmAssignedManager = bpmAssignedManager;
		this.bpmAssignedService = bpmAssignedService;
	}

	/***
     *
     * @param assignedBPM
     * @return value boolean before the save a new assigned approver
     */
    @PostMapping("bpm/saveOrUpdate")
    private boolean saveOrUpdateBpmAssigned(@RequestBody BpmAssignedDTO assignedBPM) {
        return  bpmAssignedManager.saveOrUpdateBpmAssigned(assignedBPM);
    }

    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateAssigned(@RequestBody AssignedModel assigned) {
        return assignedService.saveOrUpdateAssigned(assignedValidationService.valida(assigned)) != null;
    }


    @GetMapping("/get/code/employee/{codeEmployee}")
    private ResponseEntity<EntityRespone> findByCodeEmployee(@PathVariable("codeEmployee") String codeEmployee) {
        try {
            String code = (String) assignedValidationService.validation(codeEmployee);
            if(code == null){
                EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "codeEmployee = null Error", "the variable is null");
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
            }
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(assignedService.findByCodeEmployee(code));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetAssigned/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(assignedService.findById(assignedValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/get/All/Assigned")
    private ResponseEntity<EntityRespone> getAllAssigned() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(assignedService.getAllAssigned());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/find/Assigned/Role")
    private ResponseEntity<EntityRespone> findRelacionRole(@RequestBody RoleModel role) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(assignedService.findByRelacionRole(roleMapper.pojoToEntity(roleValidationService.valida(role))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }
    
    
//	 http://localhost:1111/bpm/assigned/bpm/
@PostMapping("/bpm")
private ResponseEntity<EntityRespone> bpmAssignedTest(@RequestBody BpmAssignedModel bpmAssignedModel) {
	try {
		
		if(bpmAssignedModel != null) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(
					bpmAssignedService.saveOrUpdateBpmAssigned(bpmAssignedModel)
					);
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		}
		
		return new ResponseEntity<EntityRespone>( mapperEntityRespone.setEntityResponT("Error", "","Call which a null Object" ), HttpStatus.BAD_REQUEST);
		
	} catch (DataAccessException e) {
		EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
		return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
	}
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


