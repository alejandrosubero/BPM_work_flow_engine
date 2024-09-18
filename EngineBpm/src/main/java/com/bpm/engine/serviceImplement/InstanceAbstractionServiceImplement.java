package com.bpm.engine.serviceImplement;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.mapper.InstanceAbstractionMapper;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.repository.InstanceAbstractionRepository;
import com.bpm.engine.service.InstanceAbstractionService;



public class InstanceAbstractionServiceImplement implements InstanceAbstractionService {
	
	  private static final Logger logger = LogManager.getLogger(InstanceAbstractionServiceImplement.class);

	@Autowired
	private InstanceAbstractionRepository instanceAbstractionRepository;
	
	@Autowired
	private InstanceAbstractionMapper mapper;
	
	@Override
	public InstanceAbstractionModel findByIdInstance(Long idInstance) {
		
		logger.info("find By Id Instance ");
		
	    try {
	    	Optional<InstanceAbstraction> consult = this.instanceAbstractionRepository.findById(idInstance);
			
			if(consult.isPresent()) {
				return mapper.entityToPojo(consult.get());
			}else {
				logger.error(mensages(idInstance,"the instance is no present in database") );
				return null;
			}
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by code referent: ", e);
	    	 
	        return Collections.emptyList();
	    }
		
		
	}
	
	
	private <T> Object mensages(T t , String note) {
		
		StringBuffer mensages = new StringBuffer(note);
		mensages.append(t.toString());
		
		return mensages.toString();
	}
	

	@Override
	public List<InstanceAbstractionModel> findByCodeReferent(String codeReferent) {
	  
		logger.info("Find By code Referent ");

	    if (codeReferent == null) {
	    	logger.error("Code referent is null");
	        return Collections.emptyList();
	    }
	    
	    try {
	        return this.mapper.entityListToPojoList(this.instanceAbstractionRepository.findByCodeReferent(codeReferent));
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by code referent: ", e);
	        return Collections.emptyList();
	    }
	}
	
	
	
	@Override
	public List<InstanceAbstractionModel> findByUserWorked(String userWorked) {
		logger.info("Find By code Referent ");

	    if (userWorked == null) {
	    	logger.error("Code userWorked is null");
	        return Collections.emptyList();
	    }
	    
	    try {
	        return this.mapper.entityListToPojoList(this.instanceAbstractionRepository.findByUserWorked(userWorked));
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by code referent: ", e);
	        return Collections.emptyList();
	    }
	}
	
	

	@Override
	public List<InstanceAbstraction> findByUserCreateInstance(String userCreateInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstance(String instanOf, Long idInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf,
			Long idInstanceOfProcess, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(String status, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateActive(Boolean active, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserWorked(String userWorked, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInstances(List<InstanceAbstraction> instances, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserWorked(Long idParent, Long idInstance) {
		// TODO Auto-generated method stub
		
	}



}
