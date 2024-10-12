package com.bpm.engine.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.ReliefAssignedModel;
import com.bpm.engine.serviceImplement.InstanceTaskEmailServiceImplement;

public class ReliefManager {
	
	private static final Logger logger = LogManager.getLogger(ReliefManager.class);

	private InstanceProcessManager instanceProcessManager;

	private InstanceProcessManagerDTO dtoProcessManagerDTO;

	private AssignmentTaskManager assignmentTaskManager;
	
	private BpmAssignedManager bpmAssignedManager;

	@Autowired
	public ReliefManager(InstanceProcessManager instanceProcessManager, InstanceProcessManagerDTO dtoProcessManagerDTO,
			AssignmentTaskManager assignmentTaskManager, BpmAssignedManager bpmAssignedManager) {
		this.instanceProcessManager = instanceProcessManager;
		this.dtoProcessManagerDTO = dtoProcessManagerDTO;
		this.assignmentTaskManager = assignmentTaskManager;
		this.bpmAssignedManager = bpmAssignedManager;
	}
	

	
	public Boolean changeBpmRole(ReliefAssignedModel reliefModel) {
		
		logger.info( "Started change Bpm role...");
		
		AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);
		
		AssignedModel reliefAssigned = assignmentTaskManager.getAssignedOrCreateAssignedInBpmSystem(reliefModel.getUserReliefCode());
				
		Boolean replaceAssignedInBpmAssigned = bpmAssignedManager.replaceUserAssignedForUserReliefInBpmAssigned(
				reliefModel.getUserCode(), reliefModel.getUserReliefCode(),reliefAssigned.getId()
				);
		
		//TODO: continuar...
		
		return false;
	}



	public Boolean unsuscribe(ReliefAssignedModel reliefModel) {
		return false;
	}

	private Boolean changePermissions(ReliefAssignedModel reliefModel) {
		return false;
	}
}
