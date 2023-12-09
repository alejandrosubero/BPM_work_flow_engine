
/*
Create on Sat Oct 07 21:00:29 EDT 2023
*Copyright (C) 123.
@author yo
@author yo
@author  
@since 1.8
@version1.0.0.0
@version  %I%, %G%
*<p>Description: Employee’s hamblet </p>
*/


package com.js.employee.repository;

import java.util.List;
import java.util.Date;

import java.util.Optional;

import com.js.employee.pojo.HierarchicalTreePojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.js.employee.entitys.HierarchicalTree;

public interface HierarchicalTreeRepository extends CrudRepository<HierarchicalTree, Long> {

    public Optional<HierarchicalTree> findByPositioName(String positioName);

    public List<HierarchicalTree> findByPositioNameContaining(String positioName);

    public Optional<HierarchicalTree> findByPositionNumber(Integer positionNumber);

    public List<HierarchicalTree> findByPositionNumberContaining(Integer positionNumber);

    public Optional<HierarchicalTree> findByAreaDivision(String areaDivision);

    public List<HierarchicalTree> findByAreaDivisionContaining(String areaDivision);

    public Optional<HierarchicalTree> findBySubAreaDivision(String subAreaDivision);

    public List<HierarchicalTree> findBySubAreaDivisionContaining(String subAreaDivision);

    public Optional<HierarchicalTree> findByAreaDivisionAndSubAreaDivisionAndPositioCode(String areaDivision, String subAreaDivision, String positioCode);

    public Optional<HierarchicalTree> findByPositioCode(String positioCode);

    public List<HierarchicalTree> findByAreaDivisionAndSubAreaDivisionAndPositionNumber(String areaOrDivision, String subAreaDivision, Integer positionNumber);

    public List<HierarchicalTree> findByHierarchyOfAreas(Integer hierarchyOfAreas);


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


