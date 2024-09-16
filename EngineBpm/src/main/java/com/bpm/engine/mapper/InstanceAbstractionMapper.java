package com.bpm.engine.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.model.InstanceAbstractionModel;

public class InstanceAbstractionMapper {

	
    public InstanceAbstractionModel entityToPojo(InstanceAbstraction entity) {
        ModelMapper modelMapper = new ModelMapper();
        InstanceAbstractionModel pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, InstanceAbstractionModel.class);
        }
        return pojo;
    }

    public List<InstanceAbstractionModel> entityListToPojoList(List<InstanceAbstraction> entitys) {
        ModelMapper modelMapper = new ModelMapper();
        List<InstanceAbstractionModel> pojos = new ArrayList<>();

        if (entitys != null && entitys.size()>0 ) {
            entitys.forEach(assigned -> {
                pojos.add(this.entityToPojo(assigned));
            });
        }
        return pojos;
    }


    public InstanceAbstraction pojoToEntity(InstanceAbstractionModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        InstanceAbstraction entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, InstanceAbstraction.class);
        }
        return entity;
    }
	
}
