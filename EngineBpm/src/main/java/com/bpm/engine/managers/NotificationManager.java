package com.bpm.engine.managers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.notification.services.NotificationService;

@Component
public class NotificationManager {

	private static NotificationService service; 
	private ConcurrentHashMap<Long, InstanceAbstractionModel> concurrentHashMap = new ConcurrentHashMap<Long, InstanceAbstractionModel>();
		
	@Autowired
	public NotificationManager(NotificationService service) {
		this.service = service;
	}

	public static void notify(InstanceAbstractionModel instanceTask, String errorStatus) {
		service.taskNotification(instanceTask, errorStatus);
	}

	
	public void putInstanve(InstanceAbstractionModel instance) {
		this.concurrentHashMap.put(instance.getIdInstance(), instance);
	}
	
	
	public InstanceAbstractionModel getInstance(Long instanceId) {
		return this.concurrentHashMap.get(instanceId);
	}
	
	public Boolean keyExist(Long instanceId) {
		return this.concurrentHashMap.contains(instanceId);
	}
	
	public Boolean removeInstance(Long instanceId) {
		return this.concurrentHashMap.remove(instanceId) != null;
	}
	
	
}
