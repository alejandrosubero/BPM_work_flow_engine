
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

import com.bpm.engine.entitys.Task;
import com.bpm.engine.validation.TaskValidation;
import com.bpm.engine.service.TaskService;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.TaskMapper;
import com.bpm.engine.mappers.TaskTypeMapper;
import com.bpm.engine.models.TaskModel;
import com.bpm.engine.models.TaskTypeModel;
import com.bpm.engine.validation.TaskTypeValidation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskService taskService;

    @Autowired
    TaskValidation taskValidationService;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    TaskTypeValidation tasktypeValidationService;

    @Autowired
    TaskTypeMapper tasktypeMapper;


    @GetMapping("/Gettitle/{title}")
    private ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String title) {
        String busca = (String) taskValidationService.validation(title);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByTitle(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getname/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) taskValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdatecreate/{datecreate}")
    private ResponseEntity<EntityRespone> findByDateCreate(@PathVariable("datecreate") Date datecreate) {
        Date busca = (Date) taskValidationService.validation(datecreate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByDateCreate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getprocescode/{procescode}")
    private ResponseEntity<EntityRespone> findByProcesCode(@PathVariable("procescode") String procescode) {
        String busca = (String) taskValidationService.validation(procescode);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByProcesCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettaskurl/{taskurl}")
    private ResponseEntity<EntityRespone> findByTaskUrl(@PathVariable("taskurl") String taskurl) {
        String busca = (String) taskValidationService.validation(taskurl);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByTaskUrl(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Geturlservice/{urlservice}")
    private ResponseEntity<EntityRespone> findByUrlService(@PathVariable("urlservice") String urlservice) {
        String busca = (String) taskValidationService.validation(urlservice);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByUrlService(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getcode/{code}")
    private ResponseEntity<EntityRespone> findByCode(@PathVariable("code") String code) {
        String busca = (String) taskValidationService.validation(code);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findByCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/Gettitlecontain/{title}")
    private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String title) {
        String busca = (String) taskValidationService.validation(title);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByTitleContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getnamecontain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) taskValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdatecreatecontain/{datecreate}")
    private ResponseEntity<EntityRespone> findByDateCreateContain(@PathVariable("datecreate") Date datecreate) {
        Date busca = (Date) taskValidationService.validation(datecreate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByDateCreateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getprocescodecontain/{procescode}")
    private ResponseEntity<EntityRespone> findByProcesCodeContain(@PathVariable("procescode") String procescode) {
        String busca = (String) taskValidationService.validation(procescode);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByProcesCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettaskurlcontain/{taskurl}")
    private ResponseEntity<EntityRespone> findByTaskUrlContain(@PathVariable("taskurl") String taskurl) {
        String busca = (String) taskValidationService.validation(taskurl);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByTaskUrlContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Geturlservicecontain/{urlservice}")
    private ResponseEntity<EntityRespone> findByUrlServiceContain(@PathVariable("urlservice") String urlservice) {
        String busca = (String) taskValidationService.validation(urlservice);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByUrlServiceContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getcodecontain/{code}")
    private ResponseEntity<EntityRespone> findByCodeContain(@PathVariable("code") String code) {
        String busca = (String) taskValidationService.validation(code);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetTask/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taskService.findById(taskValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllTask")
    private ResponseEntity<EntityRespone> getAllTask() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.getAllTask());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveTask(@RequestBody TaskModel task) {
        return taskService.saveTask(taskMapper.pojoToEntity(taskValidationService.valida(task)));
    }


    @PostMapping("/Update")
    private Long UpdateTask(@RequestBody TaskModel task) {
        taskService.updateTask(taskMapper.pojoToEntity(taskValidationService.valida(task)));
        return task.getIdTask();
    }


    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateTask(@RequestBody TaskModel task) {
        return taskService.saveOrUpdateTask(taskMapper.pojoToEntity(taskValidationService.valida(task)));
    }


    @PostMapping("/findtype")
    private ResponseEntity<EntityRespone> findRelacionTaskType(@RequestBody TaskTypeModel tasktype) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taskService.findByRelacionTaskType(tasktypeMapper.pojoToEntity(tasktypeValidationService.valida(tasktype))));
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


