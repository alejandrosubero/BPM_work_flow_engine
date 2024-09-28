package com.bpm.engine.repository;

import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.model.BpmAssignedModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BpmAssignedRepository extends CrudRepository<BpmAssigned, Long>{


//	public BpmAssigned save(BpmAssigned bpmAssigned);
    
    public List<BpmAssigned> findByIdAssigned(Long idAssigned);

    public List<BpmAssigned> findByIdAssignedContaining(Long idAssigned);

    public List<BpmAssigned> findByTaskCode(String taskCode);

    public List<BpmAssigned> findByTaskCodeContaining(String taskCode);

    public BpmAssigned findByIdBpmAssigned(Long id);

    public List<BpmAssigned> findByInstanciaProccesId(Long instanciaProccesId);

    public List<BpmAssigned> findByTaskCodeAndInstanciaProccesId(String taskCode, Long instanciaProccesId);

    public List<BpmAssigned> findByTaskCodeAndInstanciaProccesIdNull(String taskCode);

    public List<BpmAssigned> findByTaskCodeAndActiveAndInstanciaProccesIdNull(String taskCode, Boolean active);
    
    public List<BpmAssigned> findByTaskCodeAndActive(String taskCode, Boolean active);
    
    public List<BpmAssigned>  findByCodeEmployee(String codeEmployee);
    
    public BpmAssigned findByCodeEmployeeAndTaskCode(String codeEmployee, String taskCode);
    
    public List<BpmAssigned> findByProccesIdAndCodeEmployeeAndActive (Long proccesId, String codeEmployee, Boolean active);

    public List<BpmAssigned> findByCodeEmployeeAndActive (String codeEmployee, Boolean active);

}
