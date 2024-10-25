package com.bpm.engine.relief.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bpm.engine.relief.entity.ReliefAssigned;

@Repository
public interface ReliefAssignedRepository extends CrudRepository<ReliefAssigned, Long> {

	public List<ReliefAssigned> findByActive(Boolean Active);
	public List<ReliefAssigned> findByUserCodeAndActive(String userCode, Boolean Active);
	public List<ReliefAssigned> findByUserReliefCodeAndActive(String userReliefCode, Boolean Active);
	public List<ReliefAssigned> findByTemporaryAndActive(Boolean temporary, Boolean Active);
	public List<ReliefAssigned> findByPermanentAndActive(Boolean permanent, Boolean Active);
	public List<ReliefAssigned> findByTemporaryAndReturnCommandAndActive(Boolean temporary,Boolean returnCommand,Boolean Active);
	public List<ReliefAssigned> findByReturnCommandAndActive(Boolean returnCommand,Boolean Active);
	public List<ReliefAssigned> findByUserReliefCodeLike(String userReliefCode);
	
	
	@Transactional
    @Modifying
    @Query("update ReliefAssigned r set r.timeActive = ?1 where r.idRelief = ?2")
    void updateTimeActive(String timeActive, Long idRelief);
	
	
	@Transactional
    @Modifying
    @Query("update ReliefAssigned r set r.active = ?1 where r.idRelief = ?2")
    void updateActive(String active, Long idRelief);
	
	
	@Query(value = "SELECT p FROM ReliefAssigned p WHERE  p.userCode LIKE :keyword OR p.userReliefCode LIKE :keyword")
	public List<ReliefAssigned> finBySearch(@Param("keyword") String keyword);
	
	
}
