package com.bpm.engine.service;

import java.util.List;
import java.util.Optional;

import com.bpm.engine.entitys.ReliefAssigned;
import com.bpm.engine.models.ReliefAssignedModel;

public interface ReliefAssignedService {

//	public List<ReliefAssigned> findByActive(Boolean Active);
//	public List<ReliefAssigned> findByUserCodeAndActive(String userCode, Boolean Active);
//	public List<ReliefAssigned> findByUserReliefCodeAndActive(String userReliefCode, Boolean Active);
//	public List<ReliefAssigned> findByTemporaryAndActive(Boolean temporary, Boolean Active);
//	public List<ReliefAssigned> findByPermanentAndActive(Boolean permanent, Boolean Active);
//	public List<ReliefAssigned> findByTemporaryAndReturnCommandAndActive(Boolean temporary,Boolean returnCommand,Boolean Active);
//	public List<ReliefAssigned> findByReturnCommandAndActive(Boolean returnCommand,Boolean Active);
//	
//	public void updateActive(String active, Long idRelief);
//	public void updateTimeActive(String timeActive, Long idRelief);

	public void updateTimeActive(Integer timeActive, Long idRelief);

	public void updateActive(Boolean active, Long idRelief);

	public ReliefAssignedModel createReliefAssigned(ReliefAssignedModel model);

	public ReliefAssignedModel updateReliefAssigned(ReliefAssignedModel model);

	public List<ReliefAssignedModel> findByActive(Boolean active);

	public List<ReliefAssignedModel> findByUserCodeAndActive(String userCode, Boolean active);

	public List<ReliefAssignedModel> findByUserReliefCodeAndActive(String userReliefCode, Boolean active);

	public List<ReliefAssignedModel> findByTemporaryAndActive(Boolean temporary, Boolean active);

	public List<ReliefAssignedModel> findByPermanentAndActive(Boolean permanent, Boolean active);

	public List<ReliefAssignedModel> findByTemporaryAndReturnCommandAndActive(Boolean temporary, Boolean returnCommand, Boolean active);

	public List<ReliefAssignedModel> findByReturnCommandAndActive(Boolean returnCommand, Boolean active);

	public List<ReliefAssignedModel> findByUserReliefCodeLike(String userReliefCode);

}
