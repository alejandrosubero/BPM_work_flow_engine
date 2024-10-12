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

    private RoleModel getRoleModel() {
    	return  RoleModel.builder()
                  .name("Developer")
                  .codeRole("w23a")
                  .description("developer").build();
    }
    
    
    @Test
    public void testUpdateThis_AllFieldsUpdated() {
        // Create original assigned model
    	employeeRole = getRoleModel();
    	assignedModel = new AssignedModel();
        assignedModel.setId(1L);
        assignedModel.setName("Original Name");
        assignedModel.setCodeEmployee("12345");
        assignedModel.setemployeeRole(employeeRole);
        assignedModel.setMail("original@mail.com");

        List<ApprovedProcessModel> originalApprovedProcesses = new ArrayList<>();
        originalApprovedProcesses.add(approvedProcessModel);
        assignedModel.setApprovedProcess(originalApprovedProcesses);

        assignedModel.setActive(false);

        // Create new assigned model with updated values
        AssignedModel newAssignedModel = new AssignedModel(
                "Updated Name",
                "54321",
                employeeRole, // employeeRole not updated
                "updated@mail.com",
                new ArrayList<>() // approvedProcess empty
        );
        newAssignedModel.setActive(true);

        // Call updateThis method
        assignedModel.updateThis(newAssignedModel);

        // Verify updates
        assertEquals(newAssignedModel.getId(), assignedModel.getId());
        assertEquals(newAssignedModel.getName(), assignedModel.getName());
        assertEquals(newAssignedModel.getCodeEmployee(), assignedModel.getCodeEmployee());
        assertEquals(employeeRole, assignedModel.getemployeeRole()); // role not updated
        assertEquals(newAssignedModel.getMail(), assignedModel.getMail());
        assertEquals(newAssignedModel.getApprovedProcess().size(), assignedModel.getApprovedProcess().size());
        assertEquals(true, assignedModel.getActive());

        // Verify no interaction with mocked employeeRole or approvedProcessModel
        verifyNoInteractions(employeeRole);
        verifyNoInteractions(approvedProcessModel);
    }

    @Test
    public void testUpdateThis_NoChanges() {
        // Create identical assigned models
        assignedModel.setId(1L);
        assignedModel.setName("Same Name");
        assignedModel.setCodeEmployee("12345");
        assignedModel.setemployeeRole(employeeRole);
        assignedModel.setMail("same@mail.com");

        List<ApprovedProcessModel> sameApprovedProcesses = new ArrayList<>();
        sameApprovedProcesses.add(approvedProcessModel);
        assignedModel.setApprovedProcess(sameApprovedProcesses);

        assignedModel.setActive(true);

        AssignedModel newAssignedModel = new AssignedModel(
                assignedModel.getName(),
                assignedModel.getCodeEmployee(),
                assignedModel.getemployeeRole(),
                assignedModel.getMail(),
                assignedModel.getApprovedProcess()
        );
        newAssignedModel.setActive(assignedModel.getActive());

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
