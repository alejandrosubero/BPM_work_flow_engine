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
public class BpmAssignedModel {

	private Long idBpmAssigned;
	private Long idAssigned;
	private String taskCode;
	private Long instanciaProccesId;
	private Boolean active;
	private String codeEmployee;
	private Long proccesId;

	public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId) {
		if (null != idAssigned) {
			this.idAssigned = idAssigned;
		}
		if (null != taskCode) {
			this.taskCode = taskCode;
		}
		if (null != instanciaProccesId) {
			this.instanciaProccesId = instanciaProccesId;
		}

		this.active = true;
	}

	public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId, String codeEmployee) {
		if (null != idAssigned) {
			this.idAssigned = idAssigned;
		}
		if (null != taskCode) {
			this.taskCode = taskCode;
		}
		if (null != instanciaProccesId) {
			this.instanciaProccesId = instanciaProccesId;
		}
		if (codeEmployee != null && !codeEmployee.equals("")) {
			this.codeEmployee = codeEmployee;
		}

		this.active = true;
	}

	public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId, String codeEmployee, Long proccesId) {
		
		if (null != idAssigned) {
			this.idAssigned = idAssigned;
		}
		if (null != taskCode) {
			this.taskCode = taskCode;
		}
		if (null != instanciaProccesId) {
			this.instanciaProccesId = instanciaProccesId;
		}
		if (codeEmployee != null && !codeEmployee.equals("")) {
			this.codeEmployee = codeEmployee;
		}
		if (null != proccesId) {
			this.proccesId = proccesId;
		}
	
		this.active = true;
	}
	
	
	public BpmAssignedModel(Long idAssigned, String taskCode) {
		this.idAssigned = idAssigned;
		this.taskCode = taskCode;
		this.active = true;
	}

	public void setBpmAssignedModel(BpmAssignedModel assignedBpm) {

		if (null != assignedBpm.getIdAssigned())
			this.idAssigned = assignedBpm.getIdAssigned();

		if (null != assignedBpm.getTaskCode())
			this.taskCode = assignedBpm.getTaskCode();

		if (null != assignedBpm.getInstanciaProccesId())
			this.instanciaProccesId = assignedBpm.getInstanciaProccesId();

		this.idBpmAssigned = assignedBpm.getIdBpmAssigned();

		this.active = assignedBpm.getActive();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BpmAssignedModel other = (BpmAssignedModel) obj;
		return Objects.equals(active, other.active) && Objects.equals(codeEmployee, other.codeEmployee)
				&& Objects.equals(idAssigned, other.idAssigned) && Objects.equals(idBpmAssigned, other.idBpmAssigned)
				&& Objects.equals(instanciaProccesId, other.instanciaProccesId)
				&& Objects.equals(proccesId, other.proccesId) && Objects.equals(taskCode, other.taskCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, codeEmployee, idAssigned, idBpmAssigned, instanciaProccesId, proccesId, taskCode);
	}

	public void updateThis(BpmAssignedModel other) {
		if (!Objects.equals(this.active, other.active)) {
			this.active= other.active;
		}
		if (!Objects.equals(this.codeEmployee, other.codeEmployee)) {
			this.codeEmployee = other.codeEmployee;
		}
		if (!Objects.equals(this.idAssigned, other.idAssigned)) {
			this.idAssigned = other.idAssigned;
		}
		if (!Objects.equals(this.idBpmAssigned, other.idBpmAssigned)) {
			this.idBpmAssigned = other.idBpmAssigned;
		}
		if (!Objects.equals(this.instanciaProccesId, other.instanciaProccesId)) {
			this.instanciaProccesId =  other.instanciaProccesId;
		}
		if (!Objects.equals(this.proccesId, other.proccesId)) {
			this.proccesId = other.proccesId;
		}
		if (!Objects.equals(this.taskCode, other.taskCode)) {
			this.taskCode = other.taskCode;
		}
	}

}
