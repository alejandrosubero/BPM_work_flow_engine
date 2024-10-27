package com.bpm.engine.relief.strategys;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bpm.engine.managers.facades.NoReliefFacade;
import com.bpm.engine.managers.facades.ServiceBpmAndAssignedFacade;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;


@Component
public class Unsuscribe implements IReliefStrategy{

	private static final Logger logger = LogManager.getLogger(Unsuscribe.class);
	
	
	private ReliefAssignedMapper mapper;
	private ServiceBpmAndAssignedFacade serviceFacade;
	private NoReliefFacade noRelief;

	
	
	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {
		logger.info("Execute Unsuscribe... ");
		Boolean response = false;
		ReliefAssignedModel reliefModel = mapper.toModel(reliefDTO);
		
		List<BpmAssignedModel> listBpmAssignedInstances = serviceFacade.getBpmAssignedService().findByCodeEmployeeAndActive(reliefModel.getUserCode(), true);
		listBpmAssignedInstances.parallelStream().forEach(assigned ->{
			assigned.setActive(false);
			serviceFacade.getBpmAssignedService().saveOrUpdateBpmAssigned(assigned);
		});
		
	   AssignedModel assignedInstance = serviceFacade.getAssignedService().findByCodeEmployeeAndActive(reliefModel.getUserCode(), true);
	   assignedInstance.setActive(false);
	   serviceFacade.getAssignedService().saveOrUpdateAssigned(assignedInstance);
	   response = this.noRelief.execute(reliefModel);
	
		return response;
	}
	
	
	
	
	





}
