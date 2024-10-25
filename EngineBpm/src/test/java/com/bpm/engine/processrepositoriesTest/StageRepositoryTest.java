package com.bpm.engine.processrepositoriesTest;

import com.bpm.engine.entitys.*;
import com.bpm.engine.repository.StageRepository;
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
public class StageRepositoryTest {

    @Autowired
    private StageRepository stageRepository;

    private Date dateCreate;
    private List<Role> roles = new ArrayList<>();

    private Stage getAStage(){
        dateCreate = new Date();
        List<Role> roles = Arrays.asList(
                Role.builder().name("Developer").codeRole("w23a").description("developer").build(),
                Role.builder().name("DeveloperII").codeRole("w25a").description("Developer Senor").build()
        );
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

    @Test
    public void saveStageAndReturnStage(){

        Stage testObject = this.getAStage();
        //Act
        Stage saveTestObject = stageRepository.save(testObject);
        //Assert
        Assertions.assertThat(saveTestObject).isNotNull();
        Assertions.assertThat(saveTestObject.getIdStage()).isGreaterThan(0);
    }



}
