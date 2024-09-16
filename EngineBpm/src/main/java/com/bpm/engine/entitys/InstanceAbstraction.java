package com.bpm.engine.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "bpm_ruler")
public class InstanceAbstraction {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idInstance;
	 
	 private TaskType type;
	 private String title;
	 private String name;
	 private Long idProcess;
	 private Long idRefenet;
	 

}
