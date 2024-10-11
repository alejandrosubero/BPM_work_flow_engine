package com.bpm.engine.managers;

import static com.bpm.engine.utility.SystemSate.CREATE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.entitys.ControlProcessReferent;
import com.bpm.engine.interfaces.RadomCode;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.ProcessMapper;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.ControlProcessReferentModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.models.SystemInternalResponseModel;
import com.bpm.engine.models.TaskModel;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.validation.ProcessValidation;

@Service
public class ProcessManager implements RadomCode {

	private ProcessService processService;
	private ProcessValidation processValidationService;
	private ProcessMapper processMapper;
	private MapperEntityRespone mapperEntityRespone;
	private ControlProcessReferentService controlProcessReferent;
	private AssignmentTaskManager assignmentTaskManager;
	

	@Autowired
	public ProcessManager(ProcessService processService, ProcessValidation processValidationService,
			ProcessMapper processMapper, MapperEntityRespone mapperEntityRespone,
			ControlProcessReferentService controlProcessReferent, AssignmentTaskManager assignmentTaskManager) {
		this.processService = processService;
		this.processValidationService = processValidationService;
		this.processMapper = processMapper;
		this.mapperEntityRespone = mapperEntityRespone;
		this.controlProcessReferent = controlProcessReferent;
		this.assignmentTaskManager = assignmentTaskManager;
	}

	public List<ProcessModel> getProcessOfUser(String user) {

		AssignedModel assignedUser = assignmentTaskManager.getAssignedModel(user);

		List<ProcessModel> listProcessModel = new ArrayList<>();

		listProcessModel.addAll(this.findAllByRoleCodeRole(assignedUser.getemployeeRole().getCodeRole()));

		listProcessModel.addAll(this.findByGlobal(true));

		List<ProcessModel> distinctProcessModelList = listProcessModel.stream().distinct().collect(Collectors.toList());

		return distinctProcessModelList;
	}

	public List<ProcessModel> findAllByRoleCodeRole(String codeRole) {
		return processService.findAllByRoleCodeRole(codeRole);
	}

	public List<ProcessModel> findByGlobal(Boolean global) {
		return processService.findByGlobalContaining(global);
	}

	public List<ProcessModel> getListProcessModel(List<Long> idProcessModelList) {

		List<ProcessModel> listProcessModel = new ArrayList<>();

		idProcessModelList.stream().parallel().forEach(id -> {
			ProcessModel process = processService.findById(id);
			if (process != null) {
				listProcessModel.add(process);
			}
		});
		return listProcessModel;
	}

	public EntityRespone createProcess(ProcessModel processModel) {
		EntityRespone entityRespone = null;
		SystemInternalResponseModel response = processValidationService.isValid(processModel);
		if (response != null && response.getCondition()) {
			processModel.setCreateDate(new Date());
			processModel.setActivo(true);
			processModel.setState(CREATE.name());

			for (StageModel stage : processModel.getstages()) {
				stage.setDateCreate(new Date());
				for (TaskModel task : stage.gettasks()) {
					task.setDateCreate(new Date());
				}
			}

			ProcessModel process = processService.save(processMapper.pojoToEntity(processModel));

			if (process != null && process.getId_process() != null) {

				process.setProcesCode(this.getCode());
				controlProcessReferent.saveControlProcess(
						new ControlProcessReferent(process.getProcesCode(), process.getName(), process.getProcesTitle(),
								process.getState(), Constants.TYPE_PROCESS, process.getId_process(), true, new Date()));
				for (StageModel stage : process.getstages()) {
					stage.setStageCode(this.getCode());
					controlProcessReferent.saveControlProcess(
							new ControlProcessReferent(stage.getStageCode(), stage.getName(), stage.getTitle(),
									CREATE.name(), Constants.TYPE_STAGE, stage.getIdStage(), true, new Date()));
					for (TaskModel task : stage.gettasks()) {
						task.setCode(this.getCode());
						controlProcessReferent.saveControlProcess(
								new ControlProcessReferent(task.getCode(), task.getName(), task.getTitle(),
										CREATE.name(), Constants.TYPE_TASK, task.getIdTask(), true, new Date()));
					}
				}
				ProcessModel retrunModel = processService.update(processMapper.pojoToEntity(process));
				return entityRespone = mapperEntityRespone.setEntityResponT(new ProcessDTO(retrunModel),
						"the process is save", "The process format are correct");
				
				// TODO: IN THIS POINT PUT THE CODE FOR NOTIFICATION POSITIVE.
			}
		} else {

			return entityRespone = mapperEntityRespone.setEntityResponT(null, "Error in Data", response.getMensaje());
		}
		
		return entityRespone;
	}

	/***
	 *
	 * @return // the code length can be more to 16 if
	 */
	private String getCode() {
		String code = null;
		String initialCode = this.generateCodeLikeNumber(16);
		ControlProcessReferentModel controlProcessReferentModel = controlProcessReferent.findByCode(initialCode);
		if (controlProcessReferentModel.getId() != null) {
			this.getCode();
		} else {
			code = initialCode;
		}
		return code;
	}

	public ProcessModel findByProcesCode(String procesCode) {
		return processService.findByProcesCode(procesCode);
	}

}
