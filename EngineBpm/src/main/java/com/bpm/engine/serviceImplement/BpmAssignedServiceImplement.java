package com.bpm.engine.serviceImplement;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.mapper.BpmAssignedMapper;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.repository.BpmAssignedRepository;
import com.bpm.engine.service.BpmAssignedService;

@Service
public class BpmAssignedServiceImplement implements BpmAssignedService {

	
	@PersistenceContext
    private EntityManager entityManager;
	
    @Autowired
    private BpmAssignedRepository repository;

    @Autowired
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
        
        if ( assigned.getIdBpmAssigned() == null) {
        	BpmAssigned bpmAssigned = repository.save(mapper.pojoToEntity(assigned));
        	
        	if(bpmAssigned.getIdBpmAssigned() != null) {
        		return true;
        	}
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
    	
    	BpmAssignedModel model = new BpmAssignedModel(idAssigned, taskCode, instanciaProccesId);
    	BpmAssigned entity = mapper.pojoToEntity(model);
    	BpmAssigned entitySave = repository.save(entity);
//    	BpmAssigned entitySave = entityManager.merge(entity);
        return mapper.entityToPojo(entitySave);
    }

//    @Transactional
//    public void saveBpmAssigned(BpmAssigned bpmAssigned) {
//        bpmAssigned = entityManager.merge(bpmAssigned);
//    }
    
	@Override
	public List<BpmAssignedModel> findByTaskCodeAndInstanciaProccesIdNull(String taskCode, Boolean active) {
		
		List<BpmAssigned> find = repository.findByTaskCodeAndActiveAndInstanciaProccesIdNull(taskCode, active);
		
		if(find == null || find.isEmpty()) {
			return null;
		}
		 return mapper.entityListToPojoList(find);
	}
	
	@Override
	public List<BpmAssignedModel> findByTaskCodeActive(String taskCode, Boolean active) {
		 return mapper.entityListToPojoList(repository.findByTaskCodeAndActive(taskCode, active));
	}



	@Override
	public BpmAssignedModel findByCodeEmployeeAndTaskCode(String codeEmployee, String taskCode) {
		try {
			return mapper.entityToPojo(repository.findByCodeEmployeeAndTaskCode(codeEmployee, taskCode));
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public List<BpmAssignedModel> findByCodeEmployee(String codeEmployee) {
		
		try {
			return mapper.entityListToPojoList(repository.findByCodeEmployee(codeEmployee));
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BpmAssignedModel> findByProccesIdAndCodeEmployeeAndActive(Long proccesId, String codeEmployee) {
		return mapper.entityListToPojoList(repository.findByProccesIdAndCodeEmployeeAndActive(proccesId,codeEmployee, true));
	}
	

	@Override
	public List<BpmAssignedModel> findByCodeEmployeeActive(String codeEmployee) {
		
		try {
			return mapper.entityListToPojoList(repository.findByCodeEmployeeAndActive(codeEmployee, true));	
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}



