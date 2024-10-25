
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

package com.bpm.engine.service;

import java.util.Optional;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.bpm.engine.entitys.InstanceTaskEmail;
import com.bpm.engine.models.InstanceTaskEmailModel;

public interface InstanceTaskEmailService {

	public InstanceTaskEmailModel findByEmailTo(String emailTo);

	public InstanceTaskEmailModel findByEmailCC(String emailCC);

	public InstanceTaskEmailModel findByEmailFrom(String emailFrom);

	public InstanceTaskEmailModel findByNota(String nota);

	public InstanceTaskEmailModel findByTempleCode(String templeCode);

	public InstanceTaskEmailModel findByDateCreate(Date dateCreate);

	public InstanceTaskEmailModel findByDateEnd(Date dateEnd);

	public InstanceTaskEmailModel findByDateSent(Date dateSent);

	public InstanceTaskEmailModel findByRepete(Boolean repete);

	public InstanceTaskEmailModel findByNumberRepete(Integer numberRepete);

	public List<InstanceTaskEmailModel> findByEmailToContaining(String emailTo);

	public List<InstanceTaskEmailModel> findByEmailCCContaining(String emailCC);

	public List<InstanceTaskEmailModel> findByEmailFromContaining(String emailFrom);

	public List<InstanceTaskEmailModel> findByNotaContaining(String nota);

	public List<InstanceTaskEmailModel> findByTempleCodeContaining(String templeCode);

	public List<InstanceTaskEmailModel> findByDateCreateContaining(Date dateCreate);

	public List<InstanceTaskEmailModel> findByDateEndContaining(Date dateEnd);

	public List<InstanceTaskEmailModel> findByDateSentContaining(Date dateSent);

	public List<InstanceTaskEmailModel> findByRepeteContaining(Boolean repete);

	public List<InstanceTaskEmailModel> findByNumberRepeteContaining(Integer numberRepete);

	public InstanceTaskEmailModel findById(Long id);

	public boolean saveInstanceTaskEmail(InstanceTaskEmail instancetaskemail);

	public List<InstanceTaskEmailModel> getAllInstanceTaskEmail();

	public boolean updateInstanceTaskEmail(InstanceTaskEmail instancetaskemail);

	public boolean saveOrUpdateInstanceTaskEmail(InstanceTaskEmail instancetaskemail);

}
/*
 * Copyright (C) 2008 Google Inc. Licensed to the Apache Software Foundation
 * (ASF) under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
