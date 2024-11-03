package com.bpm.engine.relief.model;

import java.util.Date;
import java.util.Objects;

import com.bpm.engine.relief.dto.ReliefDTO;

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

	private Integer type;

	private Boolean delegateAll;
	
	
	private Date createDate;
	private Date endDate;
	private Date dateChangeUserForRelief;
	private Date dateChangeReliefForUser;
	
	

	public ReliefAssignedModel(ReliefDTO dto) {

		if (dto.getId() != null)
			this.idRelief = dto.getId();

		if (dto.getUserCode() != null)
			this.userCode = dto.getUserCode();

		if (dto.getUserCode() != null)
			this.userReliefCode = dto.getUserReliefCode();

		if (dto.getUserCreateCode() != null)
			this.userCreateCode = dto.getUserCreateCode();

		if (dto.getPermanent() != null)
			this.permanent = dto.getPermanent();

		if (dto.getTemporary() != null)
			this.temporary = dto.getTemporary();

		if (dto.getReturnCommand() != null)
			this.returnCommand = dto.getReturnCommand();

		this.active = true;

		if (dto.getTime() != null)
			this.time = dto.getTime();

		if (dto.getUnsuscribe() != null)
			this.unsuscribe = dto.getUnsuscribe();

		if (dto.getType() != null)
			this.type = dto.getType();

		if (dto.getDelegateAll() != null) {
			this.delegateAll = dto.getDelegateAll();
		}
		
		this.createDate = new Date();
		

	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReliefAssignedModel other = (ReliefAssignedModel) obj;
		return Objects.equals(active, other.active) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(dateChangeReliefForUser, other.dateChangeReliefForUser)
				&& Objects.equals(dateChangeUserForRelief, other.dateChangeUserForRelief)
				&& Objects.equals(delegateAll, other.delegateAll) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(idRelief, other.idRelief) && Objects.equals(permanent, other.permanent)
				&& Objects.equals(returnCommand, other.returnCommand) && Objects.equals(temporary, other.temporary)
				&& Objects.equals(time, other.time) && Objects.equals(timeActive, other.timeActive)
				&& Objects.equals(type, other.type) && Objects.equals(unsuscribe, other.unsuscribe)
				&& Objects.equals(userCode, other.userCode) && Objects.equals(userCreateCode, other.userCreateCode)
				&& Objects.equals(userReliefCode, other.userReliefCode);
	}




	@Override
	public int hashCode() {
		return Objects.hash(active, createDate, dateChangeReliefForUser, dateChangeUserForRelief, delegateAll, endDate,
				idRelief, permanent, returnCommand, temporary, time, timeActive, type, unsuscribe, userCode,
				userCreateCode, userReliefCode);
	}

}
