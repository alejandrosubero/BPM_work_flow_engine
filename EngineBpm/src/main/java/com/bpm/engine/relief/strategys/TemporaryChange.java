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
			
			if(reliefModel.getUserReliefCode() != null) {
				
				AssignedModel reliefAssigned = null;
				Boolean replaceAssignedInBpmAssigned = null;
				Boolean IsChangeInstanceAbstraction = null;
				
				
				 reliefAssigned = assignmentTaskManager.getAssignedOrCreateAssignedInBpmSystem(reliefModel.getUserReliefCode());
			
				if(reliefAssigned != null) {
					
					 replaceAssignedInBpmAssigned = bpmAssignedManager.updateUserAssignedForUserReliefInBpmAssigned(
								reliefModel.getUserCode(), 
								reliefModel.getUserReliefCode(),
								reliefAssigned.getId()
								);
					
						 IsChangeInstanceAbstraction = 
								this.services.instanceManager().getInstanceAbstractionService()
										.changeUserWorked(
											reliefModel.getUserCode(), 
											reliefModel.getUserReliefCode()
										);
				}
				
				if( reliefAssigned != null && replaceAssignedInBpmAssigned && IsChangeInstanceAbstraction) {
					response = true;
				}
			}
			
//			serviceRelief.updateActive(false, reliefModel.getIdRelief());
			
			
		} catch (Exception e) {
			return response;
		}
	
		
		return response;
	}







}
