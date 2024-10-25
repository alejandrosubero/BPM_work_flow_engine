package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.repository.ApprovedProcessRepository;
import com.bpm.engine.repository.AssignedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AssignedRepositoryTest {
    private AssignedRepository assignedRepository;
    private ApprovedProcessRepository approvedProcessRepository;

    @Autowired
    public AssignedRepositoryTest(AssignedRepository assignedRepository, ApprovedProcessRepository approvedProcessRepository) {
        this.assignedRepository = assignedRepository;
        this.approvedProcessRepository = approvedProcessRepository;
    }

    @Test
    public  void saveApprovedProcessAndReturnApprovedProcess(){
        //Arrange
        Assigned as =  Assigned.builder()
                .name("as")
                .codeEmployee("hx39075")
                .mail("ase@gmail.com")
                .employeeRole(
                        Role.builder().name("Developer").codeRole("w23a").description("developer").build())
                .approvedProcess(
                		Arrays.asList(
                                 ApprovedProcess.builder().processCode("codeProcess").idProcess(1L).granted(true).build()
                        ))
                .active(true)
                .build();

        //Act
        Assigned saveAssigned = assignedRepository.save(as);
        //Assert
        Assertions.assertThat(saveAssigned).isNotNull();
        Assertions.assertThat(saveAssigned.getId()).isGreaterThan(0);
    }
}
