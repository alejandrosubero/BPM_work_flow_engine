
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

import com.bpm.engine.validation.ControlProcessReferentValidation;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.mappers.ControlProcessReferentMapper;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.models.ControlProcessReferentModel;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/control/process")
public class ControlProcessReferentController {


    @Autowired
    ControlProcessReferentService controlprocessService;

    @Autowired
    ControlProcessReferentValidation controlprocessValidationService;

    @Autowired
    ControlProcessReferentMapper controlprocessMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;


    @GetMapping("/Getcode/{code}")
    private ResponseEntity<EntityRespone> findByCode(@PathVariable("code") String code) {
        String busca = (String) controlprocessValidationService.validation(code);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getname/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) controlprocessValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettitle/{title}")
    private ResponseEntity<EntityRespone> findByTitle(@PathVariable("title") String title) {
        String busca = (String) controlprocessValidationService.validation(title);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByTitle(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getstatus/{status}")
    private ResponseEntity<EntityRespone> findByStatus(@PathVariable("status") String status) {
        String busca = (String) controlprocessValidationService.validation(status);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByStatus(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Gettype/{type}")
    private ResponseEntity<EntityRespone> findByType(@PathVariable("type") String type) {
        String busca = (String) controlprocessValidationService.validation(type);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByType(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getidreference/{idreference}")
    private ResponseEntity<EntityRespone> findByIdReference(@PathVariable("idreference") Long idreference) {
        Long busca = (Long) controlprocessValidationService.validation(idreference);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByIdReference(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getactive/{active}")
    private ResponseEntity<EntityRespone> findByActive(@PathVariable("active") Boolean active) {
        Boolean busca = (Boolean) controlprocessValidationService.validation(active);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByActive(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdatecreate/{datecreate}")
    private ResponseEntity<EntityRespone> findByDateCreate(@PathVariable("datecreate") Date datecreate) {
        Date busca = (Date) controlprocessValidationService.validation(datecreate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByDateCreate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getdateclose/{dateclose}")
    private ResponseEntity<EntityRespone> findByDateClose(@PathVariable("dateclose") Date dateclose) {
        Date busca = (Date) controlprocessValidationService.validation(dateclose);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findByDateClose(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/Getcodecontain/{code}")
    private ResponseEntity<EntityRespone> findByCodeContain(@PathVariable("code") String code) {
        String busca = (String) controlprocessValidationService.validation(code);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getnamecontain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) controlprocessValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettitlecontain/{title}")
    private ResponseEntity<EntityRespone> findByTitleContain(@PathVariable("title") String title) {
        String busca = (String) controlprocessValidationService.validation(title);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByTitleContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getstatuscontain/{status}")
    private ResponseEntity<EntityRespone> findByStatusContain(@PathVariable("status") String status) {
        String busca = (String) controlprocessValidationService.validation(status);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByStatusContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Gettypecontain/{type}")
    private ResponseEntity<EntityRespone> findByTypeContain(@PathVariable("type") String type) {
        String busca = (String) controlprocessValidationService.validation(type);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByTypeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getidreferencecontain/{idreference}")
    private ResponseEntity<EntityRespone> findByIdReferenceContain(@PathVariable("idreference") Long idreference) {
        Long busca = (Long) controlprocessValidationService.validation(idreference);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByIdReferenceContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getactivecontain/{active}")
    private ResponseEntity<EntityRespone> findByActiveContain(@PathVariable("active") Boolean active) {
        Boolean busca = (Boolean) controlprocessValidationService.validation(active);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByActiveContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdatecreatecontain/{datecreate}")
    private ResponseEntity<EntityRespone> findByDateCreateContain(@PathVariable("datecreate") Date datecreate) {
        Date busca = (Date) controlprocessValidationService.validation(datecreate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByDateCreateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getdateclosecontain/{dateclose}")
    private ResponseEntity<EntityRespone> findByDateCloseContain(@PathVariable("dateclose") Date dateclose) {
        Date busca = (Date) controlprocessValidationService.validation(dateclose);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.findByDateCloseContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetControlProcess/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(controlprocessService.findById(controlprocessValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllControlProcess")
    private ResponseEntity<EntityRespone> getAllControlProcess() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(controlprocessService.getAllControlProcess());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveControlProcess(@RequestBody ControlProcessReferentModel controlprocess) {
        return controlprocessService.saveControlProcess(controlprocessMapper.pojoToEntity(controlprocessValidationService.valida(controlprocess)));
    }


    @PostMapping("/Update")
    private Long UpdateControlProcess(@RequestBody ControlProcessReferentModel controlprocess) {
        controlprocessService.updateControlProcess(controlprocessMapper.pojoToEntity(controlprocessValidationService.valida(controlprocess)));
        return controlprocess.getId();
    }


    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateControlProcess(@RequestBody ControlProcessReferentModel controlprocess) {
        return controlprocessService.saveOrUpdateControlProcess(controlprocessMapper.pojoToEntity(controlprocessValidationService.valida(controlprocess)));
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


