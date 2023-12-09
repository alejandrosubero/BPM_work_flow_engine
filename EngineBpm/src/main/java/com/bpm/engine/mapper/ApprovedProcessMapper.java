package com.bpm.engine.mapper;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.model.ApprovedProcessModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApprovedProcessMapper {


    public ApprovedProcessModel entityToPojo(ApprovedProcess entity) {
        ModelMapper modelMapper = new ModelMapper();
        ApprovedProcessModel pojo = null;
        if (entity != null) {
            pojo = modelMapper.map(entity, ApprovedProcessModel.class);
        }
        return pojo;
    }

    public ApprovedProcess pojoToEntity(ApprovedProcessModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        ApprovedProcess entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, ApprovedProcess.class);
        }
        return entity;
    }

}
