
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


package com.bpm.engine.serviceImplement;

import com.bpm.engine.service.InstanceStageService;
import com.bpm.engine.repository.InstanceStageRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.mapper.InstanceStageMapper;


import com.bpm.engine.entitys.Assigned;

import com.bpm.engine.model.AssignedModel;


import com.bpm.engine.entitys.InstanceTask;

import com.bpm.engine.model.InstanceTaskModel;


@Service
public class InstanceStageServiceImplement implements InstanceStageService {

//    protected static final Log logger = LogFactory.getLog(InstanceStageServiceImplement.class);
	 private static final Logger logger = LogManager.getLogger(InstanceStageServiceImplement.class);
	
    @Autowired
    private InstanceStageRepository instancestagerepository;

    @Autowired
    private InstanceStageMapper instancestageMapper;


    @Override
    public InstanceStageModel findByState(String state) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByState(state);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }

    @Override
    public InstanceStageModel findByName(String name) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByName(name);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }

    @Override
    public InstanceStageModel findByCode(String code) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByCode(code);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }

    @Override
    public InstanceStageModel findByDateStart(Date dateStart) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByDateStart(dateStart);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }

    @Override
    public InstanceStageModel findByDateEnd(Date dateEnd) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByDateEnd(dateEnd);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }

    @Override
    public InstanceStageModel findByProcesCode(String procesCode) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByProcesCode(procesCode);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }



    @Override
    public InstanceStageModel findByTitle(String title) {

        logger.info("Starting getInstanceStage");
        InstanceStage instancestageEntity = new InstanceStage();
        Optional<InstanceStage> fileOptional1 = instancestagerepository.findByTitle(title);

        if (fileOptional1.isPresent()) {

            try {
                instancestageEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancestageMapper.entityToPojo(instancestageEntity);
    }


    @Override
    public List<InstanceStageModel> getAllInstanceStage() {
        logger.info("execute> getAllInstanceStageGet allProyect");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        instancestagerepository.findAll().forEach(instancestage -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestage)));
        return listaInstanceStage;
    }


    @Override
    public boolean saveInstanceStage(InstanceStage instancestage) {
        logger.info("Save Proyect");


        try {
            instancestagerepository.save(instancestage);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateInstanceStage(InstanceStage instancestage) {
        logger.info("Update ENTITY");
        boolean clave = false;
        InstanceStage empre = instancestagerepository.findById(instancestage.getId()).get();
        empre = instancestage;

        try {
            instancestagerepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }

        return clave;
    }


    @Override
    public InstanceStageModel findById(Long id) {
        return instancestageMapper.entityToPojo(instancestagerepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateInstanceStage(InstanceStage instancestage) {
        logger.info("Update Proyect");
        boolean clave = false;
        Optional<InstanceStage> fileOptional2 = instancestagerepository.findById(instancestage.getId());
        if (fileOptional2.isPresent()) {
            clave = this.updateInstanceStage(instancestage);
            logger.info(" is update");
        } else {
            clave = this.saveInstanceStage(instancestage);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<InstanceStageModel> findByStateContaining(String state) {
        logger.info("Execute State Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByStateContaining(state);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByNameContaining(name);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByCodeContaining(String code) {
        logger.info("Execute Code Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByCodeContaining(code);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByDateStartContaining(Date datestart) {
        logger.info("Execute DateStart Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByDateStartContaining(datestart);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByDateEndContaining(Date dateend) {
        logger.info("Execute DateEnd Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByDateEndContaining(dateend);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByProcesCodeContaining(String procescode) {
        logger.info("Execute ProcesCode Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByProcesCodeContaining(procescode);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }




    @Override
    public List<InstanceStageModel> findByTitleContaining(String title) {
        logger.info("Execute Title Containing");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        List<InstanceStage> listaSInstanceStage = instancestagerepository.findByTitleContaining(title);
        listaSInstanceStage.forEach(instancestagex -> listaInstanceStage.add(instancestageMapper.entityToPojo(instancestagex)));
        return listaInstanceStage;
    }


    @Override
    public List<InstanceStageModel> findByInstanceStageContaining(InstanceStage instanceStages) {
        logger.info(" Execute InstanceStage Containing ");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        for (InstanceStageModel instancestage : this.getAllInstanceStage()) {
            for (InstanceStageModel instanceStagesx : instancestage.getinstanceStages()) {
                if (instanceStagesx.equals(instanceStages)) {
                    listaInstanceStage.add(instancestage);
                }
            }
        }
        return listaInstanceStage;

    }


    @Override
    public List<InstanceStageModel> findByAssignedContaining(Assigned assigned) {
        logger.info(" Execute Assigned Containing ");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        for (InstanceStageModel instancestage : this.getAllInstanceStage()) {
            for (AssignedModel assignedx : instancestage.getassigned()) {
                if (assignedx.equals(assigned)) {
                    listaInstanceStage.add(instancestage);
                }
            }
        }
        return listaInstanceStage;

    }



    @Override
    public List<InstanceStageModel> findByInstanceTaskContaining(InstanceTask instancesTasks) {
        logger.info(" Execute InstanceTask Containing ");
        List<InstanceStageModel> listaInstanceStage = new ArrayList<InstanceStageModel>();
        for (InstanceStageModel instancestage : this.getAllInstanceStage()) {
            for (InstanceTaskModel instancesTasksx : instancestage.getinstancesTasks()) {
                if (instancesTasksx.equals(instancesTasks)) {
                    listaInstanceStage.add(instancestage);
                }
            }
        }
        return listaInstanceStage;

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


}
