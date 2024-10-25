package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.repository.TaskTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskTypeRepositoryTest {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Test
    public  void saveTaskTypeAndReturnTaskType(){
        //Arrange
        TaskType testObject = TaskType.builder()
                .type("Human").build();
        //Act
        TaskType saveTestObject = taskTypeRepository.save(testObject);

        //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getIdTaskType()).isGreaterThan(0);
    }


    @Test
    public  void findTaskTypeByTypeAndReturnTaskType(){
        //Arrange
        String type = "Human";
        TaskType testObject = TaskType.builder()
                .type(type).build();
        //Act
        taskTypeRepository.save(testObject);
        TaskType saveTestObject = taskTypeRepository.findByType(type).get();
                //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getType()).isEqualTo(type);
    }

}
