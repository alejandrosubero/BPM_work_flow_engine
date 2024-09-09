
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

import com.bpm.engine.service.InstanceTaskService;
import com.bpm.engine.repository.InstanceTaskRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.model.InstanceTaskModel;
import com.bpm.engine.mapper.InstanceTaskMapper;

import com.bpm.engine.entitys.Assigned;


import com.bpm.engine.entitys.Task;


@Service
public class InstanceTaskServiceImplement implements InstanceTaskService {

    protected static final Log logger = LogFactory.getLog(InstanceTaskServiceImplement.class);
    @Autowired
    private InstanceTaskRepository instancetaskrepository;

    @Autowired
    private InstanceTaskMapper instancetaskMapper;


    @Override
    public InstanceTaskModel findByName(String name) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByName(name);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByState(String state) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByState(state);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByDateStart(Date dateStart) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByDateStart(dateStart);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByDateEnd(Date dateEnd) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByDateEnd(dateEnd);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByProcessCode(String processCode) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByProcessCode(processCode);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByInstanceProcessCode(String instanceProcessCode) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByInstanceProcessCode(instanceProcessCode);

        if (fileOptional1.isPresent()) {

            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }

    @Override
    public InstanceTaskModel findByCodeTask(String codeTask) {

        logger.info("Starting getInstanceTask");
        InstanceTask instancetaskEntity = new InstanceTask();
        Optional<InstanceTask> fileOptional1 = instancetaskrepository.findByCodeTask(codeTask);
        if (fileOptional1.isPresent()) {
            try {
                instancetaskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instancetaskMapper.entityToPojo(instancetaskEntity);
    }




    @Override
    public List<InstanceTaskModel> getAllInstanceTask() {
        logger.info("execute> getAllInstanceTaskGet allProyect");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        instancetaskrepository.findAll().forEach(instancetask -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetask)));
        return listaInstanceTask;
    }


    @Override
    public boolean saveInstanceTask(InstanceTask instancetask) {
        logger.info("Save Proyect");
        try {
            instancetaskrepository.save(instancetask);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateInstanceTask(InstanceTask instancetask) {
        logger.info("Update ENTITY");
        boolean clave = false;
        InstanceTask empre = instancetaskrepository.findById(instancetask.getIdInstanceTask()).get();
        empre = instancetask;

        try {
            instancetaskrepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }

        return clave;
    }


    @Override
    public InstanceTaskModel findById(Long id) {
        return instancetaskMapper.entityToPojo(instancetaskrepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateInstanceTask(InstanceTask instancetask) {
        logger.info("Update Proyect");
        boolean clave = false;
        Optional<InstanceTask> fileOptional2 = instancetaskrepository.findById(instancetask.getIdInstanceTask());
        if (fileOptional2.isPresent()) {
            clave = this.updateInstanceTask(instancetask);
            logger.info(" is update");
        } else {
            clave = this.saveInstanceTask(instancetask);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<InstanceTaskModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByNameContaining(name);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByStateContaining(String state) {
        logger.info("Execute State Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByStateContaining(state);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByDateStartContaining(Date datestart) {
        logger.info("Execute DateStart Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByDateStartContaining(datestart);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByDateEndContaining(Date dateend) {
        logger.info("Execute DateEnd Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByDateEndContaining(dateend);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByProcessCodeContaining(String processcode) {
        logger.info("Execute ProcessCode Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByProcessCodeContaining(processcode);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByInstanceProcessCodeContaining(String instanceprocesscode) {
        logger.info("Execute InstanceProcessCode Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByInstanceProcessCodeContaining(instanceprocesscode);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByCodeTaskContaining(String codetask) {
        logger.info("Execute CodeTask Containing");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        List<InstanceTask> listaSInstanceTask = instancetaskrepository.findByCodeTaskContaining(codetask);
        listaSInstanceTask.forEach(instancetaskx -> listaInstanceTask.add(instancetaskMapper.entityToPojo(instancetaskx)));
        return listaInstanceTask;
    }


    @Override
    public List<InstanceTaskModel> findByAssignedContaining(Assigned assignes) {
        logger.info(" Execute Assigned Containing ");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        for (InstanceTaskModel instancetask : this.getAllInstanceTask()) {
//            for (AssignedModel assignesx : instancetask.getassignes()) {
//                if (assignesx.equals(assignes)) {
//                    listaInstanceTask.add(instancetask);
//                }
//            }
        }
        return listaInstanceTask;

    }


    @Override
    public List<InstanceTaskModel> findByRelacionTask(Task task) {
        logger.info(" Execute Task findByRelacion");
        List<InstanceTaskModel> listaInstanceTask = new ArrayList<InstanceTaskModel>();
        for (InstanceTaskModel instancetask : this.getAllInstanceTask()) {
            if (instancetask.getTask().equals(task)) {
                listaInstanceTask.add(instancetask);
            }
        }
        return listaInstanceTask;
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
