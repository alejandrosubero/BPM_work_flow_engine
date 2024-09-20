package com.bpm.engine.controller.client;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.managers.ConectBpmToEmployeeService;
import com.bpm.engine.managers.InstanceProcessManager;
import com.bpm.engine.managers.ProcessManager;
import com.bpm.engine.mapper.MapperEntityRespone;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.service.BpmAssignedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/engineBpm")
public class EngineBpmController {

	@Autowired
	ConectBpmToEmployeeService conectBpmToEmployeeService;

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	@Autowired
	private ProcessManager processManager;

	@Autowired
	private InstanceProcessManager instanceProcessManager;

	
	
	

//		 http://localhost:1111/bpm/engineBpm/create/instance/process
	@PostMapping("/create/instance/process")
	private ResponseEntity<EntityRespone> createInstanceProcess(@RequestBody SystemRequest systemRequest) {
		try {
			
			if(systemRequest != null && systemRequest.getCodeTask() != null) {
				EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceProcessManager.createInstanceProcess(systemRequest));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}
			
			return new ResponseEntity<EntityRespone>( mapperEntityRespone.setEntityResponT("Error", "","Call which a null Object" ), HttpStatus.BAD_REQUEST);
			
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}


//			http://localhost:1111/bpm/engineBpm/create/process
	@PostMapping("/create/process")
	private ResponseEntity<EntityRespone> createProcess(@RequestBody ProcessModel process) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(processManager.createProcess(process));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("user/name/{userName}")
	private ResponseEntity<EntityRespone> findEmployeeByUserName (@PathVariable("userName") String userName) {
		try {
			return new ResponseEntity<EntityRespone>(
					mapperEntityRespone.setEntityRespon(
							conectBpmToEmployeeService.getAssignedUserName(userName), "ok"), HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(
					null, "Fail error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/start")
	public String startTest() {
		return " <h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>"+ 
		"<br>" + 

		"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>"; 
	}
	}
