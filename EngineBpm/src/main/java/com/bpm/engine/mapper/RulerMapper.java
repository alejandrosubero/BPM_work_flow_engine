package com.bpm.engine.mapper;

import com.bpm.engine.entitys.Role;
import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.model.RoleModel;
import com.bpm.engine.model.RulerModel;
import org.modelmapper.ModelMapper;

public class RulerMapper {

    public RulerModel entityToPojo(Ruler entity) {
        ModelMapper modelMapper = new ModelMapper();
        RulerModel pojo = null;
        if (entity != null) {
            pojo = modelMapper.map(entity, RulerModel.class);
        }
        return pojo;
    }

    public Ruler pojoToEntity(RulerModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Ruler entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, Ruler.class);
        }
        return entity;
    }

}
