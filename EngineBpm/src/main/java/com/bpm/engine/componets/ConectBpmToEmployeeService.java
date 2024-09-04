package com.bpm.engine.componets;

import java.util.Arrays;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.AssignedDTO;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.interfaces.IBaseModel;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.serviceImplement.RestTemplateService;
import com.google.gson.Gson;

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

    @Value("${employeeServiceAssignedNo}")
    private String employeeServiceAssignedNo;
   
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
    }

  public AssignedModel getAssigned(String employeeNumber) {
    	
        return this.decodeHttpEntity(templateService.sendGetRequest(
                new HashMap<String, String>() {{
                            put("employeeNumber", employeeNumber);
                        }},
                        stringEnsamble(Arrays.asList(employeeServiceUrl, employeeServiceAssignedNo)))
        );
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
