package com.bpm.engine.serviceImplement;

import com.bpm.engine.entitys.TaskAssigned;
import com.bpm.engine.mappers.TaskAssignedMapper;
import com.bpm.engine.models.TaskAssignedModel;
import com.bpm.engine.repository.TaskAssignedRepository;
import com.bpm.engine.service.TaskAssignedService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskAssignedServiceImplemet implements TaskAssignedService {

	private static final Logger logger = LogManager.getLogger(TaskAssignedServiceImplemet.class);
	
    @Autowired
    private TaskAssignedRepository repository;

    @Autowired
    private TaskAssignedMapper mapper;

    @Override
    public List<TaskAssignedModel> findByIdBpmAssigned(Long idBpmAssigned) {
        return mapper.entityListToPojoList(repository.findByIdBpmAssigned(idBpmAssigned));
    }

    @Override
    public TaskAssignedModel findById(Long id) {
        return this.byId(id);
    }

    private TaskAssignedModel byId(Long id) {
        Optional<TaskAssigned> resultCall = repository.findById(id);
        if (resultCall.isPresent()) {
            return mapper.entityToPojo(resultCall.get());
        }
        return null;
    }

    @Override
    public TaskAssignedModel save(TaskAssigned taskAssigned) {
        TaskAssignedModel response = null;
        if (null != taskAssigned) {
            response = mapper.entityToPojo(repository.save(taskAssigned));
        }
        return response;
    }

    @Override
    public void deliteTaskAssigned(Long id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
    }

    @Override
    public List<TaskAssignedModel> findByIdTaskId(Long taskId) {
        return mapper.entityListToPojoList(repository.findByTaskId(taskId));
    }

}
