package com.bpm.engine.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessAndInstanceFacade {

	
    private InstanceManager instanceManager;
    private ProcessManager processManager;
    
    
    @Autowired
	public ProcessAndInstanceFacade(InstanceManager instanceManager, ProcessManager processManager) {
		super();
		this.instanceManager = instanceManager;
		this.processManager = processManager;
	}
	

    
    public InstanceManager instanceManager() {
    	return this.instanceManager;
    }
    
    
    public ProcessManager processManager() {
    	return this.processManager;
    }
    
    
}
