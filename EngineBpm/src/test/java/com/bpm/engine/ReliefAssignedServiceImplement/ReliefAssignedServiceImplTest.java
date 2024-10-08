package com.bpm.engine.ReliefAssignedServiceImplement;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.bpm.engine.entitys.ReliefAssigned;
import com.bpm.engine.model.ReliefAssignedModel;
import com.bpm.engine.repository.ReliefAssignedRepository;
import com.bpm.engine.serviceImplement.ReliefAssignedServiceImpl;

class ReliefAssignedServiceImplTest {

    @InjectMocks
    private ReliefAssignedServiceImpl reliefAssignedService;

    @Mock
    private ReliefAssignedRepository reliefAssignedRepository;

    @Mock
    private ModelMapper modelMapper;

    private ReliefAssigned reliefAssigned;
    private ReliefAssignedModel reliefAssignedModel;

    @BeforeEach
    void setUp() {
    	 MockitoAnnotations.openMocks(this); 
        reliefAssigned = ReliefAssigned.builder().idRelief(1L).userCode("USER1").build();
        reliefAssignedModel = ReliefAssignedModel.builder().idRelief(1L).userCode("USER1").build();
    }

    @Test
    void testCreateReliefAssigned() {
        when(modelMapper.map(reliefAssignedModel, ReliefAssigned.class)).thenReturn(reliefAssigned);
        when(reliefAssignedRepository.save(reliefAssigned)).thenReturn(reliefAssigned);
        when(modelMapper.map(reliefAssigned, ReliefAssignedModel.class)).thenReturn(reliefAssignedModel);

        ReliefAssignedModel result = reliefAssignedService.createReliefAssigned(reliefAssignedModel);
        assertNotNull(result);
        assertEquals(reliefAssignedModel.getIdRelief(), result.getIdRelief());
    }

    @Test
    void testUpdateReliefAssigned() {
        when(reliefAssignedRepository.findById(1L)).thenReturn(Optional.of(reliefAssigned));
        when(reliefAssignedRepository.save(reliefAssigned)).thenReturn(reliefAssigned);
        when(modelMapper.map(reliefAssigned, ReliefAssignedModel.class)).thenReturn(reliefAssignedModel);

        ReliefAssignedModel result = reliefAssignedService.updateReliefAssigned(reliefAssignedModel);
        assertNotNull(result);
        assertEquals(reliefAssignedModel.getIdRelief(), result.getIdRelief());
    }
    
    

    @Test
    void testUpdateTimeActive() {
        doNothing().when(reliefAssignedRepository).updateTimeActive("10", 1L);

        reliefAssignedService.updateTimeActive(10, 1L);
        verify(reliefAssignedRepository, times(1)).updateTimeActive("10", 1L);
    }

    @Test
    void testUpdateActive() {
        doNothing().when(reliefAssignedRepository).updateActive("true", 1L);

        reliefAssignedService.updateActive(true, 1L);
        verify(reliefAssignedRepository, times(1)).updateActive("true", 1L);
    }
}

