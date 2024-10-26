package com.bpm.engine.relief.entity;

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
@Table(name = "bpm_relief_assigned")
public class ReliefAssigned {

	
	 	@Id
	    @GeneratedValue(generator = "sequence_relief_id")
	    @SequenceGenerator(name = "sequence_relief_id", initialValue = 25, allocationSize = 1000)
	    @Column(name = "id_relief", updatable = true, nullable = false, length = 25)
	    private Long idRelief;
	 	
	 	@Column(name = "user_code", updatable = true, nullable = true, length = 200)
	 	private String userCode;
	 	
	 	@Column(name = "user_relief_code", updatable = true, nullable = true, length = 200)
	 	private  String userReliefCode;
	 	
	 	@Column(name = "user_create_code", updatable = true, nullable = true, length = 200)
	 	private String userCreateCode;
	 	
	 	@Column(name = "permanent", updatable = true, nullable = true, length = 200)
	 	private Boolean permanent; 
	 	
	 	@Column(name = "temporary", updatable = true, nullable = true, length = 200)
	 	private Boolean temporary;
	 	
	 	@Column(name = "return_command", updatable = true, nullable = true, length = 200)
	 	private Boolean returnCommand;
	 	
	 	@Column(name = "active", updatable = true, nullable = true, length = 200)
	 	private Boolean active;
	 	
	 	@Column(name = "time", updatable = true, nullable = true, length = 200)
	 	private Integer time;
	 	
	 	@Column(name = "time_active", updatable = true, nullable = true, length = 200)
	 	private Integer timeActive;
	 	
		@Column(name = "unsuscribe", updatable = true, nullable = true, length = 10)
		private Boolean unsuscribe;
		
		@Column(name = "type", updatable = true, nullable = true, length = 10)
		private Integer type;
		
		@Column(name = "delegateAll", updatable = true, nullable = true, length = 10)
		private Boolean delegateAll;
	 	
}
