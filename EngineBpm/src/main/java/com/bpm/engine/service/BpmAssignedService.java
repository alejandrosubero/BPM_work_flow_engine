package com.bpm.engine.service;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.model.BpmAssignedModel;

import java.util.List;

public interface BpmAssignedService {

    public BpmAssignedModel instanceBpmAssigned(Long idAssigned, String taskCode, Long instanciaProccesId);
    public BpmAssignedModel findByIdBpmAssigned(Long id);

    public List<BpmAssignedModel> findByIdAssigned(Long idAssigned);

    public List<BpmAssignedModel> findByIdAssignedContaining(Long idAssigned);

    public List<BpmAssignedModel> findByTaskCode(String taskCode);

    public List<BpmAssignedModel> findByTaskCodeContaining(String taskCode);

    public boolean saveOrUpdateBpmAssigned(BpmAssignedModel assigned);

    public List<BpmAssignedModel> findByInstanciaProccesId(Long instanciaProccesId);

    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesId(String taskCode, Long instanciaProccesId);

    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesIdNull(String taskCode);

}
