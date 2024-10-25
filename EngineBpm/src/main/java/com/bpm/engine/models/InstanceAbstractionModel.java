package com.bpm.engine.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

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
	 
	 private TaskTypeModel type;
	 
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
	 
	 private Integer abstractFieldNumber0;
	 
	 private Integer abstractFieldNumber1;

	 private Integer abstractFieldNumber2;
	 
	 private Integer abstractFieldNumber3;
	 
	 private Integer abstractFieldNumber4;
	 
	 private Integer abstractFieldNumber5;
	 
	 @Builder.Default
	 private List<InstanceAbstractionModel> instances = new ArrayList<>();
	 
	 
	 public void addInstanceAbstractionModel(InstanceAbstractionModel instance) {
		 this.instances.add(instance);
	 }
	 
	 
	 public void addAllInstanceAbstractionModel(List<InstanceAbstractionModel> instances) {
		 this.instances.addAll(instances);
	 }
}
