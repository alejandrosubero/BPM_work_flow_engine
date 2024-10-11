
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

import com.bpm.engine.interfaces.RadomCode;
import com.bpm.engine.mappers.ProcessMapper;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.repository.ProcessRepository;

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
import com.bpm.engine.entitys.Process;
import com.bpm.engine.entitys.Stage;


@Service
public class ProcessServiceImplement implements ProcessService, RadomCode {

	private static final Logger logger = LogManager.getLogger(ProcessServiceImplement.class);
    @Autowired
    private ProcessRepository processrepository;

    @Autowired
    private ProcessMapper processMapper;


    @Override
    public ProcessModel findByName(String name) {

        logger.info("Starting getProcess");
        Process processEntity = new Process();
        Optional<Process> fileOptional1 = processrepository.findByName(name);

        if (fileOptional1.isPresent()) {
            try {
                processEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return processMapper.entityToPojo(processEntity);
    }

    @Override
    public ProcessModel findByCreateDate(Date createDate) {
        logger.info("Starting getProcess");
        Process processEntity = new Process();
        Optional<Process> fileOptional1 = processrepository.findByCreateDate(createDate);
        if (fileOptional1.isPresent()) {
            try {
                processEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return processMapper.entityToPojo(processEntity);
    }

  


    @Override
    public ProcessModel findByProcesTitle(String procesTitle) {
        logger.info("Starting getProcess");
        Process processEntity = new Process();
        Optional<Process> fileOptional1 = processrepository.findByProcesTitle(procesTitle);
        if (fileOptional1.isPresent()) {
            try {
                processEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return processMapper.entityToPojo(processEntity);
    }





    @Override
    public ProcessModel findByProcesCode(String procesCode) {
        logger.info("Starting findByProcesCode");
        Process processEntity = new Process();
        Optional<Process> fileOptional1 = processrepository.findByProcesCode(procesCode);
        if (fileOptional1.isPresent()) {
            try {
                processEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return processMapper.entityToPojo(processEntity);
    }





    @Override
    public ProcessModel findByActivo(Boolean activo) {
        logger.info("Starting findByActivo");
        Process processEntity = new Process();
        Optional<Process> fileOptional1 = processrepository.findByActivo(activo);
        if (fileOptional1.isPresent()) {
            try {
                processEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return processMapper.entityToPojo(processEntity);
    }

    @Override
    public List<ProcessModel> getAllProcess() {
        logger.info("execute> getAllProcessGet allProyect");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        processrepository.findAll().forEach(process -> listaProcess.add(processMapper.entityToPojo(process)));
        return listaProcess;
    }

    @Override
    public boolean saveProcess(Process process) {
        logger.info("Save Process");
        try {
            processrepository.save(process);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }

    @Override
    public ProcessModel save(Process process) {
        logger.info("Save Process");
        try {
            return processMapper.entityToPojo(processrepository.save(process));
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return new ProcessModel();
        }
    }

    
    @Override
    public ProcessModel save(ProcessModel process) {
        logger.info("Save Process");
        try {
            return processMapper.entityToPojo(processrepository.save(processMapper.pojoToEntity(process)));
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return new ProcessModel();
        }
    }
    
    
    
    @Override
    public boolean updateProcess(Process process) {
        logger.info("Update ENTITY");
        boolean clave = false;
        Process empre = processrepository.findById(process.getId_process()).get();
        empre = process;
        try {
            processrepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }

    @Override
    public ProcessModel update(Process process) {
        logger.info("Update ENTITY");
        Process empre = null;
        try {
            empre = processrepository.save(process);
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            empre = new Process();
        }
        return processMapper.entityToPojo(empre);

    }


    @Override
    public ProcessModel findById(Long id) {
        return processMapper.entityToPojo(processrepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateProcess(Process process) {
        logger.info("save Or Update Process");
        boolean clave = false;
        Optional<Process> fileOptional2 = processrepository.findById(process.getId_process());
        if (fileOptional2.isPresent()) {
            clave = this.updateProcess(process);
            logger.info(" is update");
        } else {
            clave = this.saveProcess(process);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<ProcessModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByNameContaining(name);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByCreateDateContaining(Date createdate) {
        logger.info("Execute CreateDate Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByCreateDateContaining(createdate);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByUserCreateContaining(String usercreate) {
        logger.info("Execute UserCreate Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByUserCreateContaining(usercreate);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByProcesTitleContaining(String procestitle) {
        logger.info("Execute ProcesTitle Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByProcesTitleContaining(procestitle);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByStateContaining(String state) {
        logger.info("Execute State Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByStateContaining(state);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByProcesCodeContaining(String procescode) {
        logger.info("Execute ProcesCode Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByProcesCodeContaining(procescode);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByVisibleContaining(Boolean visible) {
        logger.info("Execute Visible Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByVisibleContaining(visible);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByGlobalContaining(Boolean global) {
        logger.info("Execute Global Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByGlobalContaining(global);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByActivoContaining(Boolean activo) {
        logger.info("Execute Activo Containing");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        List<Process> listaSProcess = processrepository.findByActivoContaining(activo);
        listaSProcess.forEach(processx -> listaProcess.add(processMapper.entityToPojo(processx)));
        return listaProcess;
    }


    @Override
    public List<ProcessModel> findByStageContaining(Stage stages) {
        logger.info(" Execute Stage Containing ");
        List<ProcessModel> listaProcess = new ArrayList<ProcessModel>();
        for (ProcessModel process : this.getAllProcess()) {
            for (StageModel stagesx : process.getstages()) {
                if (stagesx.equals(stages)) {
                    listaProcess.add(process);
                }
            }
        }
        return listaProcess;

    }

	@Override
	public List<ProcessModel> findAllByRoleCodeRole(String codeRole) {
		try {
			return processMapper.entityListToPojoList(processrepository.findAllByRoleCodeRole(codeRole));
		}catch(Exception e){
			 logger.error(" ERROR : " + e);
			e.printStackTrace();
		}
		return null;
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
