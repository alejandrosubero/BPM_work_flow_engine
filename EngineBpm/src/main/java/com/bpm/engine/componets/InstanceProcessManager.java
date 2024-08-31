package com.bpm.engine.componets;

import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.entitys.Process;
import com.bpm.engine.model.*;
import com.bpm.engine.service.*;
import com.bpm.engine.serviceImplement.BpmAssignedServiceImplement;
import com.bpm.engine.utility.Constants;
import com.bpm.engine.utility.SystemSate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//TODO: routed -> AssignedModel-> mach(codeEmployee) and go to -> (List<ApprovedProcessModel> approvedProcess -> ApprovedProcessModel -> processCode or idProcess are granted
//Note: resquest fron front tu the engine.


// this class handled the creation on InstanceProcess and the state of InstanceProcess.
@Service
public class InstanceProcessManager {

    private ProcessService processService;
    private InstanceProcessService instanceProcessService;
    private InstanceStageService stageService;
    private InstanceTaskService taskService;
    private BpmAssignedService bpmAssignedService;
    private AssignedService assignedService;
    private TaskAssignedService taskAssignedService;
    private ControlProcessReferentService controlProcessReferentService;
    private ConectBpmToEmployeeService conectBpmToEmployeeService;


    @Autowired
    public InstanceProcessManager(ProcessService processService, InstanceProcessService instanceProcessService, InstanceStageService stageService,
                                  InstanceTaskService taskService, BpmAssignedService bpmAssignedService, AssignedService assignedService,
                                  TaskAssignedService taskAssignedService, ControlProcessReferentService controlProcessReferentService,
                                  ConectBpmToEmployeeService conectBpmToEmployeeService) {
        this.processService = processService;
        this.instanceProcessService = instanceProcessService;
        this.stageService = stageService;
        this.taskService = taskService;
        this.bpmAssignedService = bpmAssignedService;
        this.assignedService = assignedService;
        this.taskAssignedService = taskAssignedService;
        this.controlProcessReferentService = controlProcessReferentService;
        this.conectBpmToEmployeeService = conectBpmToEmployeeService;
    }

    
    //TODO: SEPARATE IN CLASS BUILD TASK STAGE O SONTHING LIKE THAT.

    public Boolean createInstanceProcess(SystemRequest systemRequest) {
        ProcessModel processRequest = processService.findByProcesCode(systemRequest.getProcessCode());
        InstanceProcessModel instanceProcess = null;
       
        if (processRequest != null) {
            
        	instanceProcess = instanceProcessService.saveInternal(new InstanceProcessModel(processRequest, systemRequest.getCodeEmployee()));
            Long instanceProccesId = instanceProcess.getIdInstanceProcess();
            instanceProcess.setprocess(processRequest);

            List<InstanceStageModel> stageModelList = new ArrayList<>();

            if (instanceProccesId != null  && null != processRequest.getProcesCode() && null != processRequest.getstages() && processRequest.getstages().size() > 0 ) {
        
            	processRequest.getstages().stream().forEach(stageModel -> {
            	
            		//this point is create the instance stage father
                    InstanceStageModel instanceStage = new InstanceStageModel(stageModel, processRequest.getProcesCode());

                    //this point evaluate the stage internal...
                    if (null != stageModel.getstages() && stageModel.getstages().size() > 0 && null != processRequest.getProcesCode()) {
                    	
                        stageModel.getstages().stream().forEach(internalsStageModels -> {
                        	
                        					//TODO: THIS POINT WORK WITH INSTANCES STAGE INTERNAL OF STAGE
                            InstanceStageModel internalInstanceStage = new InstanceStageModel(internalsStageModels, processRequest.getProcesCode());
                            
                            if (internalsStageModels.gettasks().size() > 0) {
                            	
                                internalInstanceStage.setinstancesTasks(this.setTask(stageModel, systemRequest, instanceProccesId));
                                internalInstanceStage.setInstanceProcessId(instanceProccesId);
                                
                                //TODO: THIS POINT ADD TO STAGE FATHER
                                instanceStage.getinstanceStages().add(internalInstanceStage);
                          
                            }
                            
                        });
                        
                    }
                    
                    //this point set task of the stage...
                    if (stageModel.gettasks().size() > 0) {
                        instanceStage.setinstancesTasks(this.setTask(stageModel, systemRequest, instanceProccesId));
                    }
                    instanceStage.setInstanceProcessId(instanceProccesId);
                    stageModelList.add(instanceStage);
                    
                });
            	
            }
            
            instanceProcess.setinstanceStage(stageModelList);
            String code = instanceProcess.getprocess().getProcesCode();
            ControlProcessReferentModel referentModel =
                    controlProcessReferentService.saveOrUpdateInternalControlProcess(
                            new ControlProcessReferentModel(
                                    code,
                                    instanceProcess.getName(),
                                    instanceProcess.getTitle(),
                                    instanceProcess.getState(),
                                    Constants.TYPE_PROCESS,
                                    instanceProcess.getIdInstanceProcess()));

            instanceProcess.setIdControlProcessReferent(referentModel.getIdReference());
            instanceProcess.getinstanceStage().stream().forEach(instanceStageModel -> {
                if(instanceStageModel.getState().equals(SystemSate.ASSIGNED.toString())){
                    instanceStageModel.getinstancesTasks()
                            .forEach(task -> task.setState(SystemSate.ASSIGNED.toString()));
                }});

            instanceProcess = instanceProcessService.updateInstanceProcessII(instanceProcess);
            List<InstanceStageModel> instancestageModel = instanceProcess.getinstanceStage()
                    .stream().filter(instanceStageModel -> instanceStageModel.getState()
                            .equals(SystemSate.ASSIGNED.toString())).collect(Collectors.toList());

            //This part create a ControlProcessReferentService for task into the principal Stage
            instancestageModel.forEach(instanceStageModel -> {
                instanceStageModel.getinstancesTasks().forEach(instanceTaskModel -> {
                    controlProcessReferentService.saveOrUpdateInternalControlProcess(
                            new ControlProcessReferentModel(
                                    instanceTaskModel.getCodeTask(),
                                    instanceTaskModel.getName(),
                                    instanceTaskModel.getTask().getTitle(),
                                    Constants.TYPE_TASK,
                                    instanceTaskModel.gettask().getType().getType(),
                                    instanceTaskModel.getIdInstanceTask()));
                });
            });
        }
        if(instanceProcess.getIdInstanceProcess() != null){
            return true;
        }else {
            return false;
        }
    }


    // READ the task into the StageModel a created the list.
    private List<InstanceTaskModel> setTask(StageModel stageModel, SystemRequest systemRequest, Long instanceProccesId) {
        List<InstanceTaskModel> taskList = new ArrayList<>();

        if (null != stageModel.gettasks() && stageModel.gettasks().size() > 0) {
            stageModel.gettasks().stream().forEach(taskModel -> {
                if (systemRequest.getAssigned() != null && systemRequest.getAssigned().containsKey(taskModel.getCode())
                        && systemRequest.getAssigned().get(taskModel.getCode()).size() > 0) {

                    List<TaskAssignedModel> taskAssignedModelList = new ArrayList<>();
                    systemRequest.getAssigned().get(taskModel.getCode())
                            .forEach(codeEmployee -> taskAssignedModelList.addAll(
                                    setTaskAssigned(taskModel.getCode(), codeEmployee, instanceProccesId, 0)
                            ));
                    taskList.add(new InstanceTaskModel(taskAssignedModelList, taskModel, instanceProccesId));
                } else {
                    List<TaskAssignedModel> assignes = setTaskAssigned(
                            taskModel.getCode(), systemRequest.getCodeEmployee(), instanceProccesId, 1);
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
