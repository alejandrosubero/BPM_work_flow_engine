
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


public class InstanceTaskEmailModel{

		private Long id;

		private String emailTo;

		private String emailCC;

		private String emailFrom;

		private String nota;

		private String templeCode;

		private Date dateCreate;

		private Date dateEnd;

		private Date dateSent;

		private Boolean repete;

		private Integer numberRepete;

		public Long getId() { 
		return id;
		}
		public void setId(Long  id) {
			this.id = id;
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
		public Date getDateCreate() { 
		return dateCreate;
		}
		public void setDateCreate(Date  dateCreate) {
			this.dateCreate = dateCreate;
		}
		public Date getDateEnd() { 
		return dateEnd;
		}
		public void setDateEnd(Date  dateEnd) {
			this.dateEnd = dateEnd;
		}
		public Date getDateSent() { 
		return dateSent;
		}
		public void setDateSent(Date  dateSent) {
			this.dateSent = dateSent;
		}
		public Boolean getRepete() { 
		return repete;
		}
		public void setRepete(Boolean  repete) {
			this.repete = repete;
		}
		public Integer getNumberRepete() { 
		return numberRepete;
		}
		public void setNumberRepete(Integer  numberRepete) {
			this.numberRepete = numberRepete;
		}
		public boolean equals(Object o) {
			if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					InstanceTaskEmailModel	instancetaskemailmodel = (InstanceTaskEmailModel) o;
					return 			Objects.equals(id, instancetaskemailmodel.id) ||
			Objects.equals(emailTo, instancetaskemailmodel.emailTo) ||
			Objects.equals(emailCC, instancetaskemailmodel.emailCC) ||
			Objects.equals(emailFrom, instancetaskemailmodel.emailFrom) ||
			Objects.equals(nota, instancetaskemailmodel.nota) ||
			Objects.equals(templeCode, instancetaskemailmodel.templeCode) ||
			Objects.equals(dateCreate, instancetaskemailmodel.dateCreate) ||
			Objects.equals(dateEnd, instancetaskemailmodel.dateEnd) ||
			Objects.equals(dateSent, instancetaskemailmodel.dateSent) ||
			Objects.equals(repete, instancetaskemailmodel.repete) ||
			Objects.equals(numberRepete, instancetaskemailmodel.numberRepete);

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

