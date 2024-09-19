package com.bpm.engine.service;

import java.util.List;


import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.model.InstanceAbstractionModel;

public interface InstanceAbstractionService {
	
	public InstanceAbstractionModel findByIdInstance(Long idInstance);
	public List<InstanceAbstractionModel> findByCodeReferent(String codeReferent);
	public List<InstanceAbstractionModel> findByUserWorked(String userWorked);
	public List<InstanceAbstractionModel> findByUserCreateInstance(String userCreateInstance);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstance(String instanOf, Long idInstance);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess);
	public List<InstanceAbstractionModel> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf, Long idInstanceOfProcess, Integer level);
	
	
	//TODO: CHANGE THE VOID RETURN FOR BOOLEAN IF HAD A ERROR RETURN FALSE Y ALL OK RETURN TRUE.
    void updateStatus(String status, Long idInstance);
    void updateActive(Boolean active, Long idInstance);
    void updateUserWorked(String userWorked, Long idInstance);
    void updateInstances(List<InstanceAbstraction> instances, Long idInstance);
    void updateUserWorked(Long idParent, Long idInstance);
}
