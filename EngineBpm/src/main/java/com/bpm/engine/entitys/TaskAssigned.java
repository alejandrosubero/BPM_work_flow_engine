package com.bpm.engine.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_task_assigned")
public class TaskAssigned {

    @Id
    @GeneratedValue(generator = "sequence_TaskAssigned_generator")
    @SequenceGenerator(name = "sequence_TaskAssigned_generator", initialValue = 1, allocationSize = 2000)
    @Column(name = "id_Task_Assigned", updatable = true, nullable = false, length = 25)
    private Long idTaskAssigned;

    @Column(name = "id_Bpm_Assigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "task_Id", updatable = true, nullable = false, length = 200)
    private Long taskId;

    @Column(name = "action_Executed", updatable = true, nullable = true, length = 500)
    private String actionExecuted;
    
    @Column(name = "date_Executed", updatable = true, nullable = true, length = 500)
    private Date dateExecuted;
    
    @Column(name = "state_Of_Task", updatable = true, nullable = true, length = 500)
    private String stateOfTask;
    
    
}
