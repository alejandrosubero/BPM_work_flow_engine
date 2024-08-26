
/*
Create on Sat Sep 30 10:44:39 EDT 2023
*Copyright (C) 123.
@author Alejandro Subero
@author open
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Business Project Management engine  </p>
*/

package com.bpm.engine.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "bpm_system_response")
public class SystemResponse {

    //TODO: LE FALTA QUIEN RESPONDIO UN ID THE TASK Y USER SI LO HAY O QUE ESPECIFIQUE QUE FUE AUTOMATICA

    @Id
    @GeneratedValue(generator = "sequence_system_id_generator")
    @SequenceGenerator(name = "sequence_system_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "id", updatable = true, nullable = false, length = 25)
    private Long id;

    @Column(name = "response", updatable = true, nullable = true, length = 200)
    private String response;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemResponse that = (SystemResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, response);
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

