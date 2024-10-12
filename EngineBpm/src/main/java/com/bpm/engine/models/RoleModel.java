
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

import java.util.Objects;

import com.bpm.engine.entitys.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleModel {

	private Long idRole;

	private String description;

	private String name;

	private String codeRole;

	public RoleModel(String description, String name, String codeRole) {
		this.description = description;
		this.name = name;
		this.codeRole = codeRole;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleModel other = (RoleModel) obj;
		return Objects.equals(codeRole, other.codeRole) && Objects.equals(description, other.description)
				&& Objects.equals(idRole, other.idRole) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeRole, description, idRole, name);
	}

	public void updatethis(RoleModel other) {

		if (!Objects.equals(this.codeRole, other.codeRole)) {
			this.codeRole= other.codeRole;
		}
		if (!Objects.equals(this.description, other.description)) {
			this.description = other.description;
		}
		if (!Objects.equals(this.idRole, other.idRole)) {
			this.idRole = other.idRole;
		}
		if (!Objects.equals(this.name, other.name)) {
			this.name = other.name;
		}
	}

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
