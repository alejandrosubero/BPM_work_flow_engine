package com.bpm.engine.managers;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.bpm.engine.models.InstanceAbstractionModel;



/***
 * this class manage the instances in of BlockingQueue<Runnable> java.util.concurrent.ThreadPoolExecutor.getQueue
 * or task in execution. 
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
	
	
	public  void putInstanceInReferentBook(InstanceAbstractionModel instance) {
		this.concurrentHashMap.put(instance.getIdInstance(), instance);
	}
	
	
	public  InstanceAbstractionModel getInstance(Long instanceId) {
		return this.concurrentHashMap.get(instanceId);
	}
	
	public  Boolean instanceIsWorking(Long instanceId) {
		return this.concurrentHashMap.contains(instanceId);
	}
	
	public  Boolean removeInstanceOfReferentBook(Long instanceId) {
		return this.concurrentHashMap.remove(instanceId) != null;
	}
	
	
}
