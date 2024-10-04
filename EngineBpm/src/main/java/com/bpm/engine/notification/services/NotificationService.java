package com.bpm.engine.notification.services;

import com.bpm.engine.model.InstanceAbstractionModel;


public interface NotificationService {
	public void taskNotification(InstanceAbstractionModel instanceTask, String errorStatus);
}
