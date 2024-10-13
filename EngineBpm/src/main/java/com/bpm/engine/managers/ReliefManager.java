package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ReliefAssignedModel;
import com.bpm.engine.serviceImplement.InstanceTaskEmailServiceImplement;
import com.bpm.engine.utility.InstanOf;

public class ReliefManager {
	
	private static final Logger logger = LogManager.getLogger(ReliefManager.class);

	 private ProcessAndInstanceFacade services;

	private InstanceProcessManagerDTO dtoProcessManagerDTO;

	private AssignmentTaskManager assignmentTaskManager;
	
	private BpmAssignedManager bpmAssignedManager;

	@Autowired
	public ReliefManager(ProcessAndInstanceFacade services, InstanceProcessManagerDTO dtoProcessManagerDTO, AssignmentTaskManager assignmentTaskManager, BpmAssignedManager bpmAssignedManager) {
	
		this.services = services;
		this.dtoProcessManagerDTO = dtoProcessManagerDTO;
		this.assignmentTaskManager = assignmentTaskManager;
		this.bpmAssignedManager = bpmAssignedManager;
	}
	

	
	
	public Boolean changeBpmRole(ReliefAssignedModel reliefModel, Boolean delegateAll) {
		
		logger.info( "Started change Bpm role...");
		
		AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);
		
		if(reliefModel.getUserReliefCode() != null) {
			AssignedModel reliefAssigned = assignmentTaskManager.getAssignedOrCreateAssignedInBpmSystem(
					reliefModel.getUserReliefCode());
			
			Boolean replaceAssignedInBpmAssigned = bpmAssignedManager.replaceUserAssignedForUserReliefInBpmAssigned(
						reliefModel.getUserCode(), reliefModel.getUserReliefCode(),reliefAssigned.getId());
		
			Boolean IsChangeInstanceAbstraction = 
					this.services.instanceManager().getInstanceAbstractionService().changeUserWorked(
							reliefModel.getUserCode(), reliefModel.getUserReliefCode());
			
			Boolean isChangeUserCreateInstance =  false;
			
			if(delegateAll) {
				isChangeUserCreateInstance =  this.services.instanceManager().getInstanceAbstractionService()
						.changeUserCreateInstance(reliefModel.getUserCode(), reliefModel.getUserReliefCode());
			
				if( updateAssigned != null && reliefAssigned != null && replaceAssignedInBpmAssigned && IsChangeInstanceAbstraction && isChangeUserCreateInstance) {
					return true;
				}
			}
			
			if( updateAssigned != null && reliefAssigned != null && replaceAssignedInBpmAssigned && IsChangeInstanceAbstraction) {
				return true;
			}
		}else {
			return changeBpmRoleNoRelief(reliefModel);
		}
		return false;
	}
	
	
	
	
	public Boolean changeBpmRole(ReliefAssignedModel reliefModel, List<Long> idInstances ) {
		Boolean isChangeInstanceAbstraction =  false;
		if(changeBpmRole(reliefModel, false)) {
			if(!idInstances.isEmpty() && reliefModel.getUserReliefCode() != null) {
				isChangeInstanceAbstraction = this.services.instanceManager().getInstanceAbstractionService()
						.changeUserCreateInstance( reliefModel,idInstances);
			}
		}
		return isChangeInstanceAbstraction;
	}
	
	
	
	private Boolean changeBpmRoleNoRelief(ReliefAssignedModel reliefModel) {
		
		try {
			AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);
			
			Boolean desactiveOldBpmAssigned = this.bpmAssignedManager.desactiveBpmAssigned(reliefModel.getUserCode());
			
			List<InstanceAbstractionModel>  listaInstances = this.services.instanceManager()
					.getInstanceAbstractionService().findByUserWorked(reliefModel.getUserCode());
			
			for(InstanceAbstractionModel instanceAbstractionModel : listaInstances) {
				
				if(instanceAbstractionModel.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {
					
					BpmAssignedModel temporalAssigned = this.assignmentTaskManager.getOneAssigned(
							instanceAbstractionModel.getCodeReferent(), 
							instanceAbstractionModel.getUserCreateInstance(), 
							instanceAbstractionModel.getIdInstanceOfProcess(),
							instanceAbstractionModel.getIdProcess(),
							0);
					if(temporalAssigned != null) {
						this.services.instanceManager().getInstanceAbstractionService()
						    .updateUserWorked(
						    		temporalAssigned.getCodeEmployee(), 
						    		instanceAbstractionModel.getIdInstance()
						    		);	
					}
				}
			}
			
		} catch ( DataAccessException e) {
			 logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			return false;
		}catch(IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

	public Boolean unsuscribe(ReliefAssignedModel reliefModel) {
		return false;
	}

	private Boolean changePermissions(ReliefAssignedModel reliefModel) {
		return false;
	}
}
