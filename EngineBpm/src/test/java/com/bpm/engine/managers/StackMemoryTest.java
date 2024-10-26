package com.bpm.engine.managers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.TaskTypeModel;

@ExtendWith(MockitoExtension.class)
public class StackMemoryTest {


    private StackMemoryReferentManager referentManager;


    private InstanceAbstractionModel instanceAbstractionModel;


    private StackMemory stackMemory;

    @BeforeEach
    void setup() {
    	referentManager = new StackMemoryReferentManager(); 
        stackMemory = new StackMemory(10, referentManager);
        stackMemory.startProcessing();
        instanceAbstractionModel = getIdInstance(1L);

    }
    
    
    public  InstanceAbstractionModel getIdInstance( Long id) {
        return InstanceAbstractionModel.builder()
                .idInstance(id)
                .instanOf("Ejemplo")
                .name("Nombre de la instancia")
                .title("Título de la instancia")
                .type(TaskTypeModel.builder().build())
                .idProcess(1L)
                .idRefenet(2L)
                .idParent(3L)
                .idInstanceOfProcess(4L)
                .codeProcess("Código del proceso")
                .codeReferent("Código del referente")
                .apprubeType(1)
                .isParallel(true)
                .status("activo")
                .active(true)
                .dateCreate(new Date())
                .userCreateInstance("Usuario que creó la instancia")
                .userWorked("Usuario que trabajó en la instancia")
                .response("Respuesta")
                .level(1)
                .build();
    }
    

    @Test
    void testAddElement() {
        boolean result = stackMemory.addElement(instanceAbstractionModel, "p");
        assertTrue(result);
    }
    


    @Test
    void testProcessQueue() {
        // Given    
        stackMemory.addElement(instanceAbstractionModel, "m");
      
        // When
        stackMemory.processQueue();
  
    }

//    @Test
//    void testManagerError() {
//        // Given
//        when(instanceAbstractionModel.getIdInstance()).thenReturn(1L);
//        when(referentManager.getInstanceInWorkingReferentBook(instanceAbstractionModel.getIdInstance())).thenReturn(instanceAbstractionModel);
//
//        // When
//        stackMemory.managerError(instanceAbstractionModel);
//
//        // Then
//        verify(referentManager, times(1)).removeInstanceOfWorkingReferentBook(instanceAbstractionModel.getIdInstance());
//    }
}