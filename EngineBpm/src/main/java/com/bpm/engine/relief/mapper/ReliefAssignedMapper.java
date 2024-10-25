package com.bpm.engine.relief.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bpm.engine.entitys.Process;
import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.relief.dto.ReliefDTO;
import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.model.ReliefAssignedModel;

@Component
public class ReliefAssignedMapper {

	

	public ReliefAssignedModel toModel(ReliefAssigned entity) {
		try {
			if (entity != null) {
				return new ModelMapper().map(entity, ReliefAssignedModel.class);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error creating ReliefAssigned", e);
		}
		return null;
	}

	public ReliefAssigned toEntity(ReliefAssignedModel model) {
		try {
			if (model != null) {
				return new ModelMapper().map(model, ReliefAssigned.class);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error creating ReliefAssigned", e);
		}
		return null;
	}

	public List<ReliefAssignedModel> entityListToPojoList(List<ReliefAssigned> entitys) {
		List<ReliefAssignedModel> pojos = new ArrayList<>();
		if (entitys != null && !entitys.isEmpty()) {
			entitys.parallelStream().forEach(process -> {
				pojos.add(this.toModel(process));
			});
		}
		return pojos;
	}

	public List<ReliefAssigned> PojoListToentityList(List<ReliefAssignedModel> pojos) {
		List<ReliefAssigned> entitys = new ArrayList<>();

		if (pojos != null && !pojos.isEmpty()) {
			pojos.parallelStream().forEach(process -> {
				entitys.add(this.toEntity(process));
			});
		}
		return entitys;
	}

	
	public ReliefAssignedModel toModel(ReliefDTO dto) {
		return new ReliefAssignedModel(dto);

	}
	
	
	
}