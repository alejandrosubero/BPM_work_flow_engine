package com.bpm.engine.managers;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.ReliefAssignedModel;

public class ReliefManager {

	private InstanceProcessManager instanceProcessManager;

	private InstanceProcessManagerDTO dtoProcessManagerDTO;

	private AssignmentTaskManager assignmentTaskManager;

	@Autowired
	public ReliefManager(InstanceProcessManager instanceProcessManager, InstanceProcessManagerDTO dtoProcessManagerDTO,
			AssignmentTaskManager assignmentTaskManager) {
		super();
		this.instanceProcessManager = instanceProcessManager;
		this.dtoProcessManagerDTO = dtoProcessManagerDTO;
		this.assignmentTaskManager = assignmentTaskManager;
	}

	public Boolean changeBpmRole(ReliefAssignedModel reliefModel) {

		AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);
		
		//TODO: BUSCAR LAS INSTANCIAS DONDE reliefModel.getUserCode() Y COLOCAR EL reliefModel.getUserReliefCode() si se tiene.
		// en caso de no existir por cada uno de los usuarios buscar el assigned approved 
		// el mismo caso para bpmAsigne.
		
		return false;
	}

	public Boolean unsuscribe(ReliefAssignedModel reliefModel) {
		return false;
	}

	private Boolean changePermissions(ReliefAssignedModel reliefModel) {
		return false;
	}
}
