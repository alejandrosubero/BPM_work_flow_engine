package com.bpm.engine.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bpm.engine.entitys.InstanceAbstraction;

@Repository
public interface InstanceAbstractionRepository extends CrudRepository<InstanceAbstraction, Long> {

	public List<InstanceAbstraction> findByCodeReferent(String codeReferent);
	public List<InstanceAbstraction> findByUserWorked(String userWorked);
	public List<InstanceAbstraction> findByUserCreateInstance(String userCreateInstance);
	public List<InstanceAbstraction> findByInstanOfAndIdInstance(String instanOf, Long idInstance);
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess);
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf, Long idInstanceOfProcess, Integer level);
	public List<InstanceAbstraction> findByIdInstanceOfProcess(Long idInstanceOfProcess);
	

	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE BPM_INSTANCE_ABSTRACTION SET ID_PARENT = (SELECT INSTANCES_ID FROM BPM_INSTANCE_ABSTRACTION WHERE ID_INSTANCE = :idInstance) WHERE ID_INSTANCE = :idInstance", nativeQuery = true)
	 void updateParentByIdInstance(@Param("idInstance") Long idInstance);
	
	
	@Transactional
    @Modifying
    @Query("update InstanceAbstraction u set u.status = ?1 where u.idInstance = ?2")
    void updateStatus(String status, Long idInstance);
	
	@Transactional
    @Modifying
    @Query("update InstanceAbstraction u set u.active = ?1 where u.idInstance = ?2")
    void updateActive(Boolean active, Long idInstance);
	
	
	@Transactional
    @Modifying
    @Query("update InstanceAbstraction u set u.userWorked = ?1 where u.idInstance = ?2")
    void updateUserWorked(String userWorked, Long idInstance);
	
	
	@Transactional
    @Modifying
    @Query("update InstanceAbstraction u set u.instances = ?1 where u.idInstance = ?2")
    void updateInstances(List<InstanceAbstraction> instances, Long idInstance);
	
	@Transactional
    @Modifying
    @Query("update InstanceAbstraction u set u.idParent = ?1 where u.idInstance = ?2")
    void updateUserWorked(Long idParent, Long idInstance);
	
	
//	  @Query(value = "SELECT p FROM DataJshandyManServicesConfig p WHERE CONCAT( p.userCode, ' ', p.direction, ' ', p.phoneNumber, ' ', p.company) LIKE %?1%")
//	    public List<DataJshandyManServicesConfig> finBySearch(String keyword);
}
