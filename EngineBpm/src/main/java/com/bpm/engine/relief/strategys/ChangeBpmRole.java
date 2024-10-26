package com.bpm.engine.relief.strategys;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bpm.engine.managers.AssignmentTaskManager;
import com.bpm.engine.managers.BpmAssignedManager;
import com.bpm.engine.managers.ProcessAndInstanceFacade;
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
	
	private ReliefAssignedMapper mapper;
		
	

	@Autowired
	public ChangeBpmRole(ProcessAndInstanceFacade services, AssignmentTaskManager assignmentTaskManager,
			BpmAssignedManager bpmAssignedManager, ReliefAssignedMapper mapper) {
		this.services = services;
		this.assignmentTaskManager = assignmentTaskManager;
		this.bpmAssignedManager = bpmAssignedManager;
		this.mapper = mapper;
	}
	

	
	
	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {
		
		Boolean response = false;
		ReliefAssignedModel reliefModel = mapper.toModel(reliefDTO);
		
		if(reliefDTO.getIdInstances() !=null && !reliefDTO.getIdInstances().isEmpty()) {
			reliefModel.setDelegateAll(false);
			response = this.execute(reliefModel, reliefDTO.getIdInstances());
		}else {
			response = this.execute( reliefModel);
		}
		
		return response;
	}
	
	

	

	public Boolean execute(ReliefAssignedModel reliefModel) {
		
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
			
			if(reliefModel.getDelegateAll()) {
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
			return executeNoRelief(reliefModel);
		}
		return false;
	}
	
	
	

	public Boolean execute(ReliefAssignedModel reliefModel, List<Long> idInstances ) {
		
	
		Boolean isChangeInstanceAbstraction =  false;
		if(execute(reliefModel)) {
			if(!idInstances.isEmpty() && reliefModel.getUserReliefCode() != null) {
				isChangeInstanceAbstraction = this.services.instanceManager().getInstanceAbstractionService()
						.changeUserCreateInstance( reliefModel,idInstances);
			}
		}
		return isChangeInstanceAbstraction;
	}
	
	
	
	private Boolean executeNoRelief(ReliefAssignedModel reliefModel) {
		
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





	
}
