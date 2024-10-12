package com.bpm.engine.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bpm.engine.entitys.Role;

@ExtendWith(MockitoExtension.class)
public class AssignedModelTest {

	@InjectMocks
	private AssignedModel assignedModel;

	@Mock
	private RoleModel employeeRole;

	@Mock
	private ApprovedProcessModel approvedProcessModel;



	private void setAssignedModelInitial() {

		List<ApprovedProcessModel> originalApprovedProcesses = new ArrayList<>();
		originalApprovedProcesses.add(approvedProcessModel);
		assignedModel = new AssignedModel();
		assignedModel.setId(1L);
		assignedModel.setName("Original Name");
		assignedModel.setCodeEmployee("12345");
		assignedModel.setemployeeRole(employeeRole);
		assignedModel.setMail("original@mail.com");
		assignedModel.setApprovedProcess(originalApprovedProcesses);
		assignedModel.setActive(false);
	}

	private AssignedModel getAssignedModeUpdate() {
		List<ApprovedProcessModel> originalApprovedProcesses = new ArrayList<>();
		originalApprovedProcesses.add(approvedProcessModel);
		return AssignedModel.builder().id(1l).name("Updated Name").codeEmployee("54321").employeeRole(employeeRole)
				.approvedProcess(originalApprovedProcesses).mail("updated@mail.com").active(true).build();
	}
	
	

	@Test
	public void testUpdateThis_AllFieldsUpdated() {

		this.setAssignedModelInitial();
		AssignedModel newAssignedModel = this.getAssignedModeUpdate();

		// Call updateThis method
		assignedModel.updateThis(newAssignedModel);

		// Verify updates
		assertEquals(newAssignedModel.getId(), assignedModel.getId());
		assertEquals(employeeRole, assignedModel.getemployeeRole()); // role not updated
		assertEquals(newAssignedModel.getApprovedProcess().size(), assignedModel.getApprovedProcess().size());
		assertEquals(true, assignedModel.getActive());

		assertEquals(newAssignedModel.getName(), assignedModel.getName());
		assertEquals(newAssignedModel.getCodeEmployee(), assignedModel.getCodeEmployee());
		assertEquals(newAssignedModel.getMail(), assignedModel.getMail());

		// Verify no interaction with mocked employeeRole or approvedProcessModel
		verifyNoInteractions(employeeRole);
		verifyNoInteractions(approvedProcessModel);
	}

	
	
	@Test
	public void testUpdateThis_NoChanges() {

		List<ApprovedProcessModel> sameApprovedProcesses = new ArrayList<>();
		sameApprovedProcesses.add(approvedProcessModel);
		
		assignedModel.setId(1L);
		assignedModel.setName("Same Name");
		assignedModel.setCodeEmployee("12345");
		assignedModel.setemployeeRole(employeeRole);
		assignedModel.setMail("same@mail.com");
		assignedModel.setApprovedProcess(sameApprovedProcesses);
		assignedModel.setActive(true);

		AssignedModel newAssignedModel = AssignedModel.builder()
				.id(1l)
				.name(assignedModel.getName())
				.codeEmployee(assignedModel.getCodeEmployee())
				.employeeRole(assignedModel.getemployeeRole())
				.mail(assignedModel.getMail())
				.approvedProcess(assignedModel.getApprovedProcess())
				.active(true)
				.build();

		

		// Call updateThis method
		assignedModel.updateThis(newAssignedModel);

		// Verify no changes
		assertEquals(assignedModel.getId(), 1L);
		assertEquals(assignedModel.getName(), "Same Name");
		assertEquals(assignedModel.getCodeEmployee(), "12345");
		assertEquals(assignedModel.getemployeeRole(), employeeRole);
		assertEquals(assignedModel.getMail(), "same@mail.com");
		assertEquals(assignedModel.getApprovedProcess().size(), 1);
		assertEquals(assignedModel.getActive(), true);

		// Verify no interaction with mocked objects
		verifyNoInteractions(employeeRole);
		verifyNoInteractions(approvedProcessModel);
	}
}

//assertNotEquals