package com.bpm.engine.serviceImplement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.mappers.InstanceAbstractionMapper;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.repository.InstanceAbstractionRepository;
import com.bpm.engine.service.InstanceAbstractionService;


@Service
public class InstanceAbstractionServiceImplement implements InstanceAbstractionService {
	
	  private static final Logger logger = LogManager.getLogger(InstanceAbstractionServiceImplement.class);

	@Autowired
	private InstanceAbstractionRepository repository;
	
	@Autowired
	private InstanceAbstractionMapper mapper;
	
	
	private <T> Object mensages(T t , String note) {
		StringBuffer mensages = new StringBuffer(note);
		mensages.append(t.toString());
		
		return mensages.toString();
	}
	
	
	
	@SuppressWarnings("finally")
	@Override
	public InstanceAbstractionModel findByIdInstance(Long idInstance) {
		
		logger.info("find By Id Instance ");
		InstanceAbstractionModel instanceAbstractionModel= null;
		
	    try {
	    	Optional<InstanceAbstraction> consult = this.repository.findById(idInstance);
			
			if(consult.isPresent()) {
				instanceAbstractionModel = mapper.entityToPojo(consult.get());
			}else {
				logger.error(mensages(idInstance,"the instance is no present in database") );
			}
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by code referent: ", e);
	    }finally {
			return instanceAbstractionModel;
		}
	}
	
	

	

	@Override
	public List<InstanceAbstractionModel> findByCodeReferent(String codeReferent) {
	  
		logger.info("Find By code Referent ");

	    if (codeReferent == null) {
	    	logger.error("Code referent is null");
	        return Collections.emptyList();
	    }
	    
	    try {
	        return this.mapper.entityListToPojoList(this.repository.findByCodeReferent(codeReferent));
	        
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
	        return this.mapper.entityListToPojoList(this.repository.findByUserWorkedAndActive(userWorked, true));
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by userWorked: ", e);
	        return Collections.emptyList();
	    }
	}
	
	
	@Override
	public List<InstanceAbstractionModel> findByUserWorked(String userWorked, Boolean active) {
		logger.info("Find By code Referent ");

	    if (userWorked == null) {
	    	logger.error("Code userWorked is null");
	        return Collections.emptyList();
	    }
	    
	    try {
	        return this.mapper.entityListToPojoList(this.repository.findByUserWorkedAndActive(userWorked, active));
	        
	    } catch (DataAccessException e) {
	    	 logger.error("Error at looking the InstanceAbstraction by userWorked: ", e);
	        return Collections.emptyList();
	    }
	}

	@Override
	public List<InstanceAbstractionModel> findByUserCreateInstance(String userCreateInstance) {
		
		 if (userCreateInstance == null || userCreateInstance.equals("")) {
		    	logger.error("Code userWorked is null");
		        return Collections.emptyList();
		    }
		    
		    try {
		        return this.mapper.entityListToPojoList(this.repository.findByUserCreateInstance(userCreateInstance));
		        
		    } catch (DataAccessException e) {
		    	 logger.error("Error at looking the InstanceAbstraction by userCreateInstance: ", e);
		        return Collections.emptyList();
		    }
	}

	@Override
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstance(String instanOf, Long idInstance) {
		
		 if (instanOf == null || instanOf.equals("") || idInstance== null) {
		    	logger.error("Code userWorked is null");
		        return Collections.emptyList();
		    }
		    
		    try {
		        return this.mapper.entityListToPojoList(this.repository.findByInstanOfAndIdInstance( instanOf,  idInstance));
		        
		    } catch (DataAccessException e) {
		    	 logger.error("Error at looking the InstanceAbstraction by instanOf,  idInstance: ", e);
		        return Collections.emptyList();
		    }
		
	}

	@Override
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess) {
		
		 if (instanOf == null || instanOf.equals("") || idInstanceOfProcess== null) {
		    	logger.error("Code userWorked is null");
		        return Collections.emptyList();
		    }
		    
		    try {
		        return this.mapper.entityListToPojoList(this.repository.findByInstanOfAndIdInstanceOfProcess( instanOf, idInstanceOfProcess));
		        
		    } catch (DataAccessException e) {
		    	 logger.error("Error at looking the InstanceAbstraction by instanOf, idInstanceOfProcess: ", e);
		        return Collections.emptyList();
		    }
	}

	@Override
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf, Long idInstanceOfProcess, Integer level) {
		
		if ((instanOf == null || instanOf.equals("")) || idInstanceOfProcess == null || level== null) {
		    	logger.error("Code userWorked is null");
		        return Collections.emptyList();
		    }
		    
		    try {
		        return this.mapper.entityListToPojoList(this.repository.findByInstanOfAndIdInstanceOfProcessAndLevel( instanOf,  idInstanceOfProcess, level));
		        
		    } catch (DataAccessException e) {
		    	 logger.error("Error at looking the InstanceAbstraction by instanOf, idInstanceOfProcess, level: ", e);
		        return Collections.emptyList();
		    }
	}
	
	

	@Override
	public void updateStatus(String status, Long idInstance) {
		try {
			if(status !=null && !status.equals("") && idInstance !=null) {
				this.repository.updateStatus( status, idInstance);
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void updateActive(Boolean active, Long idInstance) {
		
		try {
			if(active !=null && idInstance !=null) {
				this.repository.updateActive( active, idInstance);
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void updateUserWorked(String userWorked, Long idInstance) {
		try {
			if(userWorked !=null && !userWorked.equals("") && idInstance !=null) {
				this.repository.updateUserWorked( userWorked, idInstance);
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			
		}
		
	}
	
	
	

	
	

	
	//TODO: ES NECESARIO CREAR EL TETS UNITARIO PARA ESTE METODO
	@Override
	public void updateInstances(List<InstanceAbstraction> instances, Long idInstance) {
		
		try {
			if(instances !=null && !instances.isEmpty() && idInstance !=null) {
				this.repository.updateInstances(instances, idInstance);
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void updateUserWorked(Long idParent, Long idInstance) {
		
		try {
			if(idParent !=null  && idInstance !=null) {
				this.repository.updateUserWorked( idParent, idInstance);
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			
		}
		
	}



	@SuppressWarnings("finally")
	@Override
	public InstanceAbstractionModel save(InstanceAbstractionModel instance) {
		InstanceAbstractionModel saveInstance = null;
		try {
			
			saveInstance = mapper.entityToPojo(repository.save( mapper.pojoToEntity(instance)));
			
		}catch( DataAccessException e) {
			 logger.error("Error at save a InstanceAbstraction field: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}finally {
			return saveInstance;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public InstanceAbstractionModel saveInitial(InstanceAbstractionModel instance) {
		
		InstanceAbstractionModel saveInstance = null;
		InstanceAbstraction entity = null;
		
		try {
			entity = repository.save( mapper.pojoToEntity(instance));
			
			if(entity != null) {
				 logger.info("Save a InstanceAbstraction... " + entity.getIdInstance());
				 
				List<InstanceAbstraction> instancesOfProcess = repository.findByIdInstanceOfProcess(entity.getIdInstance());
				
				if(instancesOfProcess != null && !instancesOfProcess.isEmpty()) {
					instancesOfProcess.parallelStream().forEach(instanceAbstraction-> repository.updateParentByIdInstance(instanceAbstraction.getIdInstance()));
				}
				
				Optional<InstanceAbstraction> entityOptional = repository.findById(entity.getIdInstance());
				
				if(entityOptional.isPresent()) {
					saveInstance = mapper.entityToPojo(entityOptional.get());
				}
			}
		
		}catch( DataAccessException e) {
			 logger.error("Error at save a InstanceAbstraction field: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
		}finally {
			return saveInstance;
		}
		
	}



	@Override
	public List<InstanceAbstractionModel> finBySearch(String keyword) {
		List<InstanceAbstraction> list =repository.finBySearch2(keyword);
		return mapper.entityListToPojoList(list);
	}



	@SuppressWarnings("finally")
	@Override
	public List<InstanceAbstractionModel> findByUser(String user) {
		
		List<InstanceAbstractionModel> listInstance = null ;
		
		try {
			if(user != null) {
				listInstance  = new ArrayList<>();
				List<InstanceAbstraction> list = new ArrayList<>();
				List<Long> ids = repository.findIdInstanceProcessFromUserWorked(user);
				
				if(!ids.isEmpty()) {
					list.addAll(repository.findByIdInstanceIn(ids));
				}
				list.addAll(repository.findByUserCreateInstanceAndIdInstanceOfProcessIsNull(user));
				listInstance = mapper.entityListToPojoList(list);
			}
		}catch( DataAccessException e) {
			 logger.error("Error in find a InstanceAbstraction by user: ", e);
			e.printStackTrace();	
		}catch(IllegalArgumentException e) {
			logger.error("the user parameters are null");
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("Error in findByUser...", e);
			e.printStackTrace();
		}finally {
			return listInstance;
		}
	}



	@Override
	public Boolean changeUserWorked(String userWorked, String newUserWorked) {
		List<InstanceAbstraction> instancelist = null;
		List<Long> instancesid = new ArrayList<>();
		
		instancelist = repository.findByUserWorkedAndActive(userWorked, true);
		
		
		if(instancelist == null || instancelist.isEmpty()) {
			//TODO: CONECT TO NOTIFICATION SYSTEM BECAUS THE USER DON'T HAVE WORK ASSIGNED..
			return true;
		}
				 
		if(instancelist != null && !instancelist.isEmpty()) {
			 instancelist.parallelStream().forEach(instance -> {
				 this.updateUserWorked(newUserWorked, instance.getIdInstance()); 
				 instancesid.add(instance.getIdInstance());
			 });
		}
		 return instancesid.stream().allMatch(id -> repository.checkInstanceExistsByuserCreate(newUserWorked, id) != null);
	}

	
	@Override
	public Boolean changeUserCreateInstance(String userCreateInstance, String newUserCreateInstance) {
		List<InstanceAbstraction> instancelist = null;
		List<Long> instancesid = new ArrayList<>();
		
		instancelist = repository.findByUserCreateInstanceAndActive(userCreateInstance, true);
		 
		if(instancelist != null && !instancelist.isEmpty()) {
			 instancelist.parallelStream().forEach(instance -> {
				 this.updateUserCreateInstance(newUserCreateInstance, instance.getIdInstance()); 
				 instancesid.add(instance.getIdInstance());
			 });
		}
		return instancesid.stream().allMatch(id -> repository.checkInstanceExistsByuserCreate(newUserCreateInstance, id) != null);
	}

	@Override
	public Boolean changeUserCreateInstance(ReliefAssignedModel reliefModel, List<Long> idInstances) {

		if(idInstances != null && !idInstances.isEmpty()) {
			idInstances.parallelStream().forEach(id -> {
				 
				if(repository.checkInstanceExistsByuserCreate(id) != null ){
					 this.updateUserCreateInstance(reliefModel.getUserReliefCode(), id); 
				}
				 
			 });
		}
		return idInstances.stream().allMatch(id -> repository.checkInstanceExistsByuserCreate(id) != null);
	}

	
	@Override
	public void updateUserCreateInstance(String userCreateInstance, Long idInstance) {
		try {
			if(userCreateInstance !=null && !userCreateInstance.equals("") && idInstance !=null) {
				this.repository.updateUserCreateInstance(userCreateInstance, idInstance);
			}
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();	
		}
	}

//TODO: CONECTAR LOS ERRORES AL SISTEMA DE NOTIFICACION DE ERRORES...
	



}
