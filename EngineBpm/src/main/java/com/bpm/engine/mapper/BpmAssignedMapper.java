package com.bpm.engine.mapper;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BpmAssignedMapper {


    public BpmAssignedModel entityToPojo(BpmAssigned entity) {
        ModelMapper modelMapper = new ModelMapper();
        BpmAssignedModel pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, BpmAssignedModel.class);
        }
        return pojo;
    }

    public List<BpmAssignedModel> entityListToPojoList(List<BpmAssigned> entitys) {
        ModelMapper modelMapper = new ModelMapper();
        List<BpmAssignedModel> pojos = new ArrayList<>();

        if (entitys != null && entitys.size()>0 ) {
            entitys.forEach(assigned -> {
                pojos.add(this.entityToPojo(assigned));
            });
        }
        return pojos;
    }


    public BpmAssigned pojoToEntity(BpmAssignedModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        BpmAssigned entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, BpmAssigned.class);
        }
        return entity;
    }

}
