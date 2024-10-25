package com.bpm.engine.instanceProcessrepositoryTest;

import com.bpm.engine.entitys.TaskAssigned;
import com.bpm.engine.repository.TaskAssignedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskAssignedRepositoryTest {

    @Autowired
    private TaskAssignedRepository taskAssignedRepository;

    private Long taskId = 1223L;
    private Long idBpmAssigned = 2121L;

    private TaskAssigned getOneTaskAssigned(){
        return TaskAssigned.builder()
                .taskId(taskId)
                .idBpmAssigned(idBpmAssigned)
                .build();
    }
    @Test
    public void saveTaskAssignedAndReturnTaskAssigned(){
        TaskAssigned objectTest = this.getOneTaskAssigned();
        TaskAssigned saveObjectTest = taskAssignedRepository.save(objectTest);
        Assertions.assertThat(saveObjectTest).isNotNull();
        Assertions.assertThat(saveObjectTest.getIdTaskAssigned()).isGreaterThan(0L);
    }

    @Test
    public void findByTaskIdAnReturnAListOfTaskAssigned(){
        TaskAssigned objectTest = this.getOneTaskAssigned();
        taskAssignedRepository.save(objectTest);
        List<TaskAssigned> listObject = taskAssignedRepository.findByTaskId(this.taskId);
        Assertions.assertThat(listObject).isNotNull();
        Assertions.assertThat(listObject.size()).isGreaterThan(0);
    }


}
