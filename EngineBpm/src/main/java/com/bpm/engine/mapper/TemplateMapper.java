package com.bpm.engine.mapper;

import com.bpm.engine.entitys.Template;
import com.bpm.engine.model.TemplateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TemplateMapper {

    public Template pojoToEntity(TemplateModel pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Template entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, Template.class);
        }
        return entity;
    }

    public TemplateModel entityToPojo(Template entity) {
        ModelMapper modelMapper = new ModelMapper();
        TemplateModel pojo = null;
        if (entity != null) {
            pojo = modelMapper.map(entity, TemplateModel.class);
        }
        return pojo;
    }

}
