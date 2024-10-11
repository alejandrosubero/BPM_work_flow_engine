package com.bpm.engine.serviceImplement;

import com.bpm.engine.dto.AssignedDTO;
import com.bpm.engine.dto.ResponseAssignedDTO;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.RoleModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;


@Service
public class ResponseAssignedDTOService {

	   public List<AssignedDTO> extractEntitiesFromJson(String json) {
	        Gson gson = new Gson();
	        
	        Type responseType = new TypeToken<ResponseAssignedDTO>() {}.getType();

	        ResponseAssignedDTO response = gson.fromJson(json, responseType);

	        return response.getEntidades();
	    }
	   
	   
	   public List<AssignedModel> entitiesFromJson(String json){
		   
		   List<AssignedDTO> listAssignedDTO = this.extractEntitiesFromJson(json);
		   List<AssignedModel> entities = new ArrayList<>();

		   if(!listAssignedDTO.isEmpty()) {
			   
			   listAssignedDTO.forEach(AssignedDTO -> {
				   RoleModel role = new RoleModel(AssignedDTO.getEmployeeRole().getDescription(), AssignedDTO.getEmployeeRole().getName(), AssignedDTO.getEmployeeRole().getCode());
				   entities.add(new AssignedModel (AssignedDTO.getName(), AssignedDTO.getCodeEmployee(), role, AssignedDTO.getMail()));	   
			   });
		   }
		   
		   return entities;
	   }
	
}
