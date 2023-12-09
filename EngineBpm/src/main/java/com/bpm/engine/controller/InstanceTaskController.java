
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

import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.validation.InstanceTaskValidation;
import com.bpm.engine.mapper.InstanceTaskMapper;
import com.bpm.engine.service.InstanceTaskService;
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
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.validation.AssignedValidation;
import com.bpm.engine.mapper.AssignedMapper;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.validation.TaskTypeValidation;
import com.bpm.engine.mapper.TaskTypeMapper;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.validation.TaskValidation;
import com.bpm.engine.mapper.TaskMapper;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.validation.RoleValidation;
import com.bpm.engine.mapper.RoleMapper;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.TaskTypeModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.model.RoleModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.TaskTypeModel;
import com.bpm.engine.model.TaskModel;
import com.bpm.engine.model.RoleModel;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/instancetask")
public class InstanceTaskController {

    @Autowired
    InstanceTaskService instancetaskService;
    @Autowired
    InstanceTaskValidation instancetaskValidationService;
    @Autowired
    InstanceTaskMapper instancetaskMapper;
    @Autowired
    MapperEntityRespone mapperEntityRespone;
    @Autowired
    AssignedValidation assignedValidationService;
    @Autowired
    AssignedMapper assignedMapper;
    @Autowired
    TaskTypeValidation tasktypeValidationService;
    @Autowired
    TaskTypeMapper tasktypeMapper;
    @Autowired
    TaskValidation taskValidationService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    RoleValidation roleValidationService;
    @Autowired
    RoleMapper roleMapper;


    @GetMapping("/Getname/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) instancetaskValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getstate/{state}")
    private ResponseEntity<EntityRespone> findByState(@PathVariable("state") String state) {
        String busca = (String) instancetaskValidationService.validation(state);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByState(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdatestart/{datestart}")
    private ResponseEntity<EntityRespone> findByDateStart(@PathVariable("datestart") Date datestart) {
        Date busca = (Date) instancetaskValidationService.validation(datestart);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByDateStart(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdateend/{dateend}")
    private ResponseEntity<EntityRespone> findByDateEnd(@PathVariable("dateend") Date dateend) {
        Date busca = (Date) instancetaskValidationService.validation(dateend);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByDateEnd(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getprocesscode/{processcode}")
    private ResponseEntity<EntityRespone> findByProcessCode(@PathVariable("processcode") String processcode) {
        String busca = (String) instancetaskValidationService.validation(processcode);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByProcessCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getinstanceprocesscode/{instanceprocesscode}")
    private ResponseEntity<EntityRespone> findByInstanceProcessCode(@PathVariable("instanceprocesscode") String instanceprocesscode) {
        String busca = (String) instancetaskValidationService.validation(instanceprocesscode);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByInstanceProcessCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getcodetask/{codetask}")
    private ResponseEntity<EntityRespone> findByCodeTask(@PathVariable("codetask") String codetask) {
        String busca = (String) instancetaskValidationService.validation(codetask);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findByCodeTask(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("/Getnamecontain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) instancetaskValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getstatecontain/{state}")
    private ResponseEntity<EntityRespone> findByStateContain(@PathVariable("state") String state) {
        String busca = (String) instancetaskValidationService.validation(state);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByStateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdatestartcontain/{datestart}")
    private ResponseEntity<EntityRespone> findByDateStartContain(@PathVariable("datestart") Date datestart) {
        Date busca = (Date) instancetaskValidationService.validation(datestart);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByDateStartContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdateendcontain/{dateend}")
    private ResponseEntity<EntityRespone> findByDateEndContain(@PathVariable("dateend") Date dateend) {
        Date busca = (Date) instancetaskValidationService.validation(dateend);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByDateEndContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getprocesscodecontain/{processcode}")
    private ResponseEntity<EntityRespone> findByProcessCodeContain(@PathVariable("processcode") String processcode) {
        String busca = (String) instancetaskValidationService.validation(processcode);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByProcessCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getinstanceprocesscodecontain/{instanceprocesscode}")
    private ResponseEntity<EntityRespone> findByInstanceProcessCodeContain(@PathVariable("instanceprocesscode") String instanceprocesscode) {
        String busca = (String) instancetaskValidationService.validation(instanceprocesscode);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByInstanceProcessCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getcodetaskcontain/{codetask}")
    private ResponseEntity<EntityRespone> findByCodeTaskContain(@PathVariable("codetask") String codetask) {
        String busca = (String) instancetaskValidationService.validation(codetask);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByCodeTaskContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetInstanceTask/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instancetaskService.findById(instancetaskValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllInstanceTask")
    private ResponseEntity<EntityRespone> getAllInstanceTask() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.getAllInstanceTask());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveInstanceTask(@RequestBody InstanceTaskModel instancetask) {
        return instancetaskService.saveInstanceTask(instancetaskMapper.pojoToEntity(instancetaskValidationService.valida(instancetask)));
    }


    @PostMapping("/Update")
    private Long UpdateInstanceTask(@RequestBody InstanceTaskModel instancetask) {
        instancetaskService.updateInstanceTask(instancetaskMapper.pojoToEntity(instancetaskValidationService.valida(instancetask)));
        return instancetask.getIdInstanceTask();
    }


    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateInstanceTask(@RequestBody InstanceTaskModel instancetask) {
        return instancetaskService.saveOrUpdateInstanceTask(instancetaskMapper.pojoToEntity(instancetaskValidationService.valida(instancetask)));
    }


    @PostMapping("/Get_assignes_contain/")
    private List<InstanceTaskModel> findByAssigned(@RequestBody AssignedModel assigned) {
        return instancetaskService.findByAssignedContaining(assignedMapper.pojoToEntity(assignedValidationService.valida(assigned)));
    }



    @PostMapping("/findtask")
    private ResponseEntity<EntityRespone> findRelacionTask(@RequestBody TaskModel task) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(instancetaskService.findByRelacionTask(taskMapper.pojoToEntity(taskValidationService.valida(task))));
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


