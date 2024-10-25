package com.bpm.engine.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.SystemRequest;
import com.bpm.engine.managers.ConectBpmToEmployeeService;
import com.bpm.engine.managers.InstanceProcessManager;
import com.bpm.engine.managers.InstanceProcessManagerDTO;
import com.bpm.engine.managers.ProcessManager;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.models.ProcessModel;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/engineBpm")
public class EngineBpmController {

	

	@Autowired
	private MapperEntityRespone mapperEntityRespone;

	@Autowired
	private InstanceProcessManager instanceProcessManager;
	
	@Autowired
	private InstanceProcessManagerDTO instanceProcessManagerDTO;
	

//	http://localhost:1111/bpm/engineBpm/create/instance/process
	@PostMapping("/create/instance/process")
	private ResponseEntity<EntityRespone> createInstanceProcess(@RequestBody SystemRequest systemRequest) {
		try {

			if (systemRequest != null && systemRequest.getCodeTask() != null) {
				EntityRespone entityRespone = mapperEntityRespone
						.setEntityTobj(instanceProcessManager.createInstanceProcess2(systemRequest));
				return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
			}

			return new ResponseEntity<EntityRespone>(
					mapperEntityRespone.setEntityResponT("Error", "", "Call which a null Object"),
					HttpStatus.BAD_REQUEST);

		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",
					e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

//	http://localhost:1111/bpm/engineBpm/create/process
	@PostMapping("/create/process")
	private ResponseEntity<EntityRespone> createProcess(@RequestBody ProcessModel process) {
		try {
			EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(instanceProcessManager.createProcess(process));
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
		} catch (DataAccessException e) {
			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error",
					e.getMessage());
			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
		}
	}

	
//	http://localhost:1111/bpm/engineBpm/get/process/user
	@PostMapping("/get/process/user")
	private ResponseEntity<EntityRespone> getInstanceOfEmpleado(@RequestBody SystemRequest systemRequest) {

		EntityRespone entityRespone = null;
		try {

			if (systemRequest == null) {
				return ResponseEntity.badRequest().body(mapperEntityRespone.setEntityResponBadRequest("the systemRequest cannot be empty or null..."));
			}

			entityRespone = mapperEntityRespone.setEntityT(instanceProcessManagerDTO.getInstancesDTO(systemRequest));

			return ResponseEntity.ok(entityRespone);

		} catch (DataAccessException e) {
			entityRespone = mapperEntityRespone.setEntityResponT(null,
					"Faill in the system (Error tray to find user process...)", e.getMessage());
			return ResponseEntity.badRequest().body(entityRespone);
		}

	}
	

	@GetMapping("/start/chek/server/bpm")
	public String startTest() {
		return " <h1>!!!!!!!!!!!!!!!!!  BPM SERVER IS RUNING... !!!!!!!!!!!!</h1>" + "<br>" +

				"<h2> !!!!!!!!!!! Estoy funcionando !!!!!!!!! </h2>";
	}

//	@GetMapping("user/name/{userName}")
//	private ResponseEntity<EntityRespone> findEmployeeByUserName (@PathVariable("userName") String userName) {
//		try {
//			return new ResponseEntity<EntityRespone>(
//					mapperEntityRespone.setEntityRespon(
//							conectBpmToEmployeeService.getAssignedUserName(userName), "ok"), HttpStatus.OK);
//		} catch (DataAccessException e) {
//			EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(
//					null, "Fail error", e.getMessage());
//			return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//		}
//	}

}
