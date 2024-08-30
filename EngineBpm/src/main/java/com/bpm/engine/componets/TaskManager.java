package com.bpm.engine.componets;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.model.*;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;
import com.bpm.engine.service.TaskAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskManager {

    private BpmAssignedService bpmAssignedService;
    private AssignedService assignedService;
    private TaskAssignedService taskAssignedService;
    private ConectBpmToEmployeeService conectBpmToEmployeeService;


    @Autowired
    public TaskManager(BpmAssignedService bpmAssignedService, AssignedService assignedService, TaskAssignedService taskAssignedService, ConectBpmToEmployeeService conectBpmToEmployeeService) {
        this.bpmAssignedService = bpmAssignedService;
        this.assignedService = assignedService;
        this.taskAssignedService = taskAssignedService;
        this.conectBpmToEmployeeService = conectBpmToEmployeeService;
    }


    // READ the task into the StageModel a created the list.
    public List<InstanceTaskModel> setTask(StageModel stageModel, SystemRequest systemRequest, Long instanceProccesId) {
        List<InstanceTaskModel> taskList = new ArrayList<>();

        if (null != stageModel.gettasks() && !stageModel.gettasks().isEmpty()) {

            stageModel.gettasks().forEach(taskModel -> {

                if (systemRequest.getAssigned() != null &&
                        systemRequest.getAssigned().containsKey(taskModel.getCode()) &&
                        !systemRequest.getAssigned().get(taskModel.getCode()).isEmpty()
                ) {
                    List<TaskAssignedModel> taskAssignedModelList = new ArrayList<>();
                    
                    
                    List<String> systemRequestAssigned = systemRequest.getAssigned().get(taskModel.getCode());
                    		
                    systemRequestAssigned.forEach(codeEmployee ->taskAssignedModelList.addAll(setTaskAssigned(taskModel.getCode(), codeEmployee, instanceProccesId, 0)));
                    
                    taskList.add(new InstanceTaskModel(taskAssignedModelList, taskModel, instanceProccesId));
                    
                } else {
                	
                    List<TaskAssignedModel> assignes = setTaskAssigned(taskModel.getCode(), systemRequest.getCodeEmployee(), instanceProccesId, 1);
                    taskList.add(new InstanceTaskModel(assignes, taskModel, instanceProccesId));
                    
                }
            });
        }
        return taskList;
    }


    private List<TaskAssignedModel> setTaskAssigned(String taskCode, String codeEmployee, Long instanceProccesId, Integer router) {
        List<TaskAssignedModel> assignes = new ArrayList<>();
        try {
            if (router == 0 || router == 2) {
                assignes.addAll(getAssigned(taskCode, codeEmployee, instanceProccesId, 0));
                if (router == 2) {
                    assignes.addAll(this.getTaskAssignedFromBpmAssigned(taskCode));
                }

            } else {
                List<TaskAssignedModel> bpmAssigned = this.getTaskAssignedFromBpmAssigned(taskCode);
                if (bpmAssigned.size() > 0) {
                    assignes.addAll(bpmAssigned);
                } else {
                    if (router == 1) {
                        assignes.addAll(getAssigned(taskCode, codeEmployee, instanceProccesId, router));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return assignes;
        }
        return assignes;
    }

    private List<TaskAssignedModel> getAssigned(String taskCode, String codeEmployee, Long instanceProccesId, Integer router) {
        List<TaskAssignedModel> assignesFromRouter = new ArrayList<>();
        AssignedModel assigned = null;
        if (router == 1) {
            AssignedModel employeeCreator = assignedService.findByCodeEmployeeAndActive(codeEmployee, true);
            if (employeeCreator != null && employeeCreator.getemployeeRole().getCodeRole() != null) {
                assigned = conectBpmToEmployeeService.getAssigned(codeEmployee, assigned.getemployeeRole().getCodeRole());
            } else {
                assigned = conectBpmToEmployeeService.getAssigned(codeEmployee, null);
            }
        }
        if (router == 0) {
            assigned = conectBpmToEmployeeService.getEmployeeAssignedFromEmployeeService(codeEmployee);
        }
        assignesFromRouter.add(this.getTaskAsigned(assigned, taskCode, instanceProccesId));
        return assignesFromRouter;
    }


    private List<TaskAssignedModel> getTaskAssignedFromBpmAssigned(String taskCode) {
        List<TaskAssignedModel> bpmAssigned = new ArrayList<>();
        try {
            List<BpmAssignedModel> bpmAssignedList = bpmAssignedService.findByTaskCodeAndInstanciaProccesIdNull(taskCode);
            if (bpmAssignedList != null && bpmAssignedList.size() > 0) {
                bpmAssignedList.stream().forEach(bpmAssignedModel ->
                        bpmAssigned.add(new TaskAssignedModel(bpmAssignedModel.getIdBpmAssigned())
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bpmAssigned;
        }
        return bpmAssigned;
    }


    private TaskAssignedModel getTaskAsigned(AssignedModel assigned, String taskCode, Long instanceProccesId) {
        AssignedModel assignedInSisten = assignedService.findByCodeEmployeeAndActive(assigned.getCodeEmployee(), true);
        if (assignedInSisten == null) {
            assignedInSisten = assignedService.save(assigned);
        }
        BpmAssignedModel bpmAssigned = bpmAssignedService.instanceBpmAssigned(assignedInSisten.getId(), taskCode, instanceProccesId);
        return new TaskAssignedModel(bpmAssigned.getIdBpmAssigned());
    }


}
