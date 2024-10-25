package com.bpm.engine.service;

import com.bpm.engine.entitys.TaskAssigned;
import com.bpm.engine.models.TaskAssignedModel;

import java.util.List;

public interface TaskAssignedService {

    public List<TaskAssignedModel> findByIdBpmAssigned(Long idBpmAssigned);
    public TaskAssignedModel findById(Long id);
    public TaskAssignedModel save(TaskAssigned taskAssigned);
    public void deliteTaskAssigned(Long id);

    public List<TaskAssignedModel> findByIdTaskId(Long taskId);

}
