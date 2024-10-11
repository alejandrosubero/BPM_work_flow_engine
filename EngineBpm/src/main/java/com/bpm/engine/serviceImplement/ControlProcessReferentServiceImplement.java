
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


package com.bpm.engine.serviceImplement;

import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.repository.ControlProcessReferentRepository;

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
import com.bpm.engine.entitys.ControlProcessReferent;
import com.bpm.engine.mappers.ControlProcessReferentMapper;
import com.bpm.engine.models.ControlProcessReferentModel;


@Service
public class ControlProcessReferentServiceImplement implements ControlProcessReferentService {

//    protected static final Log logger = LogFactory.getLog(ControlProcessReferentServiceImplement.class);
	 private static final Logger logger = LogManager.getLogger(ControlProcessReferentServiceImplement.class);
	
	
    @Autowired
    private ControlProcessReferentRepository controlprocessrepository;

    @Autowired
    private ControlProcessReferentMapper controlprocessMapper;


    @Override
    public ControlProcessReferentModel findById(Long id) {
        return controlprocessMapper.entityToPojo(controlprocessrepository.findById(id).get());
    }

    @Override
    public boolean saveControlProcess(ControlProcessReferent controlprocess) {
        logger.info("Save Proyect");
        try {
            controlprocessrepository.save(controlprocess);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }

    @Override
    public boolean updateControlProcess(ControlProcessReferent controlprocess) {
        logger.info("Update ENTITY");
        boolean clave = false;
        ControlProcessReferent entity = null;
        try {
            Optional<ControlProcessReferent> empre = controlprocessrepository.findById(controlprocess.getId());
            if (empre.isPresent()) {
                entity = empre.get();
                entity = controlprocess;
            }
            controlprocessrepository.save(entity);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public boolean saveOrUpdateControlProcess(ControlProcessReferent controlprocess) {
        logger.info("Control Process Referen saveOrUpdate");
        boolean clave = false;
        if (controlprocess.getId() != null) {
            clave = this.updateControlProcess(controlprocess);
            logger.info(" Control Process Referen is update");
        } else {
            clave = this.saveControlProcess(controlprocess);
            logger.info("Control Process Referen is save a new");
        }
        return clave;
    }


    @Override
    public ControlProcessReferentModel saveOrUpdateInternalControlProcess(ControlProcessReferentModel controlprocess) {
        logger.info("Control Process Referen save Or Update Internal...");
        try {
            ControlProcessReferent controlProcessReferent = controlprocessrepository.save(controlprocessMapper.pojoToEntity(controlprocess));
            if (controlProcessReferent != null) {
                return controlprocessMapper.entityToPojo(controlProcessReferent);
            }
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
        return null;
    }


    @Override
    public ControlProcessReferentModel findByCode(String code) {
        logger.info("Control Process Referent findByCode");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByCode(code);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByName(String name) {
        logger.info("Control Process Referent find By Name");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByName(name);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByTitle(String title) {
        logger.info("Control Process Referent find By Title");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByTitle(title);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByStatus(String status) {

        logger.info("Control Process Referent find By Status");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByStatus(status);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByType(String type) {

        logger.info("Control Process Referent find By type");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByType(type);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByIdReference(Long idReference) {

        logger.info("Control Process Referent find By Reference id");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByIdReference(idReference);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByActive(Boolean active) {

        logger.info("Control Process Referent find By Active");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByActive(active);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByDateCreate(Date dateCreate) {
        logger.info("Control Process Referent find By date create");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByDateCreate(dateCreate);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }

    @Override
    public ControlProcessReferentModel findByDateClose(Date dateClose) {

        logger.info("Control Process Referent find By date close");
        ControlProcessReferent controlprocessEntity = new ControlProcessReferent();
        Optional<ControlProcessReferent> fileOptional1 = controlprocessrepository.findByDateClose(dateClose);
        if (fileOptional1.isPresent()) {
            try {
                controlprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return controlprocessMapper.entityToPojo(controlprocessEntity);
    }


    @Override
    public List<ControlProcessReferentModel> getAllControlProcess() {
        logger.info("Control Process Referent execute  getAll");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        controlprocessrepository.findAll().forEach(controlprocess -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocess)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByCodeContaining(String code) {
        logger.info("Execute Code Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByCodeContaining(code);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByNameContaining(name);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByTitleContaining(String title) {
        logger.info("Execute Title Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByTitleContaining(title);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByStatusContaining(String status) {
        logger.info("Execute Status Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByStatusContaining(status);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByTypeContaining(String type) {
        logger.info("Execute Type Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByTypeContaining(type);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByIdReferenceContaining(Long idreference) {
        logger.info("Execute IdReference Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByIdReferenceContaining(idreference);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByActiveContaining(Boolean active) {
        logger.info("Execute Active Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByActiveContaining(active);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByDateCreateContaining(Date datecreate) {
        logger.info("Execute DateCreate Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByDateCreateContaining(datecreate);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
    }


    @Override
    public List<ControlProcessReferentModel> findByDateCloseContaining(Date dateclose) {
        logger.info("Execute DateClose Containing");
        List<ControlProcessReferentModel> listaControlProcess = new ArrayList<ControlProcessReferentModel>();
        List<ControlProcessReferent> listaSControlProcess = controlprocessrepository.findByDateCloseContaining(dateclose);
        listaSControlProcess.forEach(controlprocessx -> listaControlProcess.add(controlprocessMapper.entityToPojo(controlprocessx)));
        return listaControlProcess;
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
