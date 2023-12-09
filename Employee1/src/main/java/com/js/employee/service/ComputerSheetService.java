package com.js.employee.service;

import com.js.employee.entitys.ComputerSheet;
import com.js.employee.model.AssignedModel;
import com.js.employee.pojo.ComputerSheetPojo;
import com.js.employee.pojo.EmpleadoPojo;

import java.util.List;
import java.util.Optional;

public interface ComputerSheetService {

    public ComputerSheetPojo findByUserNameAndUserPassword(String userName, String userPassword);
    public List<ComputerSheetPojo> findByMail(String mail);

    public EmpleadoPojo findEmpledo(String userName, String userPassword);

    public AssignedModel getEmployeeAssigned(String userName, String userPassword);

}
