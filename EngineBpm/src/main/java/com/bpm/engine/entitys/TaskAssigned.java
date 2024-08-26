package com.bpm.engine.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "idTaskAssigned", updatable = true, nullable = false, length = 25)
    private Long idTaskAssigned;

    @Column(name = "idBpmAssigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "taskId", updatable = true, nullable = false, length = 200)
    private Long taskId;

}
