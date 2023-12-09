package com.js.employee.mapper;

import com.js.employee.entitys.Cargo;
import com.js.employee.entitys.ComputerSheet;
import com.js.employee.pojo.CargoPojo;
import com.js.employee.pojo.ComputerSheetPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComputerSheetMapper {

    public ComputerSheetPojo entityToPojo(ComputerSheet entity) {
        ModelMapper modelMapper = new ModelMapper();
        ComputerSheetPojo pojo = null;
        if ( entity != null) {
            pojo = modelMapper.map(entity, ComputerSheetPojo.class);
        }
        return  pojo;
    }

    public ComputerSheet pojoToEntity(ComputerSheetPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        ComputerSheet entity = null;

        if ( pojo != null) {
            entity = modelMapper.map(pojo, ComputerSheet.class);
        }
        return  entity;
    }

    public List<ComputerSheetPojo> listEntityToListPojo(List<ComputerSheet> entitys) {
        List<ComputerSheetPojo> listPojo= new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        ComputerSheetPojo pojo = null;
        if ( entitys != null && entitys.size() >0) {
            entitys.stream().forEach(computerSheet -> {
                listPojo.add(this.entityToPojo(computerSheet));
            });
        }
        return  listPojo;
    }
}
