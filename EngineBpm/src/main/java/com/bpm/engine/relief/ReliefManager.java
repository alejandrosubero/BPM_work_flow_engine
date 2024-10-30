package com.bpm.engine.relief;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.interfaces.IReliefStrategy;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.service.IReliefAssignedService;
import com.bpm.engine.relief.strategys.ChangeBpmRole;
import com.bpm.engine.relief.strategys.TemporaryChange;
import com.bpm.engine.relief.strategys.Unsuscribe;

@Service
public class ReliefManager {

	private IReliefAssignedService service;
	private Unsuscribe unsuscribe;
	private TemporaryChange temporaryChange;
	private ChangeBpmRole changeBpmRole;

	@Autowired
	public ReliefManager(IReliefAssignedService service, Unsuscribe unsuscribe, TemporaryChange changePermissions, ChangeBpmRole changeBpmRole) {
		this.service = service;
		this.unsuscribe = unsuscribe;
		this.temporaryChange = changePermissions;
		this.changeBpmRole = changeBpmRole;
	}

	private  IReliefStrategy setStrategy(Integer strategy) {

		switch (strategy) {
		case 1:
			return this.changeBpmRole;
		case 2:
			return this.unsuscribe;
			
		default:
			return this.temporaryChange;
		}
	}

	
	public boolean executeRelelief(Integer strategy, ReliefDTO reliefDTO) {
		
		IReliefStrategy reliefStrategy =  setStrategy(strategy);
		
		return reliefStrategy.executeRelief(reliefDTO);
	}
		
	
	
}
