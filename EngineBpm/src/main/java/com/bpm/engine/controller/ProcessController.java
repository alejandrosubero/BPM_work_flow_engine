
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

import com.bpm.engine.entitys.Process;
import com.bpm.engine.validation.ProcessValidation;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.dto.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.bpm.engine.entitys.Stage;
import com.bpm.engine.managers.ProcessManager;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.ProcessMapper;
import com.bpm.engine.mappers.StageMapper;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.validation.StageValidation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @Autowired
    ProcessValidation processValidationService;

    @Autowired
    ProcessMapper processMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    StageValidation stageValidationService;

    @Autowired
    StageMapper stageMapper;

    @Autowired
    private ProcessManager processManager;


    @PostMapping("/create")
    private ResponseEntity<EntityRespone> createProcess(@RequestBody ProcessModel process) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processManager.createProcess(process));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/Getname/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) processValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getcreatedate/{createdate}")
    private ResponseEntity<EntityRespone> findByCreateDate(@PathVariable("createdate") Date createdate) {
        Date busca = (Date) processValidationService.validation(createdate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findByCreateDate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/Getprocestitle/{procestitle}")
    private ResponseEntity<EntityRespone> findByProcesTitle(@PathVariable("procestitle") String procestitle) {
        String busca = (String) processValidationService.validation(procestitle);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findByProcesTitle(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

   

    @GetMapping("/Getprocescode/{procescode}")
    private ResponseEntity<EntityRespone> findByProcesCode(@PathVariable("procescode") String procescode) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findByProcesCode(procescode));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/Getactivo/{activo}")
    private ResponseEntity<EntityRespone> findByActivo(@PathVariable("activo") Boolean activo) {
        Boolean busca = (Boolean) processValidationService.validation(activo);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findByActivo(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/Getnamecontain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) processValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getcreatedatecontain/{createdate}")
    private ResponseEntity<EntityRespone> findByCreateDateContain(@PathVariable("createdate") Date createdate) {
        Date busca = (Date) processValidationService.validation(createdate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByCreateDateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getusercreatecontain/{usercreate}")
    private ResponseEntity<EntityRespone> findByUserCreateContain(@PathVariable("usercreate") String usercreate) {
        String busca = (String) processValidationService.validation(usercreate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByUserCreateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getprocestitlecontain/{procestitle}")
    private ResponseEntity<EntityRespone> findByProcesTitleContain(@PathVariable("procestitle") String procestitle) {
        String busca = (String) processValidationService.validation(procestitle);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByProcesTitleContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getstatecontain/{state}")
    private ResponseEntity<EntityRespone> findByStateContain(@PathVariable("state") String state) {
        String busca = (String) processValidationService.validation(state);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByStateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getprocescodecontain/{procescode}")
    private ResponseEntity<EntityRespone> findByProcesCodeContain(@PathVariable("procescode") String procescode) {
        String busca = (String) processValidationService.validation(procescode);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByProcesCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getvisiblecontain/{visible}")
    private ResponseEntity<EntityRespone> findByVisibleContain(@PathVariable("visible") Boolean visible) {
        Boolean busca = (Boolean) processValidationService.validation(visible);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByVisibleContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getglobalcontain/{global}")
    private ResponseEntity<EntityRespone> findByGlobalContain(@PathVariable("global") Boolean global) {
        Boolean busca = (Boolean) processValidationService.validation(global);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByGlobalContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getactivocontain/{activo}")
    private ResponseEntity<EntityRespone> findByActivoContain(@PathVariable("activo") Boolean activo) {
        Boolean busca = (Boolean) processValidationService.validation(activo);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.findByActivoContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetProcess/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processService.findById(processValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllProcess")
    private ResponseEntity<EntityRespone> getAllProcess() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(processService.getAllProcess());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveProcess(@RequestBody ProcessModel process) {
        return processService.saveProcess(processMapper.pojoToEntity(processValidationService.valida(process)));
    }


    @PostMapping("/Update")
    private Long UpdateProcess(@RequestBody ProcessModel process) {
        processService.updateProcess(processMapper.pojoToEntity(processValidationService.valida(process)));
        return process.getId_process();
    }


    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateProcess(@RequestBody ProcessModel process) {
        return processService.saveOrUpdateProcess(processMapper.pojoToEntity(processValidationService.valida(process)));
    }


    @PostMapping("/Get_stages_contain/")
    private List<ProcessModel> findByStage(@RequestBody StageModel stage) {
        return processService.findByStageContaining(stageMapper.pojoToEntity(stageValidationService.valida(stage)));
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


