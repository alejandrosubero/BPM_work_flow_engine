package com.bpm.engine.managers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.bpm.engine.models.InstanceAbstractionModel;

@ExtendWith(MockitoExtension.class)
public class StackMemoryTest {

    private StackMemory stackMemory;

    @Mock
    private StackMemoryReferentManager referentManager;

    @Mock
    private ThreadPoolExecutor executor;

    private final int MAX_QUEUE_SIZE = 15;

    @BeforeEach
    void setUp() {
        stackMemory = new StackMemory(MAX_QUEUE_SIZE, referentManager);
        ReflectionTestUtils.setField(stackMemory, "executor", executor);
    }

    @Test
    void testAddElement_WithValidPriorityElement_ShouldAddSuccessfully() {
        // Arrange
        InstanceAbstractionModel model = createTestModel(1L);
        when(referentManager.getDeque(1L)).thenReturn(null);
        when(referentManager.instanceIsInWorkingReferentBook(1L)).thenReturn(false);
        when(executor.getQueue().size()).thenReturn(5);

        // Act
        Boolean result = stackMemory.addElement(model, "p");

        // Assert
        assertTrue(result);
        verify(referentManager).putInConcurrentDequeMap(model);
    }

    @Test
    void testAddElement_WithDuplicateElement_ShouldNotAdd() {
        // Arrange
        InstanceAbstractionModel model = createTestModel(1L);
        when(referentManager.getDeque(1L)).thenReturn(model);
        when(referentManager.instanceIsInWorkingReferentBook(1L)).thenReturn(true);

        // Act
        Boolean result = stackMemory.addElement(model, "p");

        // Assert
        assertFalse(result);
        verify(referentManager, never()).putInConcurrentDequeMap(any());
    }

    @Test
    void testAddElement_WithNullElement_ShouldReturnFalse() {
        // Act
        Boolean result = stackMemory.addElement(null, "p");

        // Assert
        assertFalse(result);
        verify(referentManager, never()).putInConcurrentDequeMap(any());
    }

    @Test
    void testProcessQueue_WithValidElement() throws InterruptedException {
        // Arrange
        InstanceAbstractionModel model = createTestModel(1L);
        ReflectionTestUtils.setField(stackMemory, "status2", new java.util.concurrent.atomic.AtomicBoolean(true));
        
        // Mock behavior for one iteration
        when(referentManager.getInstanceInWorkingReferentBook(1L)).thenReturn(model);
        
        // Act
        Thread thread = new Thread(() -> stackMemory.processQueue());
        thread.start();
        Thread.sleep(100); // Give some time for processing
        ReflectionTestUtils.setField(stackMemory, "status2", new java.util.concurrent.atomic.AtomicBoolean(false));
        thread.join(1000);

        // Assert
        verify(referentManager).removeInstanceOfWorkingReferentBook(1L);
    }

    @Test
    void testShutdown_ShouldTerminateExecutor() throws InterruptedException {
        // Arrange
        when(executor.awaitTermination(5, TimeUnit.SECONDS)).thenReturn(true);

        // Act
        stackMemory.shutdown();

        // Assert
        verify(executor).shutdown();
        verify(executor).awaitTermination(5, TimeUnit.SECONDS);
        verify(executor, never()).shutdownNow();
    }

    @Test
    void testShutdown_WithTimeout_ShouldForceTermination() throws InterruptedException {
        // Arrange
        when(executor.awaitTermination(5, TimeUnit.SECONDS)).thenReturn(false);

        // Act
        stackMemory.shutdown();

        // Assert
        verify(executor).shutdown();
        verify(executor).awaitTermination(5, TimeUnit.SECONDS);
        verify(executor).shutdownNow();
    }

    @Test
    void testIncreasePoolSize_ShouldUpdateMaximumPoolSize() {
        // Arrange
        when(executor.getCorePoolSize()).thenReturn(2);

        // Act
        stackMemory.increasePoolSize(6);

        // Assert
        verify(executor).setMaximumPoolSize(6);
    }

    @Test
    void testDecreasePoolSize_ShouldUpdateMaximumPoolSize() {
        // Arrange
        when(executor.getMaximumPoolSize()).thenReturn(6);

        // Act
        stackMemory.decreasePoolSize(4);

        // Assert
        verify(executor).setMaximumPoolSize(4);
    }

    private InstanceAbstractionModel createTestModel(Long id) {
        return InstanceAbstractionModel.builder()
                .idInstance(id)
                .name("Test Instance")
                .active(true)
                .build();
    }

//    @Test
//    void testManagerError_WithinMaxRetries() {
//        // Arrange
//        InstanceAbstractionModel model = createTestModel(1L);
//        when(referentManager.getInstanceInWorkingReferentBook(1L)).thenReturn(model);
//        ReflectionTestUtils.setField(stackMemory, "returnOfError", 0);
//        ReflectionTestUtils.setField(stackMemory, "maxreturnOfError", 3);
//
//        // Act
//        stackMemory.managerError(model);
//
//        // Assert
//        verify(referentManager).removeInstanceOfWorkingReferentBook(1L);
//        verify(referentManager).getInstanceInWorkingReferentBook(1L);
//    }

    @Test
    void testThreadPoolSize_ShouldIncreasePoolSize() {
        // Arrange
        when(executor.getQueue().size()).thenReturn((int)(MAX_QUEUE_SIZE * 0.7));
        when(executor.getCorePoolSize()).thenReturn(2);

        // Act
        ReflectionTestUtils.invokeMethod(stackMemory, "threadPoolSize");

        // Assert
        verify(executor, atLeastOnce()).getQueue();
        verify(executor).setMaximumPoolSize(4);
    }
}