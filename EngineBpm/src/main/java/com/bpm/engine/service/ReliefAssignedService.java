package com.bpm.engine.service;

import java.util.List;

import com.bpm.engine.entitys.ReliefAssigned;

public interface ReliefAssignedService {

	
	public List<ReliefAssigned> findByActive(Boolean Active);
	public List<ReliefAssigned> findByUserCodeAndActive(String userCode, Boolean Active);
	public List<ReliefAssigned> findByUserReliefCodeAndActive(String userReliefCode, Boolean Active);
	public List<ReliefAssigned> findByTemporaryAndActive(Boolean temporary, Boolean Active);
	public List<ReliefAssigned> findByPermanentAndActive(Boolean permanent, Boolean Active);
	public List<ReliefAssigned> findByTemporaryAndReturnCommandAndActive(Boolean temporary,Boolean returnCommand,Boolean Active);
	public List<ReliefAssigned> findByReturnCommandAndActive(Boolean returnCommand,Boolean Active);
	public void updateActive(String active, Long idRelief);
	public void updateTimeActive(String timeActive, Long idRelief);
	
	
}
