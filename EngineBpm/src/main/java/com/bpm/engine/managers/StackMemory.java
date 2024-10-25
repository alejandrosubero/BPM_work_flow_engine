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

import com.bpm.engine.models.InstanceAbstractionModel;
import com.google.gson.Gson;

// https://chatgpt.com/c/66fd9003-c050-8012-a22e-e02f9d4d1422

@Component
public class StackMemory {

	private static final Logger logger = LogManager.getLogger(StackMemory.class);

	private ConcurrentLinkedDeque<InstanceAbstractionModel> queuePriorityTask = new ConcurrentLinkedDeque<>();
	private ConcurrentLinkedDeque<InstanceAbstractionModel> deque = new ConcurrentLinkedDeque<>();
	
	private ThreadPoolExecutor executor;
	private final int maxQueueSize;
	private final BlockingQueue<Runnable> workQueue;
	private Integer newMaxQueueSize = null;
	
	private TimeUnit unit = TimeUnit.SECONDS;
	private AtomicBoolean status2 = new AtomicBoolean(true);
	private AtomicBoolean maxQueueSizeFat = new AtomicBoolean(false);
	private AtomicBoolean maximumPoolSizeFat = new AtomicBoolean(false);
	
	private Integer maxreturnOfError = 3;
	private Integer returnOfError = 0;
	private int corePoolSize = 2;
	private int maximumPoolSize = 4;
	private long keepAliveTime = 60L;
	
	private StackMemoryReferentManager referentManager;

	public StackMemory(@Value("${deque.maxSize:15}") int maxQueueSize, StackMemoryReferentManager referentManager) {
		this.maxQueueSize = maxQueueSize;
		this.workQueue = new ArrayBlockingQueue<>(maxQueueSize);
		this.referentManager = referentManager;
	}

	@PostConstruct
	public void startProcessing() {
		
		 executor = new ThreadPoolExecutor(
	                corePoolSize, maximumPoolSize, keepAliveTime,
	                unit,  workQueue, new ThreadPoolExecutor.CallerRunsPolicy()
	        );
		
		for (int i = 0; i < corePoolSize; i++) {
			executor.execute(this::processQueue);
		}
	}



	@SuppressWarnings("unused")
	public void processQueue() {

		while (this.status2.get()) {

			InstanceAbstractionModel element = null;
			logger.info(Thread.currentThread().getName() + "In processQueue... ");
			try {
				 element = !queuePriorityTask.isEmpty()? queuePriorityTask.pollLast():deque.pollLast();
				
				
				 if (element != null) {
					 this.referentManager.removeInstanceOfConcurrentDequeMap(element.getIdInstance());
					 this.referentManager.putInstanceInReferentBook(element);
					 System.out.println(Thread.currentThread().getName() + " procesando: " + element);

					 		// Simular trabajo con el elemento // ............

					 System.out.println("Simular trabajo con el elemento = " + element != null );
					 
					this.referentManager.removeInstanceOfReferentBook(element.getIdInstance());
					
				} else {
						this.status2.compareAndSet(true, false);
						this.impruvedThreadPoolExecutor();
						logger.info(Thread.currentThread().getName() + "In processQueue status active :: "+ this.status2.get() );
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				this.managerError(element);
				Thread.currentThread().interrupt();
			}			
		}
	}
	
	
	private void managerError(InstanceAbstractionModel element) {
		
		InstanceAbstractionModel intanceInError = this.referentManager.getInstance(element.getIdInstance());
		this.referentManager.removeInstanceOfReferentBook(intanceInError.getIdInstance());
		
		if(returnOfError <= maxreturnOfError) {
			returnOfError++;
			this.addElement(intanceInError, "p");
			// TODO: notificar el error al sistema de error Indeicado que fue manejado y task_reset...
		}else{
			returnOfError = 0;
			Gson gson = new Gson();
			String jsonObject = gson.toJson(intanceInError);
			
			//TODO: REALIZA EL ENVIO AL SISTEMA DE NOTIFICACION DE ERROR Y ANEXZA EL OBJETO EN JSON.
		}
		
	}
	

	public Boolean addElement(InstanceAbstractionModel newElement, String type) {

		Boolean isSave = false;
		
		if (newElement != null ) {
			
// TODO: Al no cumplir y retornar false, hay que ver como retorna un mensaje tambien para indicar por que fallo separando el null 
		// del elemento en el pool de trabajo
			
			Boolean isPresentInDeque = this.referentManager.getDeque(newElement.getIdInstance()).equals(newElement);
			Boolean isWorkingInPool = this.referentManager.instanceIsWorking(newElement.getIdInstance());
			
		if (!isPresentInDeque && !isWorkingInPool) {

			try {
				 isSave = type != null && type.toLowerCase().equals("p")? queuePriorityTask.add(newElement):deque.add(newElement);
				 this.referentManager.putInConcurrentDequeMap(newElement);
				 
				if (isSave) {
					logger.info("New Element was add");
					
					if(!this.status2.get()) {
						this.status2.compareAndSet(false, true);
					}
					
				} else {
					logger.error("Fail to add New Element in the deque...");
				}
				
				this.threadPoolSize();
				
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
	}

		return isSave;
	}

	
	private void threadPoolSize() {

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
		
		

}
