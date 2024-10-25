package com.bpm.engine.relief.service;

import java.util.List;
import java.util.Optional;

import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.model.ReliefAssignedModel;

public interface IReliefAssignedService {


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
