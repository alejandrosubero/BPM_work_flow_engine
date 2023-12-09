package com.bpm.engine.serviceImplement;


import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.mapper.BpmAssignedMapper;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.repository.BpmAssignedRepository;
import com.bpm.engine.service.BpmAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BpmAssignedServiceImplement implements BpmAssignedService {

    @Autowired
    private BpmAssignedRepository repository;

    private BpmAssignedMapper mapper;


    @Override
    public BpmAssignedModel findByIdBpmAssigned(Long id) {
        return mapper.entityToPojo(repository.findById(id).get());
    }

    @Override
    public List<BpmAssignedModel> findByIdAssigned(Long idAssigned) {
        return mapper.entityListToPojoList(repository.findByIdAssigned(idAssigned));
    }

    @Override
    public List<BpmAssignedModel> findByIdAssignedContaining(Long idAssigned) {
        return mapper.entityListToPojoList(repository.findByIdAssignedContaining(idAssigned));
    }

    @Override
    public List<BpmAssignedModel> findByTaskCode(String taskCode) {
        return mapper.entityListToPojoList(repository.findByTaskCode(taskCode));
    }

    @Override
    public List<BpmAssignedModel> findByTaskCodeContaining(String taskCode) {
        return mapper.entityListToPojoList(repository.findByTaskCodeContaining(taskCode));
    }

    @Override
    public boolean saveOrUpdateBpmAssigned(BpmAssignedModel assigned) {

        if (assigned.getIdBpmAssigned() != null) {
            BpmAssignedModel assignedBase = this.findByIdBpmAssigned(assigned.getIdBpmAssigned());
            assignedBase.setBpmAssignedModel(assigned);
            repository.save(mapper.pojoToEntity(assignedBase));
            return true;
        }
        if (repository.save(mapper.pojoToEntity(assigned)) != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<BpmAssignedModel> findByInstanciaProccesId(Long instanciaProccesId) {
        return  mapper.entityListToPojoList(repository.findByInstanciaProccesId(instanciaProccesId));
    }

    @Override
    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesId(String taskCode, Long instanciaProccesId) {
        return mapper.entityListToPojoList(repository.findByTaskCodeAndInstanciaProccesId(taskCode,instanciaProccesId));
    }

    @Override
    public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesIdNull(String taskCode) {
        return mapper.entityListToPojoList(repository.findByTaskCodeAndInstanciaProccesIdNull(taskCode));
    }


    @Override
    public BpmAssignedModel instanceBpmAssigned(Long idAssigned, String taskCode, Long instanciaProccesId){
        return mapper.entityToPojo(repository.save(mapper.pojoToEntity(new BpmAssignedModel(idAssigned, taskCode, instanciaProccesId))));
    }

}
