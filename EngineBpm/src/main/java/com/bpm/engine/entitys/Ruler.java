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
@Table(name = "bpm_ruler")
public class Ruler {

    @Id
    @GeneratedValue(generator = "sequence_id_task_ruler_generator")
    @SequenceGenerator(name = "sequence_id_task_ruler_generator", initialValue = 100, allocationSize = 2000)
    @Column(name = "idRuler", updatable = true, nullable = false, length = 25)
    private Long idRuler;
    private String condition;
    private Integer action;
    private String taskCode;
    private Integer taskNumber;

}
