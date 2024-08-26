
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

import com.bpm.engine.service.InstanceProcessService;
import com.bpm.engine.repository.InstanceProcessRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.bpm.engine.service.InstanceTaskService;
import com.bpm.engine.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.InstanceProcess;
import com.bpm.engine.model.InstanceProcessModel;
import com.bpm.engine.mapper.InstanceProcessMapper;
import com.bpm.engine.entitys.InstanceStage;
import com.bpm.engine.model.InstanceStageModel;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.model.AssignedModel;


@Service
public class InstanceProcessServiceImplement implements InstanceProcessService {

    protected static final Log logger = LogFactory.getLog(InstanceProcessServiceImplement.class);
    @Autowired
    private InstanceProcessRepository instanceprocessrepository;

    @Autowired
    private InstanceProcessMapper instanceprocessMapper;

    @Autowired
    private TaskService taskService;

    @Override
    public InstanceProcessModel findByName(String name) {
        logger.info("FindByName Instance Process ");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByName(name);

        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByCreateBy(String createBy) {
        logger.info("findByCreate Instance Process");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByCreateBy(createBy);

        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByState(String state) {
        logger.info("findByState Instance Process");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByState(state);

        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByDateCreate(Date dateCreate) {
        logger.info("findByDateCreate Instance Process");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByDateCreate(dateCreate);

        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByDateEnd(Date dateEnd) {
        logger.info("findByDateEnd InstanceProcess");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByDateEnd(dateEnd);
        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByInstanceCode(String instanceCode) {
        logger.info("findByInstanceCode Instance Process");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByInstanceCode(instanceCode);
        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }

    @Override
    public InstanceProcessModel findByTitle(String title) {
        logger.info("findByTitle Instance Process");
        InstanceProcess instanceprocessEntity = new InstanceProcess();
        Optional<InstanceProcess> fileOptional1 = instanceprocessrepository.findByTitle(title);
        if (fileOptional1.isPresent()) {
            try {
                instanceprocessEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return instanceprocessMapper.entityToPojo(instanceprocessEntity);
    }


    @Override
    public List<InstanceProcessModel> getAllInstanceProcess() {
        logger.info("execute> getAllInstanceProcessGet allProyect");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        instanceprocessrepository.findAll().forEach(instanceprocess -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocess)));
        return listaInstanceProcess;
    }




    @Override
    public boolean saveInstanceProcess(InstanceProcess instanceprocess) {
        logger.info("Save Instance Process");
        try {
            instanceprocessrepository.save(instanceprocess);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }

    @Override
    public InstanceProcessModel updateInstanceProcessII(InstanceProcessModel instanceProcessModel) {
        logger.info("Update type II for Instance Process");
        InstanceProcessModel model = null;
        try {
            InstanceProcess entity = instanceprocessrepository.save(instanceprocessMapper.pojoToEntity(instanceProcessModel));
            if(entity != null){
                model = instanceprocessMapper.entityToPojo(entity);
//         ## InstanceProcessModel Stages
                model.getinstanceStage().forEach(instanceStageModel -> {
                    instanceStageModel.getinstancesTasks().forEach(instanceTaskModel ->
                        instanceTaskModel.setTask(taskService.findByCode(instanceTaskModel.getCodeTask())));
//        ## instance_Stage in instance_Stage internal
                    instanceStageModel.getinstanceStages().forEach(instanceStageModel1 ->
                                    instanceStageModel1.getinstancesTasks().forEach(instanceTaskModel ->
                                            instanceTaskModel.setTask(taskService.findByCode(instanceTaskModel.getCodeTask()))));
                });

            }
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
           return null;
        }
        return model;
    }

    @Override
    public boolean updateInstanceProcess(InstanceProcess instanceprocess) {
        logger.info("Update Instance Process");
        boolean clave = false;
        InstanceProcess empre = instanceprocessrepository.findById(instanceprocess.getIdInstanceProcess()).get();
        empre = instanceprocess;
        try {
            instanceprocessrepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public InstanceProcessModel findById(Long id) {
        return instanceprocessMapper.entityToPojo(instanceprocessrepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateInstanceProcess(InstanceProcess instanceprocess) {
        boolean clave = false;
        Optional<InstanceProcess> fileOptional2= null;
        if(instanceprocess.getIdInstanceProcess() != null){
            fileOptional2 = instanceprocessrepository.findById(instanceprocess.getIdInstanceProcess());
            if (fileOptional2.isPresent()) {
                clave = this.updateInstanceProcess(instanceprocess);
                logger.info("Update Instance Process");
            }
        } else {
            clave = this.saveInstanceProcess(instanceprocess);
            logger.info("Save new Instance Process");
        }
        return clave;
    }

    @Override
    public InstanceProcessModel saveInternal(InstanceProcessModel instanceprocess) {
        InstanceProcessModel instanceProcessModel = null;
        try {
            if(instanceprocess != null){
              InstanceProcess process = instanceprocessrepository.save(instanceprocessMapper.pojoToEntity(instanceprocess));
                if(process!= null){
                    instanceProcessModel =  instanceprocessMapper.entityToPojo(process);
                }
            }
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
        return instanceProcessModel;
    }


    @Override
    public List<InstanceProcessModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByNameContaining(name);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByCreateByContaining(String createby) {
        logger.info("Execute CreateBy Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByCreateByContaining(createby);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByStateContaining(String state) {
        logger.info("Execute State Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByStateContaining(state);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByDateCreateContaining(Date datecreate) {
        logger.info("Execute DateCreate Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByDateCreateContaining(datecreate);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByDateEndContaining(Date dateend) {
        logger.info("Execute DateEnd Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByDateEndContaining(dateend);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByInstanceCodeContaining(String instancecode) {
        logger.info("Execute InstanceCode Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByInstanceCodeContaining(instancecode);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByTitleContaining(String title) {
        logger.info("Execute Title Containing");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        List<InstanceProcess> listaSInstanceProcess = instanceprocessrepository.findByTitleContaining(title);
        listaSInstanceProcess.forEach(instanceprocessx -> listaInstanceProcess.add(instanceprocessMapper.entityToPojo(instanceprocessx)));
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByInstanceStageContaining(InstanceStage instanceStage) {
        logger.info(" Execute InstanceStage Containing ");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        for (InstanceProcessModel instanceprocess : this.getAllInstanceProcess()) {
            for (InstanceStageModel instanceStagex : instanceprocess.getinstanceStage()) {
                if (instanceStagex.equals(instanceStage)) {
                    listaInstanceProcess.add(instanceprocess);
                }
            }
        }
        return listaInstanceProcess;

    }

    @Override
    public List<InstanceProcessModel> findByRelacionProcess(Process process) {
        logger.info(" Execute Process findByRelacion");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        for (InstanceProcessModel instanceprocess : this.getAllInstanceProcess()) {
            if (instanceprocess.getprocess().equals(process)) {
                listaInstanceProcess.add(instanceprocess);
            }
        }
        return listaInstanceProcess;
    }


    @Override
    public List<InstanceProcessModel> findByAssignedContaining(Assigned assigned) {
        logger.info(" Execute Assigned Containing ");
        List<InstanceProcessModel> listaInstanceProcess = new ArrayList<InstanceProcessModel>();
        for (InstanceProcessModel instanceprocess : this.getAllInstanceProcess()) {
            for (AssignedModel assignedx : instanceprocess.getassigned()) {
                if (assignedx.equals(assigned)) {
                    listaInstanceProcess.add(instanceprocess);
                }
            }
        }
        return listaInstanceProcess;
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