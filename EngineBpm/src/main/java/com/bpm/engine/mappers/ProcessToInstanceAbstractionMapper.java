package com.bpm.engine.mappers;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.utility.InstanOf;
import com.bpm.engine.utility.SystemSate;


@Component
public class ProcessToInstanceAbstractionMapper {

	
	public InstanceAbstractionModel createFromProcess(ProcessModel processModel, String userCreateInstance) {
		
		return InstanceAbstractionModel.builder()
		.name(processModel.getName())
		.instanOf(InstanOf.INSTANCE_PROCESS.getValue())
		.title(processModel.getProcesTitle())
		.idProcess(processModel.getId_process())
		.idRefenet(processModel.getId_process())
		.codeProcess(processModel.getProcesCode())
		.codeReferent(processModel.getProcesCode())
		.isParallel(false)
		.status(SystemSate.CREATE.toString())
		.active(true)
		.dateCreate(new Date())
		.userCreateInstance(processModel.getUserCreate())
		.build();
		
	}
	
	
	
	
}
