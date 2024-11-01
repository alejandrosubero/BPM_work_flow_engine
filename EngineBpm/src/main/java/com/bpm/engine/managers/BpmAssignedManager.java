package com.bpm.engine.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		} 
		
		
		return false;
	}
	

	
	public Boolean updateUserAssignedForUserReliefInBpmAssigned(String codeEmployee, String codeEmployeeRelief,Long idAssignedRelief) {

		List<BpmAssignedModel> bpmAssignedByEmployeeCode = service.getBpmAssignedService().findByCodeEmployeeActive(codeEmployee);

		List<BpmAssignedModel> bpmAssignedByEmployeeRelief = service.getBpmAssignedService().findByCodeEmployeeActive(codeEmployeeRelief);

		List<BpmAssignedModel> noMatchingBpmAssignedModel = new ArrayList<>();


		if (bpmAssignedByEmployeeCode != null 
				&& !bpmAssignedByEmployeeCode.isEmpty() 
				&& bpmAssignedByEmployeeRelief != null 
				&& !bpmAssignedByEmployeeRelief.isEmpty() 
				&& codeEmployeeRelief != null && idAssignedRelief != null) {

			
			ElementBoolean matchFound = ElementBoolean.getInstance();
			
			bpmAssignedByEmployeeCode.forEach(modelEmployeeCode -> {
			
				matchFound.setElement(false);

				bpmAssignedByEmployeeRelief.forEach(modelEmployeeRelief -> {

					if (modelEmployeeRelief.getTaskCode().equals(modelEmployeeCode.getTaskCode())
							&& modelEmployeeRelief.getProccesId().equals(modelEmployeeCode.getProccesId())) {
						modelEmployeeRelief.setActive(true);
						modelEmployeeCode.setActive(false);
						matchFound.setElement(true);
					}
				});

			
				if (!matchFound.getElement()) {
					noMatchingBpmAssignedModel.add(modelEmployeeCode);
				}
			});

			if (!noMatchingBpmAssignedModel.isEmpty()) {
				List<BpmAssignedModel> bpmAssignedlist = this.createListOfBpmAssignedModel(noMatchingBpmAssignedModel,
						codeEmployeeRelief, idAssignedRelief);
				return bpmAssignedlist.stream().allMatch(bpmAssignedModel -> service.getBpmAssignedService()
						.saveOrUpdateBpmAssigned(bpmAssignedModel) != null);
			}

		}

		if (bpmAssignedByEmployeeCode != null && !bpmAssignedByEmployeeCode.isEmpty() 
				&& codeEmployeeRelief != null && idAssignedRelief != null) {

			List<BpmAssignedModel> bpmAssignedlist = this.createListOfBpmAssignedModel(bpmAssignedByEmployeeCode,
					codeEmployeeRelief, idAssignedRelief);

			return bpmAssignedlist.stream().allMatch(bpmAssignedModel -> service.getBpmAssignedService()
					.saveOrUpdateBpmAssigned(bpmAssignedModel) != null);

		}

		return false;
	}
	
	
	
	private List<BpmAssignedModel> createListOfBpmAssignedModel(List<BpmAssignedModel> bpmAssignedByEmployeeCode, String codeEmployeeRelief, Long idAssignedRelief ){
		
		List<BpmAssignedModel> bpmAssignedEmployeeRelief = new ArrayList<>();
		
		bpmAssignedByEmployeeCode.parallelStream().forEach(bpmAssignedModel -> {
	
			bpmAssignedEmployeeRelief.add(new BpmAssignedModel(
					idAssignedRelief,bpmAssignedModel.getTaskCode(), 
					bpmAssignedModel.getInstanciaProccesId(), 
					codeEmployeeRelief, bpmAssignedModel.getProccesId()));
			
			bpmAssignedModel.setActive(false);
		});
		
		return bpmAssignedEmployeeRelief;
		
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

	
	public static class ElementBoolean{
		
		private ElementBoolean() {
			
		}
		
		private static ElementBoolean instance;
		
		private Boolean element;
		
		public static ElementBoolean getInstance() {
			if(instance == null) {
				return new ElementBoolean();
			}else {
				return instance;
			}
		}
		

		public Boolean getElement() {
			return element;
		}

		public void setElement(Boolean element) {
			this.element = element;
		}
		
		
	}
	
}




