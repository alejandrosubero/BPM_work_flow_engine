package com.bpm.engine.componets;

import com.bpm.engine.dto.BpmAssignedDTO;
import com.bpm.engine.mapper.AssignedMapper;
import com.bpm.engine.model.AssignedModel;
import com.bpm.engine.model.BpmAssignedModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.BpmAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BpmAssignedManager {

    @Autowired
    private AssignedService assignedService;

    @Autowired
    private AssignedMapper assignedMapper;

    @Autowired
    private BpmAssignedService bpmAssignedService;


    public Boolean saveOrUpdateBpmAssigned(BpmAssignedDTO assignedBPM) {

        AssignedModel assigned = null;
        Boolean response = false;
        try {

            if (assignedBPM.getAssigned() != null) {

                assigned = assignedService.findByCodeEmployeeAndActive(assignedBPM.getAssigned().getCodeEmployee(), true);

                if (assignedBPM.getAssigned().getId() != null && assigned != null &&
                        assignedBPM.getAssigned().getId() == assigned.getId() &&
                        !assigned.equals(assignedBPM.getAssigned())
                ) {
                    assignedService.saveOrUpdateAssigned(
                            assignedMapper.pojoToEntity(assignedBPM.getAssigned()));
                    assigned = assignedBPM.getAssigned();
                }

                Long idassigned = assigned.getId();

                assignedBPM.getCodeTaskOrProces().stream().forEach(codeTask ->
                        bpmAssignedService.saveOrUpdateBpmAssigned(
                                new BpmAssignedModel(idassigned, codeTask))
                );
                response = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return response;
    }

}
