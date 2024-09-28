package com.bpm.engine.entitys;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bpm_bpm_Assigned")
public class BpmAssigned {

    @Id
    @GeneratedValue(generator = "sequence_bpm_assigned")
    @SequenceGenerator(name = "sequence_bpm_assigned", initialValue = 1, allocationSize = 2000)
    @Column(name = "id_Bpm_Assigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "id_Assigned", updatable = true, nullable = true, length = 200)
    private Long idAssigned;

    @Column(name = "task_Code", updatable = true, nullable = true, length = 200)
    private String taskCode;

    @Column(name = "instancia_Procces_Id", updatable = true, nullable = true, length = 100)
    private Long instanciaProccesId;

    @Column(name = "active", updatable = true, nullable = true, length = 100)
    private Boolean active;
    
    @Column(name = "code_Employee", updatable = true, nullable = true, length = 200)
    private String codeEmployee;
    
    @Column(name = "procces_Id", updatable = true, nullable = true, length = 200)
    private Long proccesId;
    
}
