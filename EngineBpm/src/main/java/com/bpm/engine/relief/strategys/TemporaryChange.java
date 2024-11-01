package com.bpm.engine.relief.strategys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.service.IReliefAssignedService;


@Component
public class TemporaryChange implements IReliefStrategy{

	
	@Autowired
	private ReliefAssignedMapper mapper;
	
	private IReliefAssignedService serviceRelief;
	
	
	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {
		
		Boolean response = false;
		
		try {
			
			ReliefAssignedModel reliefModel = serviceRelief.createReliefAssigned(reliefDTO);
			
//			updateUserAssignedForUserReliefInBpmAssigned(String codeEmployee, String codeEmployeeRelief,Long idAssignedRelief)
			
			
			
			
			serviceRelief.updateActive(false, reliefModel.getIdRelief());
			
			
		} catch (Exception e) {
			
		}
	
		
		return response;
	}

}
