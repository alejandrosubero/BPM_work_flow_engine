package com.bpm.engine.controller;
import com.bpm.engine.componets.ConectBpmToEmployeeService;
import com.bpm.engine.componets.InstanceProcessManager;
import com.bpm.engine.componets.ProcessManager;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.mapper.MapperEntityRespone;
import com.bpm.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/EngineBpm")
public class EngineBpmController {

	@Autowired
	ConectBpmToEmployeeService conectBpmToEmployeeService;

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	@Autowired
	private ProcessManager processManager;

	@Autowired
	private InstanceProcessManager instanceProcessManager;


	@PostMapping("/create/instance/process")
	private ResponseEntity<EntityRespone> createInstanceProcess(@RequestBody SystemRequest systemRequest) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(
					instanceProcessManager.createInstanceProcess(systemRequest));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

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
