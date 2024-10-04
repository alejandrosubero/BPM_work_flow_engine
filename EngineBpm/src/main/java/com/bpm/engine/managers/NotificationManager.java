package com.bpm.engine.managers;

import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.notification.services.NotificationService;

public class NotificationManager {

	private NotificationService service; 
	
	
	
	@Au
	public NotificationManager(NotificationService service) {
		super();
		this.service = service;
	}




	public void notify(InstanceAbstractionModel instanceTask, String errorStatus) {
		
	
	}
}
