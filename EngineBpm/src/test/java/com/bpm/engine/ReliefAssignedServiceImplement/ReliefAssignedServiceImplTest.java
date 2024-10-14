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

import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.repository.ReliefAssignedRepository;
import com.bpm.engine.relief.service.ReliefAssignedServiceImpl;

class ReliefAssignedServiceImplTest {

    @InjectMocks
    private ReliefAssignedServiceImpl reliefAssignedService;

    @Mock
    private ReliefAssignedRepository reliefAssignedRepository;

    @Mock
    private ReliefAssignedMapper mapper;

    private ReliefAssigned reliefAssigned;
    private ReliefAssignedModel reliefAssignedModel;

    @BeforeEach
    void setUp() {
    	 MockitoAnnotations.openMocks(this); 
        reliefAssigned = getReliefAssigned() ;
        reliefAssignedModel = getReliefAssignedModel();
    }
   
    
    public ReliefAssigned getReliefAssigned() {
    	
    	return ReliefAssigned.builder()
    			.idRelief(1L)
    			.userCode("4456")
    			.userReliefCode("2349")
    			.userCreateCode("3975")
    			.temporary(true)
    			.active(true)
    			.returnCommand(true)
    			.time(30)
    			.timeActive(30)
    			.build();
    }
    
    
    public ReliefAssignedModel getReliefAssignedModel() {
    	
    	return ReliefAssignedModel.builder()
    			.idRelief(1L)
    			.userCode("4456")
    			.userReliefCode("2349")
    			.userCreateCode("3975")
    			.temporary(true)
    			.active(true)
    			.returnCommand(true)
    			.time(30)
    			.timeActive(30)
    			.build();
    }
    
    
    private void whenMock() {
    	when(mapper.toEntity(reliefAssignedModel)).thenReturn(reliefAssigned);
    	when(mapper.toModel(reliefAssigned)).thenReturn(reliefAssignedModel);
    }
    
    @Test
    void testCreateReliefAssigned() {
    
    	this.whenMock();
        when(reliefAssignedRepository.save(reliefAssigned)).thenReturn(reliefAssigned);
        ReliefAssignedModel result = reliefAssignedService.createReliefAssigned(reliefAssignedModel);
        assertNotNull(result);
        assertEquals(reliefAssignedModel.getIdRelief(), result.getIdRelief());
        
    }

    
    @Test
    void testUpdateReliefAssigned() {
    	this.whenMock();
        when(reliefAssignedRepository.findById(1L)).thenReturn(Optional.of(reliefAssigned));
        when(reliefAssignedRepository.save(reliefAssigned)).thenReturn(reliefAssigned);
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

