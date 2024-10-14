package com.bpm.engine.relief;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReliefDTO implements Serializable{

	private static final long serialVersionUID = 133334445567l;
	
	
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
	
	private Boolean unsuscribe;
	
	private Integer type;

	private Boolean delegateAll;
	
	private List<Long> idInstances;
	
	
}
