package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.BpmAssignedDTO;
import com.bpm.engine.managers.facades.ServiceBpmAndAssignedFacade;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.SystemReferentModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;

@Service
public class BpmAssignedManager {

	private static final Logger logger = LogManager.getLogger(BpmAssignedManager.class);
	
	private ServiceBpmAndAssignedFacade service;


	@Autowired
	public BpmAssignedManager(ServiceBpmAndAssignedFacade service) {
		super();
		this.service = service;
	}


	public Boolean desactiveBpmAssigned(String codeEmployee ) {
		
		List<BpmAssignedModel> bpmAssignedByEmployeeCode =  service.getBpmAssignedService().findByCodeEmployeeActive(codeEmployee);
		
		bpmAssignedByEmployeeCode.parallelStream().forEach(bpmAssignedModel -> bpmAssignedModel.setActive(false));
		
		return bpmAssignedByEmployeeCode.stream().allMatch(bpmAssignedModel -> service.getBpmAssignedService().saveOrUpdateBpmAssigned(bpmAssignedModel) != null);
		
		
	}
	

	public Boolean replaceUserAssignedForUserReliefInBpmAssigned(String codeEmployee, String codeEmployeeRelief, Long idAssignedRelief) {
	
		List<BpmAssignedModel> bpmAssignedByEmployeeCode = service.getBpmAssignedService().findByCodeEmployeeActive(codeEmployee);
	
		
		if (bpmAssignedByEmployeeCode != null && !bpmAssignedByEmployeeCode.isEmpty() && codeEmployeeRelief != null && idAssignedRelief != null) {
			List<BpmAssignedModel> bpmAssignedEmployeeRelief = new ArrayList<>();
			
			bpmAssignedByEmployeeCode.parallelStream().forEach(bpmAssignedModel -> {
		
				bpmAssignedEmployeeRelief.add(new BpmAssignedModel(
						idAssignedRelief,bpmAssignedModel.getTaskCode(), 
						bpmAssignedModel.getInstanciaProccesId(), 
						codeEmployeeRelief, bpmAssignedModel.getProccesId()));
				
				bpmAssignedModel.setActive(false);
			});
			
			return bpmAssignedEmployeeRelief.stream().allMatch(bpmAssignedModel -> service.getBpmAssignedService().saveOrUpdateBpmAssigned(bpmAssignedModel) != null);
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

				assigned = service.getAssignedService().findByCodeEmployeeAndActive(assignedBPM.getAssigned().getCodeEmployee(),
						true);

				if (assignedBPM.getAssigned().getId() != null && assigned != null
						&& assignedBPM.getAssigned().getId() == assigned.getId()
						&& !assigned.equals(assignedBPM.getAssigned())) {
					service.getAssignedService().saveOrUpdateAssigned(assignedBPM.getAssigned());
					assigned = assignedBPM.getAssigned();
				}

				Long idassigned = assigned.getId();

//                assignedBPM.getCodeTaskOrProces().stream().forEach(codeTask ->
//                        bpmAssignedService.saveOrUpdateBpmAssigned( new BpmAssignedModel(idassigned, codeTask)));
				
				if(assignedBPM.getCodeTaskOrProces() != null && !assignedBPM.getCodeTaskOrProces().isEmpty()) {
					response = assignedBPM.getCodeTaskOrProces().stream().allMatch(codeTask -> service.getBpmAssignedService().saveOrUpdateBpmAssigned(new BpmAssignedModel(idassigned, codeTask)) != null);
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
			List<BpmAssignedModel> temporaryList = service.getBpmAssignedService().findByTaskCodeAndInstanciaProccesIdNull(taskCode,
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
				return service.getBpmAssignedService().saveOrUpdateBpmAssigned(
						new BpmAssignedModel(assignedSave.getId(), taskCode, instanceProccesId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: registrar en el sistema de notificacion error.
		}

		return null;
	}

}




