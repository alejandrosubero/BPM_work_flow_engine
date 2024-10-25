
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

import com.bpm.engine.service.TaskService;
import com.bpm.engine.repository.TaskRepository;

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
import com.bpm.engine.entitys.Task;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.mappers.TaskMapper;
import com.bpm.engine.models.TaskModel;


@Service
public class TaskServiceImplement implements TaskService {

	private static final Logger logger = LogManager.getLogger(TaskServiceImplement.class);
    
    @Autowired
    private TaskRepository taskrepository;

    @Autowired
    private TaskMapper taskMapper;


    @Override
    public TaskModel findByTitle(String title) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByTitle(title);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByName(String name) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByName(name);
        if (fileOptional1.isPresent()) {
            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByDateCreate(Date dateCreate) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByDateCreate(dateCreate);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByProcesCode(String procesCode) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByProcesCode(procesCode);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByTaskUrl(String taskUrl) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByTaskUrl(taskUrl);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByUrlService(String urlService) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByUrlService(urlService);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }

    @Override
    public TaskModel findByCode(String code) {

        logger.info("Starting getTask");
        Task taskEntity = new Task();
        Optional<Task> fileOptional1 = taskrepository.findByCode(code);

        if (fileOptional1.isPresent()) {

            try {
                taskEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taskMapper.entityToPojo(taskEntity);
    }


    @Override
    public List<TaskModel> getAllTask() {
        logger.info("execute> getAllTaskGet allProyect");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        taskrepository.findAll().forEach(task -> listaTask.add(taskMapper.entityToPojo(task)));
        return listaTask;
    }


    @Override
    public boolean saveTask(Task task) {
        logger.info("Save Task");
        try {
            taskrepository.save(task);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateTask(Task task) {
        logger.info("Update ENTITY");
        boolean clave = false;
        Task empre = taskrepository.findById(task.getIdTask()).get();
        empre = task;

        try {
            taskrepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }

        return clave;
    }


    @Override
    public TaskModel findById(Long id) {
        return taskMapper.entityToPojo(taskrepository.findById(id).get());
    }


    @Override
    public boolean saveOrUpdateTask(Task task) {
        logger.info("Update Proyect");
        boolean clave = false;
        Optional<Task> fileOptional2 = taskrepository.findById(task.getIdTask());
        if (fileOptional2.isPresent()) {
            clave = this.updateTask(task);
            logger.info(" is update");
        } else {
            clave = this.saveTask(task);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<TaskModel> findByTitleContaining(String title) {
        logger.info("Execute Title Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByTitleContaining(title);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByNameContaining(name);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByDateCreateContaining(Date datecreate) {
        logger.info("Execute DateCreate Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByDateCreateContaining(datecreate);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByProcesCodeContaining(String procescode) {
        logger.info("Execute ProcesCode Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByProcesCodeContaining(procescode);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByTaskUrlContaining(String taskurl) {
        logger.info("Execute TaskUrl Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByTaskUrlContaining(taskurl);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByUrlServiceContaining(String urlservice) {
        logger.info("Execute UrlService Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByUrlServiceContaining(urlservice);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByCodeContaining(String code) {
        logger.info("Execute Code Containing");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        List<Task> listaSTask = taskrepository.findByCodeContaining(code);
        listaSTask.forEach(taskx -> listaTask.add(taskMapper.entityToPojo(taskx)));
        return listaTask;
    }


    @Override
    public List<TaskModel> findByRelacionTaskType(TaskType tasktype) {
        logger.info(" Execute TaskType findByRelacion");
        List<TaskModel> listaTask = new ArrayList<TaskModel>();
        for (TaskModel task : this.getAllTask()) {
            if (task.getType().equals(tasktype)) {
                listaTask.add(task);
            }
        }
        return listaTask;
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
