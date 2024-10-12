package com.bpm.engine.models;

import java.util.Objects;

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
	
	private Boolean unsuscribe;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReliefAssignedModel other = (ReliefAssignedModel) obj;
		return Objects.equals(active, other.active) && Objects.equals(idRelief, other.idRelief)
				&& Objects.equals(permanent, other.permanent) && Objects.equals(returnCommand, other.returnCommand)
				&& Objects.equals(temporary, other.temporary) && Objects.equals(time, other.time)
				&& Objects.equals(timeActive, other.timeActive) && Objects.equals(userCode, other.userCode)
				&& Objects.equals(userCreateCode, other.userCreateCode)
				&& Objects.equals(userReliefCode, other.userReliefCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, idRelief, permanent, returnCommand, temporary, time, timeActive, userCode,
				userCreateCode, userReliefCode);
	}

	
}
