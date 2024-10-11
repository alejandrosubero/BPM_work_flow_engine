package com.bpm.engine.managers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.notification.services.NotificationService;

@Component
public class NotificationManager {

	private static NotificationService service; 
	
	@Autowired
	public NotificationManager(NotificationService service) {
		this.service = service;
	}

	public static void notify(InstanceAbstractionModel instanceTask, String errorStatus) {
		service.taskNotification(instanceTask, errorStatus);
	}

	

	
	
}
