package com.bpm.engine.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.entitys.ReliefAssigned;
import com.bpm.engine.model.ReliefAssignedModel;

@Component
public class ReliefAssignedMapper {

	@Autowired 
    private final ModelMapper modelMapper;

    public ReliefAssignedMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReliefAssignedModel toModel(ReliefAssigned entity) {
        return modelMapper.map(entity, ReliefAssignedModel.class);
    }

    public ReliefAssigned toEntity(ReliefAssignedModel model) {
        return modelMapper.map(model, ReliefAssigned.class);
    }
}

