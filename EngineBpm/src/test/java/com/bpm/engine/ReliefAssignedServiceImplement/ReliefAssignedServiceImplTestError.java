package com.bpm.engine.ReliefAssignedServiceImplement;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.mapper.ReliefAssignedMapper;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.repository.ReliefAssignedRepository;
import com.bpm.engine.relief.service.ReliefAssignedServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReliefAssignedServiceImplTestError {
	
	@InjectMocks
	private ReliefAssignedServiceImpl reliefAssignedService;
	
    @Mock
    private ReliefAssignedRepository reliefAssignedRepository;

    @Mock
    private ReliefAssignedMapper mapper;



    @BeforeEach
    void setUp() {
    	 MockitoAnnotations.openMocks(this); 
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
    
    

    @Test
    void testCreateReliefAssigned_DatabaseError() {
        ReliefAssignedModel model = new ReliefAssignedModel();
        ReliefAssigned entity = new ReliefAssigned();

        when(mapper.toEntity(model)).thenReturn(entity);
       
        when(reliefAssignedRepository.save(any(ReliefAssigned.class))).thenThrow(new DataAccessException("DB error") {});

        RuntimeException exception = assertThrows(RuntimeException.class,() -> reliefAssignedService.createReliefAssigned(model),"Error creating ReliefAssigned");

        assertEquals("Error creating ReliefAssigned", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).save(any(ReliefAssigned.class));
    }



    @Test
    void testUpdateTimeActive_DatabaseError() {
        Integer timeActive = 10;
        Long idRelief = 1L;
  
        doThrow(new DataAccessException("DB error"){}).when(reliefAssignedRepository).updateTimeActive(anyString(), eq(idRelief));

        RuntimeException exception = assertThrows(RuntimeException.class,() -> reliefAssignedService.updateTimeActive(timeActive, idRelief), "Error updating timeActive");

        assertEquals("Error updating timeActive", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).updateTimeActive(anyString(), eq(idRelief));
    }

    @Test
    void testUpdateActive_DatabaseError() {
        Boolean active = true;
        Long idRelief = 1L;

        doThrow(new DataAccessException("DB error") {}).when(reliefAssignedRepository).updateActive(anyString(), eq(idRelief));

        RuntimeException exception = assertThrows(RuntimeException.class,() -> reliefAssignedService.updateActive(active, idRelief), "Error updating active status");

        assertEquals("Error updating active status", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).updateActive(anyString(), eq(idRelief));
    }

    @Test
    void testFindByActive_DatabaseError() {
        Boolean active = true;

        when(reliefAssignedRepository.findByActive(active)).thenThrow(new DataAccessException("DB error") {});
    
        RuntimeException exception = assertThrows(RuntimeException.class,() -> reliefAssignedService.findByActive(active), "Error finding ReliefAssigned by active");

        assertEquals("Error finding ReliefAssigned by active", exception.getMessage());
        verify(reliefAssignedRepository, times(1)).findByActive(active);
    }
}
