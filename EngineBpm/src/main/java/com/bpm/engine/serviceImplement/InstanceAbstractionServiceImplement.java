package com.bpm.engine.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.mapper.InstanceAbstractionMapper;
import com.bpm.engine.model.InstanceAbstractionModel;
import com.bpm.engine.repository.InstanceAbstractionRepository;
import com.bpm.engine.service.InstanceAbstractionService;



public class InstanceAbstractionServiceImplement implements InstanceAbstractionService {

	@Autowired
	private InstanceAbstractionRepository instanceAbstractionRepository;
	
	@Autowired
	private InstanceAbstractionMapper mapper;
	
	@Override
	public InstanceAbstractionModel findByIdInstance(Long idInstance) {
		return mapper.entityToPojo(this.instanceAbstractionRepository.findById(idInstance).get());
	}
	
	@Override
	public List<InstanceAbstraction> findByCodeReferent(String codeReferent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByUserWorked(String userWorked) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByUserCreateInstance(String userCreateInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstance(String instanOf, Long idInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcess(String instanOf, Long idInstanceOfProcess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstanceAbstraction> findByInstanOfAndIdInstanceOfProcessAndLevel(String instanOf,
			Long idInstanceOfProcess, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(String status, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateActive(Boolean active, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserWorked(String userWorked, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInstances(List<InstanceAbstraction> instances, Long idInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserWorked(Long idParent, Long idInstance) {
		// TODO Auto-generated method stub
		
	}



}
