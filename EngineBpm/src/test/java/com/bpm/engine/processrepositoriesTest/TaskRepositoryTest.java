package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.entitys.Task;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private Date dateCreate;

    private Task getTask(){
        dateCreate = new Date();
        return  Task.builder().name("Task_test_1").title("Task Test 1").urlService( ".....xxxx....")
                .taskUrl("htttp....iiiiuuu...")
                .rulers(Arrays.asList(
                        Ruler.builder().condition("APRUBE").action(1).build(),
                        Ruler.builder().condition("CANCEL").action(2).build()
                ))
                .roles(Arrays.asList(
                        Role.builder().name("Developer").codeRole("w23a").description("developer").build(),
                        Role.builder().name("DeveloperII").codeRole("w25a").description("Developer Senor").build()
                ))
                .type(TaskType.builder().type("Human").build())
                .dateCreate(dateCreate)
                .procesCode("procesCode")
                .code("codeTask")
                .build();
    }
    @Test
    public  void saveTaskAndReturnTask(){

        Task taskObject = this.getTask();
        //Act
        Task saveTestObject = taskRepository.save(taskObject);
        //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getIdTask()).isGreaterThan(0);
    }

    @Test
    public  void findByDateCreateTaskAndRetunTask(){
        Task taskObject = this.getTask();
        taskRepository.save(taskObject);
        Task saveTestObject = taskRepository.findByDateCreate(dateCreate).get();
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getDateCreate()).isEqualTo(dateCreate);
    }
}
