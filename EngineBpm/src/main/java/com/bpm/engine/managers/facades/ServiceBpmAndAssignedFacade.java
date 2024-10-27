package com.bpm.engine.managers.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;


@Service
public class ServiceBpmAndAssignedFacade {

	
	private AssignedService assignedService;
	private BpmAssignedService bpmAssignedService;
	
	
	@Autowired
	public ServiceBpmAndAssignedFacade(AssignedService assignedService, BpmAssignedService bpmAssignedService) {
		super();
		this.assignedService = assignedService;
		this.bpmAssignedService = bpmAssignedService;
	}
	
	
	public AssignedService getAssignedService() {
		return this.assignedService;
	}
	
	public BpmAssignedService getBpmAssignedService() {
		return this.bpmAssignedService;
	}
	
}
