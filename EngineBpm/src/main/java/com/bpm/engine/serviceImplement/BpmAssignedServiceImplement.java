package com.bpm.engine.serviceImplement;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.mappers.BpmAssignedMapper;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.repository.BpmAssignedRepository;
import com.bpm.engine.service.BpmAssignedService;

@Service
public class BpmAssignedServiceImplement implements BpmAssignedService {


	 private static final Logger logger = LogManager.getLogger(BpmAssignedServiceImplement.class);
	
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

    @SuppressWarnings("finally")
	@Override
    public BpmAssignedModel saveOrUpdateBpmAssigned(BpmAssignedModel assigned) {

    	BpmAssignedModel response = null;
    try {
        if (assigned.getIdBpmAssigned() != null) {
            BpmAssignedModel assignedBase = this.findByIdBpmAssigned(assigned.getIdBpmAssigned());
            assignedBase.updateThis(assigned);
            response = mapper.entityToPojo( repository.save(mapper.pojoToEntity(assignedBase)));
        }
        
        if ( assigned.getIdBpmAssigned() == null) {
        	BpmAssigned bpmAssigned = repository.save(mapper.pojoToEntity(assigned));
        	
        	if(bpmAssigned.getIdBpmAssigned() != null) {
        		response = mapper.entityToPojo(bpmAssigned);
        	}
        }
	}catch( DataAccessException e) {
		 logger.error("Error change Role Assigned: ", e);
		e.printStackTrace();	
		 //TODO: registrar en el sistema de notificacion error and set logger
	}catch(IllegalArgumentException e) {
		logger.error("the one or all parameters are null");
		e.printStackTrace();
		 //TODO: registrar en el sistema de notificacion error and set logger
	}finally {
		return response;
	}
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
        return mapper.entityToPojo(entitySave);
    }


    
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

	@Override
	public List<BpmAssignedModel> findByCodeEmployeeAndActive(String codeEmployee, Boolean active) {
		try {
			return mapper.entityListToPojoList(repository.findByCodeEmployeeAndActive(codeEmployee,active));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateBpmAssignedActive(Boolean active, Long idBpmAssigned) {
		try {
			
			repository.updateBpmAssignedActive(active, idBpmAssigned);
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a BpmAssigned field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}
		
	}
	

}



