
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



package com.bpm.engine.serviceImplement ;

import com.bpm.engine.service.InstanceTaskEmailService;
import com.bpm.engine.repository.InstanceTaskEmailRepository;
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
import com.bpm.engine.entitys.InstanceTaskEmail;
import com.bpm.engine.mappers.InstanceTaskEmailMapper;
import com.bpm.engine.models.InstanceTaskEmailModel;




@Service
public class InstanceTaskEmailServiceImplement implements InstanceTaskEmailService {

	private static final Logger logger = LogManager.getLogger(InstanceTaskEmailServiceImplement.class);
	
@Autowired
private InstanceTaskEmailRepository instancetaskemailrepository;

		@Autowired
		private	InstanceTaskEmailMapper	instancetaskemailMapper;


		@Override
		public	InstanceTaskEmailModel	findByEmailTo(String	emailTo){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByEmailTo(emailTo);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByEmailCC(String	emailCC){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByEmailCC(emailCC);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByEmailFrom(String	emailFrom){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByEmailFrom(emailFrom);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByNota(String	nota){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByNota(nota);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByTempleCode(String	templeCode){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByTempleCode(templeCode);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByDateCreate(Date	dateCreate){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByDateCreate(dateCreate);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByDateEnd(Date	dateEnd){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByDateEnd(dateEnd);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByDateSent(Date	dateSent){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByDateSent(dateSent);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByRepete(Boolean	repete){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByRepete(repete);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}
		@Override
		public	InstanceTaskEmailModel	findByNumberRepete(Integer	numberRepete){

		logger.info("Starting getInstanceTaskEmail");
		InstanceTaskEmail	instancetaskemailEntity = new InstanceTaskEmail();
		Optional<InstanceTaskEmail> fileOptional1 = instancetaskemailrepository.findByNumberRepete(numberRepete);

		if (fileOptional1.isPresent()) { 

				try {
			instancetaskemailEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return	instancetaskemailMapper.entityToPojo(instancetaskemailEntity);	}




		@Override
		public List<InstanceTaskEmailModel> getAllInstanceTaskEmail(){
		logger.info("execute> getAllInstanceTaskEmailGet allProyect");
			List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
				instancetaskemailrepository.findAll().forEach(instancetaskemail -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemail)));
			return listaInstanceTaskEmail;
	}


		@Override
		public boolean saveInstanceTaskEmail(InstanceTaskEmail instancetaskemail){
		logger.info("Save Proyect");


				try {
				instancetaskemailrepository.save(instancetaskemail);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}




		@Override
		public boolean updateInstanceTaskEmail(InstanceTaskEmail	instancetaskemail ){
			logger.info("Update ENTITY");
			boolean clave = false;
		InstanceTaskEmail empre = instancetaskemailrepository.findById(instancetaskemail.getId()).get();
			empre = instancetaskemail;

				try {
			instancetaskemailrepository.save(empre);
						clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			clave = false;
				}

					return clave;
	}



		@Override
		public	InstanceTaskEmailModel findById( Long id){
				return  instancetaskemailMapper.entityToPojo(instancetaskemailrepository.findById(id).get());
		}



		@Override
		public boolean saveOrUpdateInstanceTaskEmail(InstanceTaskEmail  instancetaskemail ){
			logger.info("Update Proyect");
			boolean clave = false;
	Optional<InstanceTaskEmail> fileOptional2 = instancetaskemailrepository.findById(instancetaskemail.getId());
			if (fileOptional2.isPresent()) { 
				clave = this.updateInstanceTaskEmail(instancetaskemail);
				logger.info(" is update");
			} else {
					clave = this.saveInstanceTaskEmail(instancetaskemail);
					logger.info(" is save");
 				}
 		return clave;
		}



		@Override
		public List<InstanceTaskEmailModel> findByEmailToContaining(String	emailto){
		logger.info("Execute EmailTo Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByEmailToContaining(emailto);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByEmailCCContaining(String	emailcc){
		logger.info("Execute EmailCC Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByEmailCCContaining(emailcc);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByEmailFromContaining(String	emailfrom){
		logger.info("Execute EmailFrom Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByEmailFromContaining(emailfrom);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByNotaContaining(String	nota){
		logger.info("Execute Nota Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByNotaContaining(nota);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByTempleCodeContaining(String	templecode){
		logger.info("Execute TempleCode Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByTempleCodeContaining(templecode);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByDateCreateContaining(Date	datecreate){
		logger.info("Execute DateCreate Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByDateCreateContaining(datecreate);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByDateEndContaining(Date	dateend){
		logger.info("Execute DateEnd Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByDateEndContaining(dateend);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByDateSentContaining(Date	datesent){
		logger.info("Execute DateSent Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByDateSentContaining(datesent);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByRepeteContaining(Boolean	repete){
		logger.info("Execute Repete Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByRepeteContaining(repete);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
		}


		@Override
		public List<InstanceTaskEmailModel> findByNumberRepeteContaining(Integer	numberrepete){
		logger.info("Execute NumberRepete Containing" );		List<InstanceTaskEmailModel> listaInstanceTaskEmail = new ArrayList<InstanceTaskEmailModel>();
		List<InstanceTaskEmail> listaSInstanceTaskEmail = instancetaskemailrepository.findByNumberRepeteContaining(numberrepete);
		listaSInstanceTaskEmail.forEach(instancetaskemailx -> listaInstanceTaskEmail.add(instancetaskemailMapper.entityToPojo(instancetaskemailx)));
				return listaInstanceTaskEmail;
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
