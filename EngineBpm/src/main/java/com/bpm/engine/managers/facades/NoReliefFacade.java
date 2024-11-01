package com.bpm.engine.managers.facades;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bpm.engine.managers.AssignmentTaskManager;
import com.bpm.engine.managers.BpmAssignedManager;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.BpmAssignedModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.utility.InstanOf;


@Service
public class NoReliefFacade {

	private static final Logger logger = LogManager.getLogger(NoReliefFacade.class);

	private BpmAssignedManager bpmAssignedManager;
	private AssignmentTaskManager assignmentTaskManager;
	private ProcessAndInstanceFacade services;


	@Autowired
	public NoReliefFacade(BpmAssignedManager bpmAssignedManager, AssignmentTaskManager assignmentTaskManager,
			ProcessAndInstanceFacade services) {
		super();
		this.bpmAssignedManager = bpmAssignedManager;
		this.assignmentTaskManager = assignmentTaskManager;
		this.services = services;
	}


	public Boolean execute(ReliefAssignedModel reliefModel) {

		try {
			AssignedModel updateAssigned = assignmentTaskManager.changeRoleAssigned(reliefModel.getUserCode(), null);

			Boolean desactiveOldBpmAssigned = this.bpmAssignedManager.desactiveBpmAssigned(reliefModel.getUserCode());

			List<InstanceAbstractionModel> listaInstances = this.services.instanceManager()
					.getInstanceAbstractionService().findByUserWorked(reliefModel.getUserCode());

			for (InstanceAbstractionModel instanceAbstractionModel : listaInstances) {

				if (instanceAbstractionModel.getInstanOf().equals(InstanOf.INSTANCE_STAGE.getValue())) {

					BpmAssignedModel temporalAssigned = this.assignmentTaskManager.getOneAssigned(
							instanceAbstractionModel.getCodeReferent(),
							instanceAbstractionModel.getUserCreateInstance(),
							instanceAbstractionModel.getIdInstanceOfProcess(), instanceAbstractionModel.getIdProcess(),
							0);
					if (temporalAssigned != null) {
						this.services.instanceManager().getInstanceAbstractionService().updateUserWorked(
								temporalAssigned.getCodeEmployee(), instanceAbstractionModel.getIdInstance());
					}
				}
			}

			
		} catch (DataAccessException e) {
			logger.error("Error at update a InstanceAbstraction field: ", e);
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			logger.error("the one or all parameters are null");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
