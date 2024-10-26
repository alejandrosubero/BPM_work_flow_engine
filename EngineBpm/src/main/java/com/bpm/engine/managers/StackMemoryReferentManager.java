package com.bpm.engine.managers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.bpm.engine.models.InstanceAbstractionModel;



/***
 * this class is backup of operation in  the instances in concurrent Queue
 * for manager error
 */
@Service
public class StackMemoryReferentManager {

	
	private  ConcurrentHashMap<Long, InstanceAbstractionModel> concurrentHashMap = new ConcurrentHashMap<Long, InstanceAbstractionModel>();
	private  ConcurrentHashMap<Long, InstanceAbstractionModel> inConcurrentLinkedDeque = new ConcurrentHashMap<Long, InstanceAbstractionModel>();
	
	
	public  void putInConcurrentDequeMap(InstanceAbstractionModel instance) {
		this.inConcurrentLinkedDeque.put(instance.getIdInstance(), instance);
	}
	
	public  InstanceAbstractionModel getDeque(Long instanceId) {
		return this.inConcurrentLinkedDeque.get(instanceId);
	}
	
	public  Boolean instanceInConcurrentDeque(Long instanceId) {
		return this.inConcurrentLinkedDeque.contains(instanceId);
	}
	
	public  Boolean removeInstanceOfConcurrentDequeMap(Long instanceId) {
		return this.inConcurrentLinkedDeque.remove(instanceId) != null;
	}
	
	
	public  void putInstanceInWorkingReferentBook(InstanceAbstractionModel instance) {
		this.concurrentHashMap.put(instance.getIdInstance(), instance);
	}
	
	
	public  InstanceAbstractionModel getInstanceInWorkingReferentBook(Long instanceId) {
		return this.concurrentHashMap.get(instanceId);
	}
	
	public  Boolean instanceIsInWorkingReferentBook(Long instanceId) {
		return this.concurrentHashMap.contains(instanceId);
	}
	
	public  Boolean removeInstanceOfWorkingReferentBook(Long instanceId) {
		return this.concurrentHashMap.remove(instanceId) != null;
	}
	
	
}
