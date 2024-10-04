package com.bpm.engine.managers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Deque;
import java.util.concurrent.*;

// https://chatgpt.com/c/66fd9003-c050-8012-a22e-e02f9d4d1422



@Component
public class StackMemory {
	
    private ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>(); // Asegúrate de que esto sea ConcurrentLinkedDeque
    private ThreadPoolExecutor executor; // Cambiar el tipo a ThreadPoolExecutor
    private final int maxQueueSize;

    public StackMemory( @Value("${deque.maxSize:10}") int maxQueueSize) {
       
        this.maxQueueSize = maxQueueSize; // Tamaño máximo de la cola
    }

    @PostConstruct
    public void startProcessing() {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;

        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new LinkedBlockingQueue<>()
        );

        // Lanzar hilos para procesar los elementos
        for (int i = 0; i < corePoolSize; i++) {
            executor.execute(this::processQueue);
        }
    }

    public void increasePoolSize(int newSize) {
        // Aumentar el tamaño máximo del pool
        if (newSize > executor.getMaximumPoolSize()) {
            executor.setMaximumPoolSize(newSize);
        }
    }

    public void decreasePoolSize(int newSize) {
        // Disminuir el tamaño máximo del pool
        if (newSize < executor.getMaximumPoolSize()) {
            executor.setMaximumPoolSize(newSize);
        }
    }

    public void processQueue() {
        while (true) {
            String element = deque.pollLast(); // Toma el último elemento de la Deque de manera no bloqueante
            if (element != null) {
                System.out.println(Thread.currentThread().getName() + " procesando: " + element);
                try {
                    Thread.sleep(1000); // Simular trabajo con el elemento
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                try {
                    Thread.sleep(500); // Espera si la cola está vacía para no consumir CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    // Método para añadir un nuevo elemento a la Deque en tiempo real
    public void addElement(String newElement) {
        if (deque.size() < maxQueueSize) {
            deque.add(newElement); // Se puede agregar sin bloqueo
            System.out.println("Nuevo elemento agregado: " + newElement);
            // Si la cola está cerca de alcanzar el límite, aumentar el tamaño del pool
            if (executor.getQueue().size() >= (maxQueueSize - 1)) { // Al alcanzar el límite, aumentar el pool
                increasePoolSize(4); // Aumentar el tamaño máximo del pool
            }
            // Notificar a los hilos que hay nuevos elementos para procesar
        } else {
            System.out.println("Backpressure: Se ha alcanzado el tamaño máximo de la cola");
        }
    }
}

