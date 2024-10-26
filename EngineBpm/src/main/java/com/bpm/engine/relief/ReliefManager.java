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
	private IReliefStrategy reliefStrategy;
	private ReliefAssignedMapper mapper;
	private Unsuscribe unsuscribe;
	private TemporaryChange temporaryChange;
	private ChangeBpmRole changeBpmRole;

	@Autowired
	public ReliefManager(ReliefAssignedMapper mapper, IReliefAssignedService service,
			Unsuscribe unsuscribe, TemporaryChange changePermissions, ChangeBpmRole changeBpmRole) {
		this.mapper = mapper;
		this.service = service;
		this.unsuscribe = unsuscribe;
		this.temporaryChange = changePermissions;
		this.changeBpmRole = changeBpmRole;
	}

	public void setStrategy(Integer strategy) {

		switch (strategy) {
		case 1:
			this.reliefStrategy = this.changeBpmRole;
			break;
		case 2:
			this.reliefStrategy = this.unsuscribe;
			break;
		default:
			this.reliefStrategy = this.temporaryChange;
			break;
		}
	}

	
	public boolean executeStrategy(ReliefDTO reliefDTO) {
		ReliefAssignedModel reliefModel = mapper.toModel(reliefDTO);
		service.createReliefAssigned(reliefModel);
		return reliefStrategy.executeRelief(reliefDTO);
	}
	

}
