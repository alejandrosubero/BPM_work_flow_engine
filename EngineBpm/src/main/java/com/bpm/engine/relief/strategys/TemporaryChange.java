package com.bpm.engine.relief.strategys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bpm.engine.managers.AssignmentTaskManager;
import com.bpm.engine.managers.BpmAssignedManager;
import com.bpm.engine.managers.facades.ProcessAndInstanceFacade;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.service.IReliefAssignedService;


@Service
public class TemporaryChange implements IReliefStrategy{

	

//	private ReliefAssignedMapper mapper;
	private IReliefAssignedService serviceRelief;
	private AssignmentTaskManager assignmentTaskManager;
	private BpmAssignedManager bpmAssignedManager;
	private ProcessAndInstanceFacade services;
	
	
	@Autowired
	public TemporaryChange(IReliefAssignedService serviceRelief,
			AssignmentTaskManager assignmentTaskManager, 
			BpmAssignedManager bpmAssignedManager,
			ProcessAndInstanceFacade services
			) {
		super();
		this.serviceRelief = serviceRelief;
		this.assignmentTaskManager = assignmentTaskManager;
		this.bpmAssignedManager = bpmAssignedManager;
		this.services = services;
	}



	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {

		Boolean response = false;

		try {

			ReliefAssignedModel reliefModel = serviceRelief.createReliefAssigned(reliefDTO);

			if (reliefModel.getUserReliefCode() != null) {

				response = this.executeTemporaryChanges(reliefModel.getUserReliefCode(), reliefModel.getUserCode());
			}
//			serviceRelief.updateActive(false, reliefModel.getIdRelief());
		} catch (Exception e) {
			return response;
		}

		return response;
	}


	public Boolean executeTemporaryChanges(String userReliefCode, String userCode) {

		Boolean response = false;
		AssignedModel reliefAssigned = null;
		Boolean replaceAssignedInBpmAssigned = null;
		Boolean IsChangeInstanceAbstraction = null;

		try {

			reliefAssigned = assignmentTaskManager.getAssignedOrCreateAssignedInBpmSystem(userReliefCode);

			if (reliefAssigned != null) {

				replaceAssignedInBpmAssigned = bpmAssignedManager.updateUserAssignedForUserReliefInBpmAssigned(
						userCode,
						userReliefCode, 
						reliefAssigned.getId()
						);

				IsChangeInstanceAbstraction = this.services.instanceManager()
						.getInstanceAbstractionService()
						.changeUserWorked(userCode, userReliefCode);
			}

			if (reliefAssigned != null && replaceAssignedInBpmAssigned && IsChangeInstanceAbstraction) {
				response = true;
			}
			
		} catch (Exception e) {
			return response;
		}
		return response;
	}


}
