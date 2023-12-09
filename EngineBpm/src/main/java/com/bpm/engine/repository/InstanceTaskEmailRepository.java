
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


package com.bpm.engine.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.bpm.engine.entitys.InstanceTaskEmail;

public interface InstanceTaskEmailRepository extends CrudRepository< InstanceTaskEmail, Long> {
 
		public Optional<InstanceTaskEmail> findByEmailTo(String emailTo);
		public List<InstanceTaskEmail> findByEmailToContaining(String emailTo);
		public Optional<InstanceTaskEmail> findByEmailCC(String emailCC);
		public List<InstanceTaskEmail> findByEmailCCContaining(String emailCC);
		public Optional<InstanceTaskEmail> findByEmailFrom(String emailFrom);
		public List<InstanceTaskEmail> findByEmailFromContaining(String emailFrom);
		public Optional<InstanceTaskEmail> findByNota(String nota);
		public List<InstanceTaskEmail> findByNotaContaining(String nota);
		public Optional<InstanceTaskEmail> findByTempleCode(String templeCode);
		public List<InstanceTaskEmail> findByTempleCodeContaining(String templeCode);
		public Optional<InstanceTaskEmail> findByDateCreate(Date dateCreate);
		public List<InstanceTaskEmail> findByDateCreateContaining(Date dateCreate);
		public Optional<InstanceTaskEmail> findByDateEnd(Date dateEnd);
		public List<InstanceTaskEmail> findByDateEndContaining(Date dateEnd);
		public Optional<InstanceTaskEmail> findByDateSent(Date dateSent);
		public List<InstanceTaskEmail> findByDateSentContaining(Date dateSent);
		public Optional<InstanceTaskEmail> findByRepete(Boolean repete);
		public List<InstanceTaskEmail> findByRepeteContaining(Boolean repete);
		public Optional<InstanceTaskEmail> findByNumberRepete(Integer numberRepete);
		public List<InstanceTaskEmail> findByNumberRepeteContaining(Integer numberRepete);

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


