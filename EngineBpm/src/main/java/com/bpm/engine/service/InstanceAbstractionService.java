package com.bpm.engine.service;

import java.util.List;


import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.relief.model.ReliefAssignedModel;

public interface InstanceAbstractionService {
	
	public InstanceAbstractionModel findByIdInstance(Long idInstance);
	public List<InstanceAbstractionModel> findByCodeReferent(String codeReferent);
	public List<InstanceAbstractionModel> findByUserWorked(String userWorked);
	public List<InstanceAbstractionModel> findByUserCreateInstance(String userCreateInstance);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstance(String instanOf, Long idInstance);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf, Long idInstanceOfProcess, Integer level);
	public InstanceAbstractionModel save(InstanceAbstractionModel instance);
	public InstanceAbstractionModel saveInitial(InstanceAbstractionModel instance);
	public List<InstanceAbstractionModel> finBySearch(String keyword);
	public List<InstanceAbstractionModel> findByUser(String userWorked);
	
    void updateStatus(String status, Long idInstance);
    void updateActive(Boolean active, Long idInstance);
    void updateUserWorked(String userWorked, Long idInstance);
    void updateUserCreateInstance(String userCreateInstance, Long idInstance);
    
    void updateInstances(List<InstanceAbstraction> instances, Long idInstance);
    void updateUserWorked(Long idParent, Long idInstance);
    
//	public List<InstanceAbstraction> findByUserWorkedAndActive(String userWorked, Boolean active);
//	public List<InstanceAbstraction> findByUserCreateInstanceAndActive(String userCreateInstance, Boolean active);
	
	public Boolean changeUserWorked(String userWorked, String newUserWorked);
	public Boolean changeUserCreateInstance(String userCreateInstance, String newUserCreateInstance);
	public Boolean changeUserCreateInstance(ReliefAssignedModel reliefModel, List<Long> idInstances);
	public List<InstanceAbstractionModel> findByUserWorked(String userWorked, Boolean active);
}
