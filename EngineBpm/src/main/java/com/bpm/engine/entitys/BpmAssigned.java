package com.bpm.engine.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bpm_bpm_Assigned")
public class BpmAssigned {

    @Id
    @GeneratedValue(generator = "sequence_bpm_assigned")
    @SequenceGenerator(name = "sequence_bpm_assigned", initialValue = 1, allocationSize = 2000)
    @Column(name = "idBpmAssigned", updatable = true, nullable = false, length = 200)
    private Long idBpmAssigned;

    @Column(name = "idAssigned", updatable = true, nullable = true, length = 200)
    private Long idAssigned;

    @Column(name = "taskCode", updatable = true, nullable = true, length = 200)
    private String taskCode;

    @Column(name = "instanciaProccesId", updatable = true, nullable = true, length = 100)
    private Long instanciaProccesId;

    @Column(name = "active", updatable = true, nullable = true, length = 100)
    private Boolean active;
    

    public BpmAssigned() {
    }


    public Long getIdBpmAssigned() {
        return idBpmAssigned;
    }

    public void setIdBpmAssigned(Long idBpmAssigned) {
        this.idBpmAssigned = idBpmAssigned;
    }

    public Long getIdAssigned() {
        return idAssigned;
    }

    public void setIdAssigned(Long idAssigned) {
        this.idAssigned = idAssigned;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getInstanciaProccesId() {
        return instanciaProccesId;
    }

    public void setInstanciaProccesId(Long instanciaProccesId) {
        this.instanciaProccesId = instanciaProccesId;
    }


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((idAssigned == null) ? 0 : idAssigned.hashCode());
		result = prime * result + ((idBpmAssigned == null) ? 0 : idBpmAssigned.hashCode());
		result = prime * result + ((instanciaProccesId == null) ? 0 : instanciaProccesId.hashCode());
		result = prime * result + ((taskCode == null) ? 0 : taskCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BpmAssigned other = (BpmAssigned) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (idAssigned == null) {
			if (other.idAssigned != null)
				return false;
		} else if (!idAssigned.equals(other.idAssigned))
			return false;
		if (idBpmAssigned == null) {
			if (other.idBpmAssigned != null)
				return false;
		} else if (!idBpmAssigned.equals(other.idBpmAssigned))
			return false;
		if (instanciaProccesId == null) {
			if (other.instanciaProccesId != null)
				return false;
		} else if (!instanciaProccesId.equals(other.instanciaProccesId))
			return false;
		if (taskCode == null) {
			if (other.taskCode != null)
				return false;
		} else if (!taskCode.equals(other.taskCode))
			return false;
		return true;
	}

 
}
