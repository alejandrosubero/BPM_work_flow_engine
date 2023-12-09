package com.bpm.engine.componets;

import com.bpm.engine.dto.AssignedDTO;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.interfaces.IBaseModel;
import com.bpm.engine.model.ApprovedProcessModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.RoleModel;
import com.bpm.engine.serviceImplement.RestTemplateService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

/*
This componet or service is custom for each company in this case we use the service in the repositoria app Employee.
this app implement a tree for employee Approver.
 */

@Service
public class ConectBpmToEmployeeService implements IBaseModel {

    @Value("${employeeServiceUrl}")
    private String employeeServiceUrl;

    @Value("${employeeServiceAssigned}")
    private String employeeServiceAssigned;

    @Value("${employeeServiceFindUserName}")
    private String employeeServiceFindUserName;


    @Value("${employeeService}")
    private String employeeService;


    @Autowired
    private RestTemplateService templateService;

    public AssignedModel getAssigned(String employeeNumber, String positionCode) {
        return this.decodeHttpEntity(templateService.sendGetRequest(
                new HashMap<String, String>() {{
                            put("employeeNumber", employeeNumber);
                            put("positionCode", positionCode);
                        }},
                        stringEnsamble(Arrays.asList(employeeServiceUrl, employeeServiceAssigned)))
        );
        //        String employeeApproveServiceUrl = stringEnsamble(Arrays.asList(employeeServiceUrl, employeeServiceAssigned));
//        Map<String, String> vars= new HashMap<String, String>() {{
//            put("employeeNumber", employeeNumber);
//            put("positionCode", positionCode);
//        }};
//        HttpEntity<String> request = templateService.sendGetRequest(vars, employeeApproveServiceUrl);
//        return request;
    }


    public AssignedModel getAssignedUserName(String userName) {
        return this.decodeHttpEntity(templateService.sendGetRequest(
                        new HashMap<String, String>() {{put("userName", userName);}},
                        stringEnsamble(Arrays.asList(employeeServiceUrl, employeeServiceFindUserName))));
    }


    public AssignedModel getEmployeeAssignedFromEmployeeService(String employeeNumber){
        return this.decodeHttpEntity(templateService.sendGetRequest(
                new HashMap<String, String>() {{put("employeeNumber", employeeNumber);}},
                stringEnsamble(Arrays.asList(employeeServiceUrl, employeeService))));
    }

    private AssignedModel decodeHttpEntity(HttpEntity<String> request) {
        String resp = "";
        Gson gson = new Gson();
        EntityRespone response = gson.fromJson(request.getBody(), EntityRespone.class);
        if (response != null && response.getEntidades() != null && response.getEntidades().size() > 0) {
            resp = (String) response.getEntidades().get(0);
        }
        return new ModelMapper().map(gson.fromJson(resp, AssignedDTO.class), AssignedModel.class);
    }

}
