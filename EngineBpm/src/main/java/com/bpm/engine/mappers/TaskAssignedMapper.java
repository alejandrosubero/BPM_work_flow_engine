package com.bpm.engine.mappers;

import com.bpm.engine.entitys.TaskAssigned;
import com.bpm.engine.entitys.TaskType;
import com.bpm.engine.models.TaskAssignedModel;
import com.bpm.engine.models.TaskTypeModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskAssignedMapper {

    public TaskAssignedModel entityToPojo(TaskAssigned entity) {
        ModelMapper modelMapper = new ModelMapper();
        TaskAssignedModel pojo = null;

        if ( entity != null) {
            pojo = modelMapper.map(entity, TaskAssignedModel.class);
        }
        return  pojo;
    }

    public TaskAssigned pojoToEntity(TaskAssignedModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        TaskAssigned entity = null;

        if ( pojo != null) {
            entity = modelMapper.map(pojo, TaskAssigned.class);
        }
        return  entity;
    }

    public List<TaskAssignedModel> entityListToPojoList(List<TaskAssigned> entity) {
        List<TaskAssignedModel> pojos = new ArrayList<>();
        entity.stream().forEach(taskAssigned -> {pojos.add(this.entityToPojo(taskAssigned));});
        return  pojos;
    }
}
