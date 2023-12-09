package com.js.employee.interfaces;

import com.js.employee.model.AssignedModel;
import com.js.employee.model.RoleModel;
import com.js.employee.pojo.EmpleadoPojo;

public interface IAssigned {

    default public AssignedModel generateAssigned(EmpleadoPojo empleadoPojo) {
        try {
            RoleModel role = new RoleModel(empleadoPojo.getPosition().getDescription(), empleadoPojo.getPosition().getName(), empleadoPojo.getPosition().getCode());
            AssignedModel assigned = new AssignedModel(empleadoPojo.getName(), empleadoPojo.getNumeroEmpleado(), role, empleadoPojo.getComputerSheet().getMail());
            return assigned;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
