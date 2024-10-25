package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.BpmAssignedDTO;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.SystemReferentModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;

@Service
public class BpmAssignedManager {

	private static final Logger logger = LogManager.getLogger(BpmAssignedManager.class);

	private AssignedService assignedService;
	private BpmAssignedService bpmAssignedService;

	@Autowired
	public BpmAssignedManager(AssignedService assignedService, BpmAssignedService bpmAssignedService) {
		this.assignedService = assignedService;
		this.bpmAssignedService = bpmAssignedService;
	}

	public BpmAssignedService service() {
		return this.bpmAssignedService;
	}


	public Boolean desactiveBpmAssigned(String codeEmployee ) {
		
		List<BpmAssignedModel> bpmAssignedByEmployeeCode = bpmAssignedService.findByCodeEmployeeActive(codeEmployee);
		
		bpmAssignedByEmployeeCode.parallelStream().forEach(bpmAssignedModel -> bpmAssignedModel.setActive(false));
		
		return bpmAssignedByEmployeeCode.stream().allMatch(bpmAssignedModel -> bpmAssignedService.saveOrUpdateBpmAssigned(bpmAssignedModel) != null);
		
		
	}
	

	public Boolean replaceUserAssignedForUserReliefInBpmAssigned(String codeEmployee, String codeEmployeeRelief, Long idAssignedRelief) {
	
		List<BpmAssignedModel> bpmAssignedByEmployeeCode = bpmAssignedService.findByCodeEmployeeActive(codeEmployee);
	
		
		if (bpmAssignedByEmployeeCode != null && !bpmAssignedByEmployeeCode.isEmpty() && codeEmployeeRelief != null && idAssignedRelief != null) {
			List<BpmAssignedModel> bpmAssignedEmployeeRelief = new ArrayList<>();
			
			bpmAssignedByEmployeeCode.parallelStream().forEach(bpmAssignedModel -> {
		
				bpmAssignedEmployeeRelief.add(new BpmAssignedModel(
						idAssignedRelief,bpmAssignedModel.getTaskCode(), 
						bpmAssignedModel.getInstanciaProccesId(), 
						codeEmployeeRelief, bpmAssignedModel.getProccesId()));
				
				bpmAssignedModel.setActive(false);
			});
			
			return bpmAssignedEmployeeRelief.stream().allMatch(bpmAssignedModel -> bpmAssignedService.saveOrUpdateBpmAssigned(bpmAssignedModel) != null);
		} else {
			
		}
		return false;
	}
	

	
	public Boolean saveOrUpdateBpmAssigned(BpmAssignedDTO assignedBPM) {

		AssignedModel assigned = null;
		Boolean response = false;

		try {

			if (assignedBPM.getAssigned() != null && assignedBPM.getAssigned().getId() != null
					&& assignedBPM.getAssigned().getCodeEmployee() != null) {

				assigned = assignedService.findByCodeEmployeeAndActive(assignedBPM.getAssigned().getCodeEmployee(),
						true);

				if (assignedBPM.getAssigned().getId() != null && assigned != null
						&& assignedBPM.getAssigned().getId() == assigned.getId()
						&& !assigned.equals(assignedBPM.getAssigned())) {
					assignedService.saveOrUpdateAssigned(assignedBPM.getAssigned());
					assigned = assignedBPM.getAssigned();
				}

				Long idassigned = assigned.getId();

//                assignedBPM.getCodeTaskOrProces().stream().forEach(codeTask ->
//                        bpmAssignedService.saveOrUpdateBpmAssigned( new BpmAssignedModel(idassigned, codeTask)));
				
				if(assignedBPM.getCodeTaskOrProces() != null && !assignedBPM.getCodeTaskOrProces().isEmpty()) {
					response = assignedBPM.getCodeTaskOrProces().stream().allMatch(codeTask -> bpmAssignedService.saveOrUpdateBpmAssigned(new BpmAssignedModel(idassigned, codeTask)) != null);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return response;
	}

	/***
	 * 
	 * @param taskCode
	 * @return BpmAssignedModel List from BpmAssigned for taskCode were
	 *         InstanciaProccesId is null? in data base.
	 */
	public List<BpmAssignedModel> getAssignedFromBpmAssigned(String taskCode) {
		List<BpmAssignedModel> bpmAssigned = new ArrayList<>();
		try {
			List<BpmAssignedModel> temporaryList = bpmAssignedService.findByTaskCodeAndInstanciaProccesIdNull(taskCode,
					true);
			if (temporaryList != null && !temporaryList.isEmpty()) {
				bpmAssigned.addAll(temporaryList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return bpmAssigned;
		}
		return bpmAssigned;
	}

	
	
	public BpmAssignedModel saveAndCreteNewBpmAssigned(String taskCode, AssignedModel assignedSave,
			Long instanceProccesId) {

		try {
			if (assignedSave != null && assignedSave.getId() != null) {
				return bpmAssignedService.saveOrUpdateBpmAssigned(
						new BpmAssignedModel(assignedSave.getId(), taskCode, instanceProccesId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: registrar en el sistema de notificacion error.
		}

		return null;
	}

}

//List<SystemReferentModel> referentEmployee = new ArrayList<>();
//referentEmployee.add(SystemReferentModel.builder()
//		.referent0(codeEmployeeRelief)
//		.referentLong0(idAssignedRelief)
//		.referent1(bpmAssignedModel.getTaskCode())
//		.referentLong1(bpmAssignedModel.getInstanciaProccesId())
//		.referentLong2(bpmAssignedModel.getProccesId())
//		.build());


//referentEmployee.parallelStream().forEach(systemReferentModel -> 
//bpmAssignedEmployeeRelief.add(new BpmAssignedModel(
//		systemReferentModel.getReferentLong0(),
//		systemReferentModel.getReferent1(), 
//		systemReferentModel.getReferentLong1(), 
//		systemReferentModel.getReferent0() ,
//		systemReferentModel.getReferentLong2())
//));


