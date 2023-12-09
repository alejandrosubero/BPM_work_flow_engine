package com.bpm.engine.repository;

import com.bpm.engine.entitys.TaskAssigned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskAssignedRepository extends CrudRepository<TaskAssigned, Long> {

    public List<TaskAssigned> findByIdBpmAssigned(Long idBpmAssigned);
    public TaskAssigned save(TaskAssigned taskAssigned);
    public List<TaskAssigned> findByTaskId(Long taskId);

}
