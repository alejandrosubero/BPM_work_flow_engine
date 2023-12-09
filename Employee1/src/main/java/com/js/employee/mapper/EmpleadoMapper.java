

/*
Create on Mon Aug 08 06:22:32 EDT 2022
*Copyright (C) 122.
@author Alejandro subero
@author JSH
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/


package com.js.employee.mapper;

import com.js.employee.entitys.Empleado;
import com.js.employee.pojo.EmpleadoPojo;
import com.js.employee.entitys.FichaDesempeno;
import com.js.employee.pojo.FichaDesempenoPojo;
import com.js.employee.entitys.FichaPermisos;
import com.js.employee.pojo.FichaPermisosPojo;
import com.js.employee.entitys.FichaSueldo;
import com.js.employee.pojo.FichaSueldoPojo;
import com.js.employee.entitys.FichaPagos;
import com.js.employee.pojo.FichaPagosPojo;
import com.js.employee.entitys.FichaFaltas;
import com.js.employee.pojo.FichaFaltasPojo;
import com.js.employee.entitys.FichaMedica;
import com.js.employee.pojo.FichaMedicaPojo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@Component
public class EmpleadoMapper {

    @Autowired
    private FichaDesempenoMapper fichadesempenomapper;

    @Autowired
    private FichaPermisosMapper fichapermisosmapper;

    @Autowired
    private FichaSueldoMapper fichasueldomapper;

    @Autowired
    private FichaPagosMapper fichapagosmapper;

    @Autowired
    private FichaFaltasMapper fichafaltasmapper;

    @Autowired
    private FichaMedicaMapper fichamedicamapper;


    public List<Empleado> listPojoToListEntity(List<EmpleadoPojo> pojos) {
        List<Empleado> entitys = new ArrayList<>();
        if (pojos != null && pojos.size() > 0) {
            pojos.forEach(empleadoPojo -> entitys.add(this.pojoToEntity(empleadoPojo)));
        }
        return entitys;
    }

    public Empleado pojoToEntity(EmpleadoPojo pojo) {
//        ModelMapper modelMapper = new ModelMapper();
        Empleado entity = null;
        if (pojo != null) {
            entity = new ModelMapper().map(pojo, Empleado.class);
        }
        return entity;
    }

    public EmpleadoPojo entityToPojo(Empleado entity) {
//        ModelMapper modelMapper = new ModelMapper();
        EmpleadoPojo pojo = null;
        if (entity != null) {
            pojo = new ModelMapper().map(entity, EmpleadoPojo.class);
        }
        return pojo;
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


