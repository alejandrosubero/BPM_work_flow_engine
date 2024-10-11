package com.bpm.engine.mappers;

import com.bpm.engine.entitys.Parameters;
import com.bpm.engine.models.ParametersModel;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParametersMapper {

    public Parameters pojoToEntity(ParametersModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Parameters entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, Parameters.class);
        }
        return entity;
    }

    public ParametersModel entityToPojo(Parameters entity) {
        ModelMapper modelMapper = new ModelMapper();
        ParametersModel pojo = null;
        if (entity != null) {
            pojo = modelMapper.map(entity, ParametersModel.class);
        }
        return pojo;
    }

}
