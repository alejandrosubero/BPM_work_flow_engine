package com.bpm.engine.service;

import java.util.List;

import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.models.BpmAssignedModel;

public interface BpmAssignedService {

    public BpmAssignedModel instanceBpmAssigned(Long idAssigned, String taskCode, Long instanciaProccesId);
    
    public BpmAssignedModel findByIdBpmAssigned(Long id);
    
    public BpmAssignedModel findByCodeEmployeeAndTaskCode(String codeEmployee, String taskCode);
    
    public List<BpmAssignedModel>  findByCodeEmployee(String codeEmployee);
    
    public List<BpmAssignedModel> findByCodeEmployeeActive (String codeEmployee);

    public List<BpmAssignedModel> findByIdAssigned(Long idAssigned);

    public List<BpmAssignedModel> findByIdAssignedContaining(Long idAssigned);

    public List<BpmAssignedModel> findByTaskCode(String taskCode);

    public List<BpmAssignedModel> findByTaskCodeContaining(String taskCode);

    public BpmAssignedModel saveOrUpdateBpmAssigned(BpmAssignedModel assigned);

    public List<BpmAssignedModel> findByInstanciaProccesId(Long instanciaProccesId);

    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesId(String taskCode, Long instanciaProccesId);

    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesIdNull(String taskCode);
    
    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesIdNull(String taskCode, Boolean active);
    
    public List<BpmAssignedModel> findByTaskCodeActive(String taskCode, Boolean active);
    
    public List<BpmAssignedModel> findByProccesIdAndCodeEmployeeAndActive (Long proccesId, String codeEmployee);
    
    public List<BpmAssignedModel> findByCodeEmployeeAndActive (String codeEmployee, Boolean active);
    
    public void updateBpmAssignedActive(Boolean active, Long idBpmAssigned);
    
}
