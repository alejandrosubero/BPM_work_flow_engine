package com.bpm.engine.componets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.TaskAssignedModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;

@Component
public class AssignmentTaskManager {
	
	private BpmAssignedService bpmAssignedService;
	private AssignedService assignedService;
	private ConectBpmToEmployeeService conectBpmToEmployeeService;
	
	
	@Autowired
	public AssignmentTaskManager(BpmAssignedService bpmAssignedService, AssignedService assignedService, ConectBpmToEmployeeService conectBpmToEmployeeService) {
	
		this.bpmAssignedService = bpmAssignedService;
		this.assignedService = assignedService;
		this.conectBpmToEmployeeService = conectBpmToEmployeeService;
	}
	
	
	

	public List<TaskAssignedModel> getTaskAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		List<TaskAssignedModel> assignes = new ArrayList<>();
		
		if( taskCode != null &&  codeEmployee!= null &&  instanceProccesId != null && router != null) {

			try {
			
				List<TaskAssignedModel> bpmAssigned = this.getTaskAssignedFromBpmAssigned(taskCode);
	
				if (bpmAssigned != null && bpmAssigned.size() > 0) {
					assignes.addAll(bpmAssigned);
				} 
	
				if ((bpmAssigned == null || bpmAssigned.isEmpty()) && router == 0 || router == 1) {
					assignes.addAll(getAssigned(taskCode, codeEmployee, instanceProccesId, router));
				}
	
				if ((bpmAssigned == null || bpmAssigned.isEmpty()) && router == 2) {
					assignes.addAll(this.getTaskAssignedFromBpmAssigned(taskCode));
				}
		
					
		} catch (Exception e) {
			e.printStackTrace();
			return assignes;
		}
	}		
		return assignes;
	}

	
	
	/***
	 * 
	 * @param taskCode
	 * @param codeEmployee
	 * @param instanceProccesId
	 * @param router
	 * @service call to employeeServise need wait response and check in internal assigned
	 * @return
	 */
	public List<TaskAssignedModel> getAssigned(String taskCode, String codeEmployee, Long instanceProccesId,Integer router) {
		
		List<TaskAssignedModel> assignesFromRouter = new ArrayList<>();
		AssignedModel assigned = null;
		
		if (router == 1) {
			
			AssignedModel employeeCreator = this.assignedService.findByCodeEmployeeAndActive(codeEmployee, true);
			
			if (employeeCreator != null && employeeCreator.getemployeeRole().getCodeRole() != null) {
				assigned = this.conectBpmToEmployeeService.getAssigned(codeEmployee, employeeCreator.getemployeeRole().getCodeRole());
			} else {
				assigned = conectBpmToEmployeeService.getAssigned(codeEmployee);
			}
		}
		
		if (router == 0) {
			assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
		}
		
		//IS NESESARY SAVE ALL 
		if(assigned != null) {
			saveAndCreteBpmAssigned(taskCode,assigned ); 
		}
		
		
		assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, instanceProccesId));
		return assignesFromRouter;
	}
	
	

	public void saveAndCreteBpmAssigned(String taskCode, AssignedModel assigned ) {
		
		try {
		
			if(assigned != null) {
				AssignedModel assignedSave = this.assignedService.save(assigned);
				if(assignedSave.getId() != null) {
					bpmAssignedService.saveOrUpdateBpmAssigned(new BpmAssignedModel(assignedSave.getId(), taskCode));
				}
			}
	
		}catch (Exception e) {
			e.printStackTrace();
			//TODO: registrar en el sistema de notificacion error.
		}
	}
	
	
	
/***
 * 
 * @param taskCode
 * @return TaskAssignedModel List from BpmAssigned for taskCode were InstanciaProccesId is null? in data base.
 */
	public  List<TaskAssignedModel> getTaskAssignedFromBpmAssigned(String taskCode) {
		//TODO: require analyze for implement ...  
		List<TaskAssignedModel> bpmAssigned = new ArrayList<>();
		try {
			List<BpmAssignedModel> bpmAssignedList = bpmAssignedService.findByTaskCodeAndInstanciaProccesIdNull(taskCode,true);
			
			if (bpmAssignedList != null && bpmAssignedList.size() > 0) {
				bpmAssignedList.stream().forEach(bpmAssignedModel -> 
				bpmAssigned.add(new TaskAssignedModel(bpmAssignedModel.getIdBpmAssigned())));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return bpmAssigned;
		}
		return bpmAssigned;
	}

	
	public TaskAssignedModel getTaskAsigned(AssignedModel assigned, String taskCode, Long instanceProccesId) {
		AssignedModel assignedInSisten = assignedService.findByCodeEmployeeAndActive(assigned.getCodeEmployee(), true);
		if (assignedInSisten == null) {
			assignedInSisten = assignedService.save(assigned);
		}
		BpmAssignedModel bpmAssigned = bpmAssignedService.instanceBpmAssigned(assignedInSisten.getId(), taskCode,
				instanceProccesId);
		return new TaskAssignedModel(bpmAssigned.getIdBpmAssigned());
	}
	
	
	
	
	
	
	
	
	
	
	

}
