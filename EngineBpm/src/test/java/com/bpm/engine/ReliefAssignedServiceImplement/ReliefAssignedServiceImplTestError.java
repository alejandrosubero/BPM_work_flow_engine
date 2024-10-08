package com.bpm.engine.ReliefAssignedServiceImplement;




import com.bpm.engine.entitys.ReliefAssigned;
import com.bpm.engine.model.ReliefAssignedModel;
import com.bpm.engine.repository.ReliefAssignedRepository;
import com.bpm.engine.serviceImplement.ReliefAssignedServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReliefAssignedServiceImplTestError {
    @Mock
    private ReliefAssignedRepository reliefAssignedRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReliefAssignedServiceImpl reliefAssignedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReliefAssigned_DatabaseError() {
        ReliefAssignedModel model = new ReliefAssignedModel();
        ReliefAssigned entity = new ReliefAssigned();

        // Simular que el mapper funciona correctamente
        when(modelMapper.map(any(ReliefAssignedModel.class), eq(ReliefAssigned.class))).thenReturn(entity);

        // Simular error al guardar en la base de datos
        when(reliefAssignedRepository.save(any(ReliefAssigned.class)))
                .thenThrow(new DataAccessException("DB error") {});

        // Probar la excepción esperada
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reliefAssignedService.createReliefAssigned(model),
                "Error creating ReliefAssigned");

        assertEquals("Error creating ReliefAssigned", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).save(any(ReliefAssigned.class));
    }

    @Test
    void testCreateReliefAssigned_ModelMapperError() {
        ReliefAssignedModel model = new ReliefAssignedModel();

        // Simular que el mapeo falla
        when(modelMapper.map(any(ReliefAssignedModel.class), eq(ReliefAssigned.class)))
                .thenThrow(new RuntimeException("Mapping error"));

        // Probar la excepción esperada
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reliefAssignedService.createReliefAssigned(model),
                "Error creating ReliefAssigned due to mapping issue");

        assertEquals("Error creating ReliefAssigned", exception.getMessage());
        verify(modelMapper, times(1)).map(any(ReliefAssignedModel.class), eq(ReliefAssigned.class));
    }

//    @Test
//    void testUpdateReliefAssigned_NullModel() {
//        // Probar que el método lanza una excepción al recibir un modelo nulo
//        assertThrows(NullPointerException.class, () -> reliefAssignedService.updateReliefAssigned(null),
//                "Model is null");
//    }
//
//    @Test
//    void testUpdateReliefAssigned_NotFound() {
//        ReliefAssignedModel model = new ReliefAssignedModel();
//        model.setIdRelief(1L);
//
//        // Simular que el repositorio no encuentra la entidad
//        when(reliefAssignedRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Probar que lanza la excepción correcta
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> reliefAssignedService.updateReliefAssigned(model));
//
//        assertEquals("ReliefAssigned not found with id: 1", exception.getMessage());
//        verify(reliefAssignedRepository, times(1)).findById(1L);
//    }

    @Test
    void testUpdateTimeActive_DatabaseError() {
        Integer timeActive = 10;
        Long idRelief = 1L;

        // Simular error al actualizar en la base de datos
        doThrow(new DataAccessException("DB error") {}).when(reliefAssignedRepository)
                .updateTimeActive(anyString(), eq(idRelief));

        // Probar la excepción esperada
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reliefAssignedService.updateTimeActive(timeActive, idRelief),
                "Error updating timeActive");

        assertEquals("Error updating timeActive", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).updateTimeActive(anyString(), eq(idRelief));
    }

    @Test
    void testUpdateActive_DatabaseError() {
        Boolean active = true;
        Long idRelief = 1L;

        // Simular error al actualizar en la base de datos
        doThrow(new DataAccessException("DB error") {}).when(reliefAssignedRepository)
                .updateActive(anyString(), eq(idRelief));

        // Probar la excepción esperada
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reliefAssignedService.updateActive(active, idRelief),
                "Error updating active status");

        assertEquals("Error updating active status", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).updateActive(anyString(), eq(idRelief));
    }

    @Test
    void testFindByActive_DatabaseError() {
        Boolean active = true;

        // Simular error al buscar en la base de datos
        when(reliefAssignedRepository.findByActive(active))
                .thenThrow(new DataAccessException("DB error") {});

        // Probar la excepción esperada
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reliefAssignedService.findByActive(active),
                "Error finding ReliefAssigned by active");

        assertEquals("Error finding ReliefAssigned by active", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).findByActive(active);
    }
}
