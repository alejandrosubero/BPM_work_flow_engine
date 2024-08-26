package com.bpm.engine.instanceProcessrepositoryTest;

import com.bpm.engine.entitys.*;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.repository.*;
import com.bpm.engine.utility.SystemSate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InstanceProcessRepositoryTest {

    private InstanceProcessRepository instanceprocessRepository;
    private InstanceStageRepository instanceStageRepository;
    private InstanceTaskRepository instanceTaskRepository;
    private TaskRepository taskRepository;
    private ProcessRepository processRepository;
    private Date dateCreate;
    private List<Role> roles = new ArrayList<>();

    @Autowired
    public InstanceProcessRepositoryTest(ProcessRepository processRepository, InstanceProcessRepository instanceprocessRepository, InstanceStageRepository instanceStageRepository, InstanceTaskRepository instanceTaskRepository, TaskRepository taskRepository) {
        this.instanceprocessRepository = instanceprocessRepository;
        this.processRepository = processRepository;
        this.instanceStageRepository = instanceStageRepository;
        this.instanceTaskRepository = instanceTaskRepository;
        this.taskRepository = taskRepository;
        roles = List.of(
                Role.builder().name("Developer").codeRole("w23a").description("developer").build(),
                Role.builder().name("DeveloperII").codeRole("w25a").description("Developer Senor").build()
        );

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
    private Stage getAStage(){
        return Stage.builder()
                .stageCode("stageCode")
                .name("go_to_party")
                .title("Go to party")
                .type("human")
                .dateCreate(dateCreate)
                .tasks(List.of(this.getTask()))
                .stages(new ArrayList<Stage>())
                .roles(roles)
                .stageNumber(1)
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
    private Process getProcess(){
        List<Stage> stages = List.of(this.getAStage());
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
    private Assigned getAssigned(String processCode, Long processId){
       return   Assigned.builder()
                .name("as")
                .codeEmployee("hx39075")
                .mail("ase@gmail.com")
                .employeeRole(
                        Role.builder().name("Developer").codeRole("w23a").description("developer").build())
                .approvedProcess(
                        List.of(
                                ApprovedProcess.builder().processCode(processCode).idProcess(processId).granted(true).build()
                        ))
                .active(true)
                .build();
    }

    private InstanceProcess getInstanceProcess(){
        Process process = this.processRepository.save(this.getProcess());
        return InstanceProcess.builder()
                .name("InstanceProcessTestRepository")
                .createBy("Alejandro Subero")
                .dateCreate(this.dateCreate)
                .state(SystemSate.CREATE.toString())
                .instanceCode("InstanceProcessCode")
                .title("Instance_Process_test_repository")
                .process(process)
                .instanceStage(List.of(this.getInstanceStage()))
                .assigned(List.of(
                        this.getAssigned(process.getProcesCode(), process.getId_process())
                ))
                .idControlProcessReferent(1L)
                .build();
    }

    @Test
    public void saveInstanceProcessAndReturnAInstanceProcess(){
        InstanceProcess objectTest =  instanceprocessRepository.save(this.getInstanceProcess());
        Assertions.assertThat(objectTest).isNotNull();
        Assertions.assertThat(objectTest.getIdInstanceProcess()).isGreaterThan(24);
    }

}
