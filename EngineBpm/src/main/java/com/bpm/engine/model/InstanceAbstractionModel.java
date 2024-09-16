package com.bpm.engine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.entitys.TaskType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstanceAbstractionModel {

	 private Long idInstance;
	 
	 private String instanOf;
	 
	 private String name;
	 
	 private String title;
	 
	 private TaskType type;
	 
	 private Long idProcess;
	 
	 private Long idRefenet;
	 
	 private Long idParent;
	 
	 private Long idInstanceOfProcess;
	 
	 private String codeProcess;
	 
	 private String codeReferent;
	 
	 private Integer apprubeType;
	 
	 private Boolean isParallel;
	 
	 private String status;
	 
	 private Boolean active;
	 
	 private Date dateCreate;
	 
	 private Date dateClose;
	 
	 private String userCreateInstance;
	 
	 private String userWorked;
	 
	 private String response;
	 
	 private Integer level;
	 
	 private String abstractField0;
	 
	 private String abstractField1;
	 
	 private String abstractField2;
	 
	 private String abstractField3;
	 
	 private String abstractField4;
	 
	 private String abstractField5;
	 
	 private String abstractField6;
	 
	 private String abstractField7;
	 
	 private String abstractField8;
	 
	 private String abstractField9;
	 
	 @Builder.Default
	 private List<InstanceAbstraction> instances = new ArrayList<>();
}
