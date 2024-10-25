package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.*;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.repository.ProcessRepository;
import com.bpm.engine.utility.SystemSate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProcessRespositoriyTest {

    private ProcessRepository processRepository;

    private Date dateCreate;
    private List<Role> roles = new ArrayList<>();

    @Autowired
    public ProcessRespositoriyTest(ProcessRepository processRepository) {
        this.processRepository = processRepository;
        dateCreate = new Date();
       this.roles = Arrays.asList(
                Role.builder().name("Developer").codeRole("w23a").description("developer").build(),
                Role.builder().name("DeveloperII").codeRole("w25a").description("Developer Senor").build()
        );
    }

    private Stage getAStage(){
        return Stage.builder()
                .stageCode("stageCode")
                .name("go_to_party")
                .title("Go to party")
                .type("human")
                .dateCreate(dateCreate)
                .tasks(Arrays.asList(this.getTask()))
                .stages(new ArrayList<Stage>())
                .roles(roles)
                .stageNumber(1)
                .build();
    }

    private Task getTask(){
        return  Task.builder().name("Task_test_1").title("Task Test 1").urlService( ".....xxxx....")
                .taskUrl("htttp....iiiiuuu...")
                .rulers(Arrays.asList(
                        Ruler.builder().condition("APRUBE").action(1).build(),
                        Ruler.builder().condition("CANCEL").action(2).build()
                ))
                .roles(roles)
                .type(TaskType.builder().type("Human").build())
                .dateCreate(dateCreate)
                .procesCode("procesCode")
                .code("code")
                .build();
    }

    private Process getProcess(){
        List<Stage> stages = Arrays.asList(this.getAStage());
        return Process.builder()
                .name("we out")
                .procesTitle("we out system")
                .procesCode("codeProcess")
                .userCreate("hx39075")
                .global(true)
                .visible(true)
                .createDate(dateCreate)
                .state(SystemSate.CREATE.toString())
                .activo(true)
                .stages(stages)
                .roles(roles)
                .build();
    }

    @Test
    public void saveProcessAndReturnProcess(){
        Process testObject = this.getProcess();
        //Act
        Process saveTestObject = processRepository.save(testObject);
        //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getId_process()).isGreaterThan(0);
    }
}
