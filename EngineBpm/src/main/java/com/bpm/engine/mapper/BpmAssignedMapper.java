package com.bpm.engine.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bpm.engine.entitys.BpmAssigned;
import com.bpm.engine.model.BpmAssignedModel;

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

		List<BpmAssignedModel> pojos = new ArrayList<>();

		if (entitys != null && !entitys.isEmpty()) {

			pojos = entitys == null ? new ArrayList<>()
					: entitys.stream().map(this::entityToPojo).collect(Collectors.toList());

			// if (entitys != null && entitys.size()>0 ) {
			// entitys.forEach(assigned -> {
			// pojos.add(this.entityToPojo(assigned));
			// });
			// }

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
