package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.entitys.Role;
import com.bpm.engine.repository.ApprovedProcessRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApprovedProcessRepositoryTest {

    @Autowired
    private ApprovedProcessRepository approvedProcessRepository;

    @Test
    public  void saveApprovedProcessAndReturnApprovedProcess(){
        //Arrange
        ApprovedProcess approvedProcess = ApprovedProcess.builder()
                .processCode("processCode")
                .idProcess(1L)
                .granted(true).build();
        //Act
        ApprovedProcess saveRole = approvedProcessRepository.save(approvedProcess);

        //Assert
        Assertions.assertThat(saveRole).isNotNull();
        Assertions.assertThat(saveRole.getIdApprovedProcess()).isGreaterThan(0);
    }

}
