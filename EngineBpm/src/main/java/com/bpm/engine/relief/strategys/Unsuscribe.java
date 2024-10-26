package com.bpm.engine.relief.strategys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;


@Component
public class Unsuscribe implements IReliefStrategy{

	@Autowired
	private ReliefAssignedMapper mapper;
	
	
	
	@Override
	public Boolean executeRelief(ReliefDTO reliefDTO) {
		
		Boolean response = false;
		ReliefAssignedModel reliefModel = mapper.toModel(reliefDTO);
		
		
		return response;
	}

}
