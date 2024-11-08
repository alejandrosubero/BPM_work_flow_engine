package com.bpm.engine.relief.strategys;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bpm.engine.managers.AssignmentTaskManager;
import com.bpm.engine.managers.BpmAssignedManager;
import com.bpm.engine.managers.facades.NoReliefFacade;
import com.bpm.engine.managers.facades.ProcessAndInstanceFacade;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.service.IReliefAssignedService;
import com.bpm.engine.utility.InstanOf;


@Component
public class ChangeBpmRole implements IReliefStrategy {
	
	private static final Logger logger = LogManager.getLogger(ChangeBpmRole.class);

	private ProcessAndInstanceFacade services;

	private AssignmentTaskManager assignmentTaskManager;
	
	private BpmAssignedManager bpmAssignedManager;
	
	private NoReliefFacade noRelief;
	
	private IReliefAssignedService serviceRelief;
		
	

	@Autowired
	public ChangeBpmRole(ProcessAndInstanceFacade services, AssignmentTaskManager assignmentTaskManager,
			BpmAssignedManager bpmAssignedManager, NoReliefFacade noRelief, IReliefAssignedService serviceRelief) {
		this.services = services;
		this.assignmentTaskManager = assignmentTaskManager;
		this.bpmAssignedManager = bpmAssignedManager;
		this.noRelief =noRelief;
		this.serviceRelief = serviceRelief;
	}
	


	
	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {
		
		Boolean response = false;
		ReliefAssignedModel reliefModel = serviceRelief.createReliefAssigned(reliefDTO);
		
		if(reliefDTO.getIdInstances() !=null && !reliefDTO.getIdInstances().isEmpty()) {
			
			reliefModel.setDelegateAll(false);
			response = this.execute(reliefModel, reliefDTO.getIdInstances());
			
		}else {
			response = this.execute(reliefModel);
		}
		
		serviceRelief.updateActive(false, reliefModel.getIdRelief());
		return response;
	}
	
	

	

	private Boolean execute(ReliefAssignedModel reliefModel) {
		
		logger.info( "Started change Bpm role...");
		
		AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);
		
		if(reliefModel.getUserReliefCode() != null) {
			AssignedModel reliefAssigned = assignmentTaskManager.getAssignedOrCreateAssignedInBpmSystem(reliefModel.getUserReliefCode());
			
			Boolean replaceAssignedInBpmAssigned = 
					bpmAssignedManager.replaceUserAssignedForUserReliefInBpmAssigned(
						reliefModel.getUserCode(), 
						reliefModel.getUserReliefCode(),
						reliefAssigned.getId()
					);
		
			Boolean IsChangeInstanceAbstraction = 
					this.services.instanceManager().getInstanceAbstractionService()
							.changeUserWorked(
								reliefModel.getUserCode(), 
								reliefModel.getUserReliefCode()
							);
			
			
			
			if(reliefModel.getDelegateAll() != null && reliefModel.getDelegateAll()) {
				 this.services.instanceManager().getInstanceAbstractionService()
						.changeUserCreateInstance(reliefModel.getUserCode(), 
												  reliefModel.getUserReliefCode()
												  );
			}
			
			if( updateAssigned != null && reliefAssigned != null && replaceAssignedInBpmAssigned && IsChangeInstanceAbstraction) {
				return true;
			}
			
		}else {
			return noRelief.execute(reliefModel);
		}
		return false;
	}
	
	
	

	public Boolean execute(ReliefAssignedModel reliefModel, List<Long> idInstances) {

		Boolean isChangeInstanceAbstraction = false;

		if (reliefModel != null && idInstances != null && !idInstances.isEmpty()) {
			reliefModel.setDelegateAll(false);
			if (this.execute(reliefModel)) {
				if (!idInstances.isEmpty() && reliefModel.getUserReliefCode() != null) {
					isChangeInstanceAbstraction = this.services.instanceManager().getInstanceAbstractionService()
							.changeUserCreateInstance(reliefModel, idInstances);
				}
			}

		} 
		return isChangeInstanceAbstraction;
	}
	
	
	
	



	
}
