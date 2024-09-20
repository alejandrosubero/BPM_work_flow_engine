package com.bpm.engine.model;




public class BpmAssignedModel {

    private Long idBpmAssigned;
    private Long idAssigned;
    private String taskCode;
    private Long instanciaProccesId;
    private Boolean active;
    private String codeEmployee;

    public BpmAssignedModel() {
    }

    public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId) {
        if(null!=idAssigned){
            this.idAssigned = idAssigned;
        }
        if(null!=taskCode ){
            this.taskCode = taskCode;
        }
        if(null!=instanciaProccesId){
            this.instanciaProccesId = instanciaProccesId;
        }
        
        this.active = true;
    }
    
    public BpmAssignedModel(Long idAssigned, String taskCode, Long instanciaProccesId, String codeEmployee ) {
        if(null!=idAssigned){
            this.idAssigned = idAssigned;
        }
        if(null!=taskCode ){
            this.taskCode = taskCode;
        }
        if(null!=instanciaProccesId){
            this.instanciaProccesId = instanciaProccesId;
        }
        if(codeEmployee != null && !codeEmployee.equals("")) {
        	this.codeEmployee = codeEmployee;
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

	public String getCodeEmployee() {
		return codeEmployee;
	}

	public void setCodeEmployee(String codeEmployee) {
		this.codeEmployee = codeEmployee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((codeEmployee == null) ? 0 : codeEmployee.hashCode());
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
		BpmAssignedModel other = (BpmAssignedModel) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (codeEmployee == null) {
			if (other.codeEmployee != null)
				return false;
		} else if (!codeEmployee.equals(other.codeEmployee))
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
