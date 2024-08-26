package com.bpm.engine.instanceProcessrepositoryTest;

import com.bpm.engine.entitys.*;
import com.bpm.engine.repository.InstanceStageRepository;
import com.bpm.engine.repository.InstanceTaskRepository;
import com.bpm.engine.repository.TaskRepository;
import com.bpm.engine.utility.SystemSate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InstanceStageRepositoryTest {

    private InstanceStageRepository instanceStageRepository;

    private InstanceTaskRepository instanceTaskRepository;
    private TaskRepository taskRepository;

    private Date dateCreate;

    @Autowired
    public InstanceStageRepositoryTest(InstanceStageRepository instanceStageRepository, InstanceTaskRepository instanceTaskRepository, TaskRepository taskRepository) {
        this.instanceStageRepository = instanceStageRepository;
        this.instanceTaskRepository = instanceTaskRepository;
        this.taskRepository = taskRepository;
    }

    private Task getTask(){
        dateCreate = new Date();
        return  Task.builder().name("Task_test_1").title("Task Test 1").urlService( ".....xxxx....")
                .taskUrl("htttp....iiiiuuu...")
                .rulers(List.of(
                        Ruler.builder().condition("APRUBE").action("go to end").build(),
                        Ruler.builder().condition("CANCEL").action("go to STAR").build()
                ))
                .roles(List.of(
                        Role.builder().name("Developer").codeRole("w23a").description("developer").build(),
                        Role.builder().name("DeveloperII").codeRole("w25a").description("Developer Senor").build()
                ))
                .type(TaskType.builder().type("Human").build())
                .dateCreate(dateCreate)
                .procesCode("procesCode")
                .code("codeTask")
                .build();
    }
    private InstanceTask getInstanceTask(){
        Task task = taskRepository.save(this.getTask());
        return InstanceTask.builder()
                .name("Task_test_1")
                .processCode("procesCode")
                .instanceProcessId(11l)
                .instanceProcessCode("instanceProcessCode")
                .codeTask("codeTask")
                .task(task)
                .dateStart(this.dateCreate)
                .assignes(
                        List.of(TaskAssigned.builder().taskId(task.getIdTask()).idBpmAssigned(2121L).build())
                )
                .state(SystemSate.CREATE.toString())
                .idControlProcessReferent(1l)
                .build();

    }
    private InstanceStage getInstanceStage(){
       return InstanceStage.builder()
                .name("InstanceStageTest")
                .code("InstanceStageCodeTest")
                .dateStart(this.dateCreate)
                .title("InstanceStage_Test")
                .procesCode("procesCode")
                .instanceProcessId(112l)
                .instancesTasks(List.of(this.getInstanceTask()))
                .stageNumber(1)
                .state(SystemSate.CREATE.toString())
                .build();
    }

    @Test
    public void saveInstanceStageAndReturnAInstanceStage(){
        InstanceStage objectTeest = instanceStageRepository.save(this.getInstanceStage());
        Assertions.assertThat(objectTeest).isNotNull();
        Assertions.assertThat(objectTeest.getId()).isGreaterThan(24);

    }
}
