package com.bpm.engine.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bpm.engine.entitys.InstanceAbstraction;
import com.bpm.engine.models.InstanceAbstractionModel;

@Component
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
        List<InstanceAbstractionModel> pojos = new ArrayList<>();
        if (entitys != null && entitys.size()>0 ) {
            entitys.parallelStream().forEach(assigned -> {
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
