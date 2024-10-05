package com.bpm.engine.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReliefAssignedModel {

	private Long idRelief;

	private String userCode;

	private String userReliefCode;

	private String userCreateCode;

	private Boolean permanent;

	private Boolean temporary;

	private Boolean returnCommand;

	private Boolean active;

	private Integer time;

	private Integer timeActive;

	
}
