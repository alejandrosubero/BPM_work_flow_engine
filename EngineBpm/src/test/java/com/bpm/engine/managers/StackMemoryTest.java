package com.bpm.engine.managers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.TaskTypeModel;

@ExtendWith(MockitoExtension.class)
class StackMemoryTest {

    @Mock
    private StackMemoryReferentManager referentManager;

    private StackMemory stackMemory;
    private static final int MAX_QUEUE_SIZE = 15;

    @BeforeEach
    void setUp() {
        stackMemory = new StackMemory(MAX_QUEUE_SIZE, referentManager);
        stackMemory.startProcessing();
    }

    // Helper method to create test instances
    private InstanceAbstractionModel createTestInstance(Long id) {
        return InstanceAbstractionModel.builder()
            .idInstance(id)
            .instanOf("TestInstance")
            .name("Test Task " + id)
            .title("Test Title")
            .type(TaskTypeModel.builder().build())
            .idProcess(1L)
            .idRefenet(100L)
            .idParent(null)
            .idInstanceOfProcess(1000L)
            .codeProcess("PROC_" + id)
            .codeReferent("REF_" + id)
            .apprubeType(1)
            .isParallel(false)
            .status("PENDING")
            .active(true)
            .dateCreate(new Date())
            .userCreateInstance("testUser")
            .level(1)
            .build();
    }

    @Test
    void testAddElement_Success() {
        // Arrange
        InstanceAbstractionModel instance = createTestInstance(1L);
        when(referentManager.getDeque(instance.getIdInstance())).thenReturn(null);
        when(referentManager.instanceIsWorking(instance.getIdInstance())).thenReturn(false);

        // Act
        Boolean result = stackMemory.addElement(instance, null);

        // Assert
        assertTrue(result);
        verify(referentManager).putInConcurrentDequeMap(instance);
    }

    @Test
    void testAddElement_PriorityQueue() {
        // Arrange
        InstanceAbstractionModel instance = createTestInstance(2L);
        when(referentManager.getDeque(instance.getIdInstance())).thenReturn(null);
        when(referentManager.instanceIsWorking(instance.getIdInstance())).thenReturn(false);

        // Act
        Boolean result = stackMemory.addElement(instance, "p");

        // Assert
        assertTrue(result);
        verify(referentManager).putInConcurrentDequeMap(instance);
    }

    @Test
    void testAddElement_AlreadyInDeque() {
        // Arrange
        InstanceAbstractionModel instance = createTestInstance(3L);
        when(referentManager.getDeque(instance.getIdInstance())).thenReturn(instance);
        when(referentManager.instanceIsWorking(instance.getIdInstance())).thenReturn(false);

        // Act
        Boolean result = stackMemory.addElement(instance, null);

        // Assert
        assertFalse(result);
        verify(referentManager, never()).putInConcurrentDequeMap(instance);
    }

    @Test
    void testAddElement_CurrentlyWorking() {
        // Arrange
        InstanceAbstractionModel instance = createTestInstance(4L);
        when(referentManager.getDeque(instance.getIdInstance())).thenReturn(null);
        when(referentManager.instanceIsWorking(instance.getIdInstance())).thenReturn(true);

        // Act
        Boolean result = stackMemory.addElement(instance, null);

        // Assert
        assertFalse(result);
        verify(referentManager, never()).putInConcurrentDequeMap(instance);
    }

    @Test
    void testThreadPoolSize_Increase() throws Exception {
        // Arrange
        ThreadPoolExecutor executor = (ThreadPoolExecutor) ReflectionTestUtils.getField(stackMemory, "executor");
        assertNotNull(executor);

        // Fill the queue to trigger increase
        for (int i = 0; i < (int)(MAX_QUEUE_SIZE * 0.7); i++) {
            executor.getQueue().offer(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Act - this will trigger threadPoolSize() internally
        InstanceAbstractionModel instance = createTestInstance(5L);
        when(referentManager.getDeque(instance.getIdInstance())).thenReturn(null);
        when(referentManager.instanceIsWorking(instance.getIdInstance())).thenReturn(false);
        stackMemory.addElement(instance, null);

        // Assert
        assertTrue(executor.getMaximumPoolSize() >= 4);
    }

    @Test
    void testShutdown() throws InterruptedException {
        // Act
        stackMemory.shutdown();

        // Get executor through reflection
        ThreadPoolExecutor executor = (ThreadPoolExecutor) ReflectionTestUtils.getField(stackMemory, "executor");
        assertNotNull(executor);

        // Assert
        assertTrue(executor.isShutdown());
        assertTrue(executor.awaitTermination(1, TimeUnit.SECONDS));
    }

    @Test
    void testManagerError() {
        // Arrange
        InstanceAbstractionModel instance = createTestInstance(6L);
        when(referentManager.getInstance(instance.getIdInstance())).thenReturn(instance);

        // Get private field through reflection
        ReflectionTestUtils.setField(stackMemory, "returnOfError", 0);

        // Act - using reflection to call private method
        ReflectionTestUtils.invokeMethod(stackMemory, "managerError", instance);

        // Assert
        verify(referentManager).removeInstanceOfReferentBook(instance.getIdInstance());
        verify(referentManager).getInstance(instance.getIdInstance());
        
        // Verify that returnOfError was incremented
        int returnOfError = (int) ReflectionTestUtils.getField(stackMemory, "returnOfError");
        assertEquals(1, returnOfError);
    }

    @Test
    void testProcessQueue_EmptyQueues() throws InterruptedException {
        // Act
        Thread thread = new Thread(() -> stackMemory.processQueue());
        thread.start();
        Thread.sleep(100); // Give some time for processing

        // Assert
        assertFalse(thread.isInterrupted());
    }

    @Test
    void testImpruvedThreadPoolExecutor() {
        // Arrange
        ReflectionTestUtils.setField(stackMemory, "maxQueueSizeFat", new java.util.concurrent.atomic.AtomicBoolean(true));
        ReflectionTestUtils.setField(stackMemory, "newMaxQueueSize", 20);

        // Act
        stackMemory.impruvedThreadPoolExecutor();

        // Assert
        ThreadPoolExecutor executor = (ThreadPoolExecutor) ReflectionTestUtils.getField(stackMemory, "executor");
        assertNotNull(executor);
        assertFalse((boolean) ReflectionTestUtils.getField(stackMemory, "maxQueueSizeFat"));
        assertEquals(0, ReflectionTestUtils.getField(stackMemory, "newMaxQueueSize"));
    }
}