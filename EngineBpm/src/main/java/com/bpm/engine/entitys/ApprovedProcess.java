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
@Table(name = "bpm_approved_process")
public class ApprovedProcess {

    @Id
    @GeneratedValue(generator = "sequence_approved_id_1_gene")
    @SequenceGenerator(name = "sequence_approved_id_1_gene", initialValue = 25, allocationSize = 1000)
    @Column(name = "idApprovedProcess", updatable = true, nullable = false, length = 25)
    private Long idApprovedProcess;

    @Column(name = "processCode", updatable = true, nullable = true, length = 200)
    private String processCode;

    @Column(name = "id_process", updatable = true, nullable = true, length = 200)
    private Long idProcess;

    @Column(name = "granted", updatable = true, nullable = true)
    private Boolean granted;

}
