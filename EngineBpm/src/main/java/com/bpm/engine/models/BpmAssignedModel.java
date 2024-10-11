package com.bpm.engine.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
public class BpmAssignedModel {

    private Long idBpmAssigned;
    private Long idAssigned;
    private String taskCode;
    private Long instanciaProccesId;
    private Boolean active;
    private String codeEmployee;
    private Long proccesId;

  
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

    
 
}
