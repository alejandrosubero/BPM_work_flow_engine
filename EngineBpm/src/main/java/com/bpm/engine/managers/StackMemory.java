package com.bpm.engine.managers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bpm.engine.model.InstanceAbstractionModel;

// https://chatgpt.com/c/66fd9003-c050-8012-a22e-e02f9d4d1422

@Component
public class StackMemory {

	private static final Logger logger = LogManager.getLogger(StackMemory.class);

	private ConcurrentLinkedDeque<InstanceAbstractionModel> queuePriorityTask = new ConcurrentLinkedDeque<>();
	private ConcurrentLinkedDeque<InstanceAbstractionModel> deque = new ConcurrentLinkedDeque<>();
	private ThreadPoolExecutor executor;
	
	private final int maxQueueSize;
	private final BlockingQueue<Runnable> workQueue;
	private int corePoolSize = 2;
	private int maximumPoolSize = 4;
	private long keepAliveTime = 60L;
	private TimeUnit unit = TimeUnit.SECONDS;
	
	private AtomicBoolean status2 = new AtomicBoolean(true);
	private AtomicBoolean maxQueueSizeFat = new AtomicBoolean(false);
	private AtomicBoolean maximumPoolSizeFat = new AtomicBoolean(false);
	private Integer newMaxQueueSize = null;

	public StackMemory(@Value("${deque.maxSize:15}") int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
		  this.workQueue = new ArrayBlockingQueue<>(maxQueueSize);
	}

	@PostConstruct
	public void startProcessing() {
		
		 executor = new ThreadPoolExecutor(
	                corePoolSize,
	                maximumPoolSize,
	                keepAliveTime,
	                unit,
	                workQueue,
	                new ThreadPoolExecutor.CallerRunsPolicy()
	        );
		
		for (int i = 0; i < corePoolSize; i++) {
			executor.execute(this::processQueue);
		}
	}



	public void processQueue() {

		while (this.status2.get()) {
			
			InstanceAbstractionModel element = null;
			
				 element = !queuePriorityTask.isEmpty()? queuePriorityTask.pollLast():deque.pollLast();
				 
	
			if (element != null) {
				
				System.out.println(Thread.currentThread().getName() + " procesando: " + element);

			 // Simular trabajo con el elemento

			
			} else {
				
				try {
					this.status2.compareAndSet(true, false);
					this.impruvedThreadPoolExecutor();
					
				} catch (Exception e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
					logger.error(e);
				}
			}
		}
	}
	

	public Boolean addElement(InstanceAbstractionModel newElement, String type) {

		Boolean isSave = false;

		if (newElement != null) {

			try {
				 isSave = type != null && type.toLowerCase().equals("p")? queuePriorityTask.add(newElement):deque.add(newElement);
				 
				if (isSave) {
					logger.info("New Element was add");
					
					if(!this.status2.get()) {
						this.status2.compareAndSet(false, true);
					}
					
				} else {
					logger.error("Fail to add New Element in the deque...");
				}
				
				this.queueSize();
				
				if (deque.size() < maxQueueSize) {
					if (executor.getQueue().size() < maxQueueSize) {
						executor.execute(this::processQueue);
					}

				} else {
					logger.error("Backpressure for control off pool...");
				}

			} catch (IllegalStateException ei) {
				isSave = false;
				ei.printStackTrace();
			}
		}

		return isSave;
	}

	private void queueSize() {

		if (executor.getQueue().size() > (maxQueueSize * 0.6) && executor.getQueue().size() < (maxQueueSize * 0.95)) {
			increasePoolSize(maximumPoolSize);
			logger.info(" the Queue task are in limit the Thread pool was is increase...");
		}

		if (executor.getQueue().size() <= (maxQueueSize * 0.5) && executor.getQueue().size() > executor.getCorePoolSize()) {
			logger.info(" the Queue task are lower of limit the Thread pool was is decrese...");
			decreasePoolSize(corePoolSize);
		}
		
		if (executor.getQueue().size() >= maxQueueSize && executor.getQueue().size() < deque.size() && !this.maximumPoolSizeFat.get()) {
			increasePoolSize(maximumPoolSize+2);
			logger.info(" the Queue task are in limit the Thread pool was is increase...");
			this.setImpruvedThreadPoolExecutor();			
		}
	}

	@PreDestroy
	public void shutdown() throws InterruptedException {
		executor.shutdown();
		if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
			executor.shutdownNow();
		}
	}
	
	
	
	  public void increasePoolSize(int newSize) {
	        if (newSize > ((ThreadPoolExecutor) executor).getCorePoolSize()) {
	            ((ThreadPoolExecutor) executor).setMaximumPoolSize(newSize);
	        }
	    }

	    public void decreasePoolSize(int newSize) {
	        if (newSize < ((ThreadPoolExecutor) executor).getMaximumPoolSize()) {
	            ((ThreadPoolExecutor) executor).setMaximumPoolSize(newSize);
	        }
	    }
	
		
		
		public void impruvedThreadPoolExecutor(){
			
			if(maxQueueSizeFat.get()) {
				try {
					this.shutdown();
					this.resetFlat(); 
					 
					final BlockingQueue<Runnable> workQueueNew = new ArrayBlockingQueue<>(newMaxQueueSize);
					
					 executor = new ThreadPoolExecutor(
				                corePoolSize, maximumPoolSize, keepAliveTime,unit,
				                workQueueNew,
				                new ThreadPoolExecutor.CallerRunsPolicy()
				        );

					for (int i = 0; i < corePoolSize; i++) {
						executor.execute(this::processQueue);
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					 this.newMaxQueueSize = 0;
				}
			}
		}
		
		private void setImpruvedThreadPoolExecutor() {
			this.maxQueueSizeFat.compareAndSet(false, true);
			this.newMaxQueueSize = deque.size();
			this.maximumPoolSizeFat.compareAndSet(false, true);
		}
		
		private void resetFlat() {
			this.maxQueueSizeFat.compareAndSet(true, false);
			this.maximumPoolSizeFat.compareAndSet(true, false);
			this.status2.compareAndSet(false, true);
		}
		
		
//		public void increasePoolSizeD(int newSize) {
//		if (newSize > executor.getCorePoolSize()) {
//			executor.setMaximumPoolSize(newSize);
//		}
//	}
//
//	public void decreasePoolSizeD(int newSize) {
//		if (newSize < executor.getMaximumPoolSize()) {
//			executor.setMaximumPoolSize(newSize);
//		}
//	}

}
