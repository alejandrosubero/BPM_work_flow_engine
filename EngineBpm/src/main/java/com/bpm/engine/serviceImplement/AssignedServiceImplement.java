
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

import com.bpm.engine.service.AssignedService;
import com.bpm.engine.repository.AssignedRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bpm.engine.service.BpmAssignedService;
import com.bpm.engine.service.TaskAssignedService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.mappers.AssignedMapper;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.TaskAssignedModel;


@Service
public class AssignedServiceImplement implements AssignedService {

	 private static final Logger logger = LogManager.getLogger(AssignedServiceImplement.class);
	 
    @Autowired
    private AssignedRepository assignedrepository;

    @Autowired
    private AssignedMapper assignedMapper;

    @Autowired
    private BpmAssignedService bpmAssignedService;


    @Override
    public AssignedModel findById(Long id) {
        return assignedMapper.entityToPojo(assignedrepository.findById(id).get());
    }
    
    
    @Override
    public AssignedModel findByName(String name) {

        logger.info("Starting getAssigned");
        Assigned assignedEntity = new Assigned();
        Optional<Assigned> fileOptional1 = assignedrepository.findByName(name);
        if (fileOptional1.isPresent()) {
            try {
                assignedEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return assignedMapper.entityToPojo(assignedEntity);
    }

    
    @Override
    public AssignedModel findByCodeEmployee(String codeEmployee) {
        logger.info("Starting find Assigned By Code Employee ");
        Assigned assignedEntity = new Assigned();
        Optional<Assigned> fileOptional1 = assignedrepository.findByCodeEmployee(codeEmployee);

        if (fileOptional1.isPresent()) {
            try {
                assignedEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return null;
            }
        }
        return assignedMapper.entityToPojo(assignedEntity);
    }


    @Override
    public List<AssignedModel> getAllAssigned() {
        logger.info("execute> getAllAssignedGet allProyect");
        List<AssignedModel> listaAssigned = new ArrayList<AssignedModel>();
        assignedrepository.findAll().forEach(assigned -> listaAssigned.add(assignedMapper.entityToPojo(assigned)));
        return listaAssigned;
    }



    @Override
    public boolean saveAssigned(AssignedModel assigned) {
        logger.info("Save Proyect");
        try {
            assignedrepository.save(assignedMapper.pojoToEntity(assigned));
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }



    @Override
    public AssignedModel saveOrUpdateAssigned(AssignedModel assigned) {
    	logger.info("Update Proyect");
        AssignedModel clave = null;
        if(assigned!=null) {
            Optional<Assigned> fileOptional2 = this.assignedrepository.findById(assigned.getId());
            if (fileOptional2.isPresent()) {
            	AssignedModel fileDataBase = assignedMapper.entityToPojo(fileOptional2.get());
            	logger.info(" Update this model...");
            	fileDataBase.updateThis(assigned);
            	 clave =  saveAndFormat(fileDataBase);
                logger.info(" save update model ...");
            } else {
                clave =  saveAndFormat(assigned);
                logger.info(" model is save");
            }
        }
        return clave;
    }


    private AssignedModel saveAndFormat(AssignedModel model) {
	   logger.info("save And Format...... ");
	   
	   AssignedModel entityResponse = null;
        try {
        	entityResponse = assignedMapper.entityToPojo(assignedrepository.save(assignedMapper.pojoToEntity(model)));
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            logger.error("Error happen during the direct saved of assigned... ");
            e.printStackTrace();
            //TODO: registrar en el sistema de notificacion error and set logger
            return entityResponse;
        }
    			
    	return entityResponse;
    }
    
    
    


    @Override
    public List<AssignedModel> findByNameContaining(String name) {
        logger.info("Execute Name Containing");
        List<AssignedModel> listaAssigned = new ArrayList<AssignedModel>();
        List<Assigned> listaSAssigned = assignedrepository.findByNameContaining(name);
        listaSAssigned.forEach(assignedx -> listaAssigned.add(assignedMapper.entityToPojo(assignedx)));
        return listaAssigned;
    }


    @Override
    public List<AssignedModel> findByCodeEmployeeContaining(String codeemployee) {
        logger.info("Execute CodeEmployee Containing");
        List<AssignedModel> listaAssigned = new ArrayList<AssignedModel>();
        List<Assigned> listaSAssigned = assignedrepository.findByCodeEmployeeContaining(codeemployee);
        listaSAssigned.forEach(assignedx -> listaAssigned.add(assignedMapper.entityToPojo(assignedx)));
        return listaAssigned;
    }


    @Override
    public List<AssignedModel> findByRelacionRole(Role role) {
        logger.info(" Execute Role findByRelacion");

        List<AssignedModel> listaAssigned =  assignedMapper.entityListToPojoList(
                        assignedrepository.findByEmployeeRole(role)).stream()
                .filter(assignedModel -> assignedModel.getemployeeRole().equals(role))
                .collect(Collectors.toList());
        return listaAssigned;
    }

    
    @SuppressWarnings("finally")
	@Override
    public AssignedModel findByCodeEmployeeAndActive(String codeEmployee, Boolean active) {
      
    	AssignedModel assignedModel = null;
        try {
        	  Optional<Assigned> fileOptional2 = assignedrepository.findByCodeEmployeeAndActive(codeEmployee, active);
              if(fileOptional2.isPresent()){
            	  assignedModel = assignedMapper.entityToPojo(fileOptional2.get());
              }
        }catch( DataAccessException e) {
			 logger.error("Error in find a AssignedModel by codeEmployee: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the codeEmployee parameters are null");
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("Error in findByCodeEmployeeAndActive...", e);
			e.printStackTrace();
		}finally {
			return assignedModel;
		}
        
    }

    @Override
    public List<AssignedModel> findByTaskAssignedModel(List<TaskAssignedModel> taskAssignedList){
        List<AssignedModel> listTaskAssignedModel = new ArrayList<>();
        try {
            taskAssignedList.stream().forEach(taskAssignedModel ->
                    listTaskAssignedModel.add( assignedMapper.entityToPojo(
                                    assignedrepository.findById(bpmAssignedService.findByIdBpmAssigned(
                                                    taskAssignedModel.getIdBpmAssigned()).getIdAssigned()).get()))
            );
            
        }catch (Exception e){
            e.printStackTrace();
           // TODO: START A NOTIFICATION FOR ERROR
            return listTaskAssignedModel;
        }
        return listTaskAssignedModel;
    }

	@Override
	public Boolean checkCodeEmployeeExists(String codeEmployee) {
		  	Integer result = assignedrepository.checkCodeEmployeeExists(codeEmployee);
	        return result != null;
		
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