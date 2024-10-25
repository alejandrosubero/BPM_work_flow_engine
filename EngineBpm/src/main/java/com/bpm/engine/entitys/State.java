
/*
Create on Sat Sep 16 23:15:49 EDT 2023
*Copyright (C) 123.
@author Alejandro Subero
@author open
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Business Project manameng engine  </p>
*/

package com.bpm.engine.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "bpm_state")
public class State {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_state_generator")
    @SequenceGenerator(name = "sequence_mat_id_state_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idState", updatable = true, nullable = false, length = 30)
    private Long idState;

    @Column(name = "theState", updatable = true, nullable = true, length = 200)
    private String theState;

    @Column(name = "code", updatable = true, nullable = true, length = 200)
    private String code;

    public State() {
    }

    public State(String theState) {
        this.theState = theState;
    }

    public State(String theState, String code) {
        this.theState = theState;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public String getTheState() {
        return theState;
    }

    public void setTheState(String theState) {
        this.theState = theState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(idState, state.idState) && Objects.equals(theState, state.theState) && Objects.equals(code, state.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idState, theState, code);
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

