
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

package com.bpm.engine.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


public class TaskEmailModel{

		private Long idEmail;

		private String emailFrom;

		private String nota;

		private String templeCode;

		private String emailTo;

		private String emailCC;

		private Integer numberRepete;

		private Boolean repete;

		public Long getIdEmail() { 
		return idEmail;
		}
		public void setIdEmail(Long  idEmail) {
			this.idEmail = idEmail;
		}
		public String getEmailFrom() { 
		return emailFrom;
		}
		public void setEmailFrom(String  emailFrom) {
			this.emailFrom = emailFrom;
		}
		public String getNota() { 
		return nota;
		}
		public void setNota(String  nota) {
			this.nota = nota;
		}
		public String getTempleCode() { 
		return templeCode;
		}
		public void setTempleCode(String  templeCode) {
			this.templeCode = templeCode;
		}
		public String getEmailTo() { 
		return emailTo;
		}
		public void setEmailTo(String  emailTo) {
			this.emailTo = emailTo;
		}
		public String getEmailCC() { 
		return emailCC;
		}
		public void setEmailCC(String  emailCC) {
			this.emailCC = emailCC;
		}
		public Integer getNumberRepete() { 
		return numberRepete;
		}
		public void setNumberRepete(Integer  numberRepete) {
			this.numberRepete = numberRepete;
		}
		public Boolean getRepete() { 
		return repete;
		}
		public void setRepete(Boolean  repete) {
			this.repete = repete;
		}
		public boolean equals(Object o) {
			if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					TaskEmailModel	taskemailmodel = (TaskEmailModel) o;
					return 			Objects.equals(idEmail, taskemailmodel.idEmail) ||
			Objects.equals(emailFrom, taskemailmodel.emailFrom) ||
			Objects.equals(nota, taskemailmodel.nota) ||
			Objects.equals(templeCode, taskemailmodel.templeCode) ||
			Objects.equals(emailTo, taskemailmodel.emailTo) ||
			Objects.equals(emailCC, taskemailmodel.emailCC) ||
			Objects.equals(numberRepete, taskemailmodel.numberRepete) ||
			Objects.equals(repete, taskemailmodel.repete);

}}
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

