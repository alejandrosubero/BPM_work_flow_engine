package com.bpm.engine.entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "bpm_instance_Abstraction")
public class InstanceAbstraction {
	
	 @Id
	 @GeneratedValue(generator = "sequence_matInstance_Abstraction_generator")
	 @SequenceGenerator(name = "sequence_matInstance_Abstraction_generator", initialValue = 3, allocationSize = 10000)
	 @Column(name = "id_instance", updatable = true, nullable = true, length = 300)
	 private Long idInstance;
	 
	 @Column(name = "instan_Of", updatable = true, nullable = true, length = 200)
	 private String instanOf;
	 
	 @Column(name = "name", updatable = true, nullable = true, length = 200)
	 private String name;
	 
	 @Column(name = "title", updatable = true, nullable = true, length = 200)
	 private String title;
	 
	 @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_type")
	 private TaskType type;
	 
	 @Column(name = "id_process", updatable = true, nullable = true, length = 200)
	 private Long idProcess;
	 
	 @Column(name = "id_referent", updatable = true, nullable = true, length = 200)
	 private Long idRefenet;
	 
	 @Column(name = "id_Parent", updatable = true, nullable = true, length = 200)
	 private Long idParent;
	 
	 @Column(name = "id_Instance_Of_Process", updatable = true, nullable = true, length = 200)
	 private Long idInstanceOfProcess;
	 
	 @Column(name = "code_Process", updatable = true, nullable = true, length = 200)
	 private String codeProcess;
	 
	 @Column(name = "code_Referent", updatable = true, nullable = true, length = 200)
	 private String codeReferent;
	 
	 @Column(name = "apprube_Type", updatable = true, nullable = true)
	 private Integer apprubeType;
	 
	 @Column(name = "is_parallel", updatable = true, nullable = true, length = 200)
	 private Boolean isParallel;
	 
	 @Column(name = "status", updatable = true, nullable = true, length = 200)
	 private String status;
	 
	 @Column(name = "active", updatable = true, nullable = true, length = 200)
	 private Boolean active;
	 
	 @Column(name = "date_create", updatable = true, nullable = true, length = 200)
	 private Date dateCreate;
	 
	 @Column(name = "date_close", updatable = true, nullable = true, length = 200)
	 private Date dateClose;
	 
	 @Column(name = "user_create_instance", updatable = true, nullable = true, length = 200)
	 private String userCreateInstance;
	 
	 @Column(name = "user_Worked", updatable = true, nullable = true, length = 200)
	 private String userWorked;
	 
	 @Column(name = "response", updatable = true, nullable = true, length = 200)
	 private String response;
	 
	 @Column(name = "level", updatable = true, nullable = true)
	 private Integer level;
	 
	 @Column(name = "abstract_Field_0", updatable = true, nullable = true, length = 300)
	 private String abstractField0;
	 
	 @Column(name = "abstract_Field_1", updatable = true, nullable = true, length = 300)
	 private String abstractField1;
	 
	 @Column(name = "abstract_Field_2", updatable = true, nullable = true, length = 300)
	 private String abstractField2;
	 
	 @Column(name = "abstract_Field_3", updatable = true, nullable = true, length = 300)
	 private String abstractField3;
	 
	 @Column(name = "abstract_Field_4", updatable = true, nullable = true, length = 300)
	 private String abstractField4;
	 
	 @Column(name = "abstract_Field_5", updatable = true, nullable = true, length = 300)
	 private String abstractField5;
	 
	 @Column(name = "abstract_Field_6", updatable = true, nullable = true, length = 300)
	 private String abstractField6;
	 
	 @Column(name = "abstract_Field_7", updatable = true, nullable = true, length = 300)
	 private String abstractField7;
	 
	 @Column(name = "abstract_Field_Number_0", updatable = true, nullable = true)
	 private Integer abstractFieldNumber0;
	 
	 @Column(name = "abstract_Field_Number_1", updatable = true, nullable = true)
	 private Integer abstractFieldNumber1;
	 
	 @Column(name = "abstract_Field_Number_2", updatable = true, nullable = true)
	 private Integer abstractFieldNumber2;
	 
	 @Column(name = "abstract_Field_Number_3", updatable = true, nullable = true)
	 private Integer abstractFieldNumber3;
	 
	 @Column(name = "abstract_Field_Number_4", updatable = true, nullable = true)
	 private Integer abstractFieldNumber4;
	 
	 @Column(name = "abstract_Field_Number_5", updatable = true, nullable = true)
	 private Integer abstractFieldNumber5;
	 
	 
	 
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	 @JoinColumn(name = "instances_id")
	 @Builder.Default
	 private List<InstanceAbstraction> instances = new ArrayList<>();
	 
	 
}





// the process don't have TaskType or type, apprubeType,idInstanceOfProcess, userWorked,response, and level. Only task and is possible the stages

// in abstract_Field_9 for intance of process the value is the process_id represent the roles can't crete the process.


