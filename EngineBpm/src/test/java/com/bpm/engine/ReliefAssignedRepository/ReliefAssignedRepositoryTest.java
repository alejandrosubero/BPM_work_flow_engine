package com.bpm.engine.ReliefAssignedRepository;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.repository.ReliefAssignedRepository;

@SpringBootTest
class ReliefAssignedRepositoryTest {

    @Mock
    private ReliefAssignedRepository reliefAssignedRepository;

    // Inicialización de mocks usando openMocks en lugar de initMocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindByActive() {
        ReliefAssigned reliefAssigned = ReliefAssigned.builder().idRelief(1L).active(true).build();
        when(reliefAssignedRepository.findByActive(true)).thenReturn(Arrays.asList(reliefAssigned));

        List<ReliefAssigned> result = reliefAssignedRepository.findByActive(true);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByUserCodeAndActive() {
        ReliefAssigned reliefAssigned = ReliefAssigned.builder().idRelief(1L).userCode("USER1").active(true).build();
        when(reliefAssignedRepository.findByUserCodeAndActive("USER1", true)).thenReturn(Arrays.asList(reliefAssigned));

        List<ReliefAssigned> result = reliefAssignedRepository.findByUserCodeAndActive("USER1", true);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateTimeActive() {
        doNothing().when(reliefAssignedRepository).updateTimeActive("10", 1L);

        reliefAssignedRepository.updateTimeActive("10", 1L);
        verify(reliefAssignedRepository, times(1)).updateTimeActive("10", 1L);
    }

    @Test
    void testUpdateActive() {
        doNothing().when(reliefAssignedRepository).updateActive("true", 1L);

        reliefAssignedRepository.updateActive("true", 1L);
        verify(reliefAssignedRepository, times(1)).updateActive("true", 1L);
    }

    @Test
    void testFindByUserReliefCodeLike() {
        ReliefAssigned reliefAssigned = ReliefAssigned.builder().idRelief(1L).userReliefCode("RELIEF1").build();
        when(reliefAssignedRepository.findByUserReliefCodeLike("RELIEF1")).thenReturn(Arrays.asList(reliefAssigned));

        List<ReliefAssigned> result = reliefAssignedRepository.findByUserReliefCodeLike("RELIEF1");
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}

