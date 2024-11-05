package com.bpm.engine.relief.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bpm.engine.relief.ReliefManager;
import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.model.ReliefAssignedModel;
import com.bpm.engine.relief.strategys.TemporaryChange;

@Component
public class ReturnCommandService {

    private IReliefAssignedService reliefAssignedService;    
    private TemporaryChange temporaryChanges;
	
    

    @Autowired
	public ReturnCommandService(IReliefAssignedService reliefAssignedService, TemporaryChange temporaryChanges) {
		super();
		this.reliefAssignedService = reliefAssignedService;
		this.temporaryChanges = temporaryChanges;
	}


	public void scheduleTaskCheckReturnCommand() {

		List<ReliefAssignedModel> reliefreturnCommandList = reliefAssignedService.getReliefreturnCommand();
		
		Map<Long, Boolean>responseReturnCommand = new HashMap<>();
		
		
		if (reliefreturnCommandList != null && !reliefreturnCommandList.isEmpty()) {

			reliefreturnCommandList.parallelStream().forEach(reliefAssigned -> {
				// In this code switching the order of the input: original (userReliefCode,userCode) to ReturnCommand (userCode, userReliefCode)
				responseReturnCommand.put(
								reliefAssigned.getIdRelief(), 
								temporaryChanges.executeTemporaryChanges(reliefAssigned.getUserCode(),reliefAssigned.getUserReliefCode())
							);	
			});

			if (!responseReturnCommand.isEmpty()) {
				responseReturnCommand.entrySet().parallelStream().forEach(entry -> {
					if (entry.getValue()) {
						reliefAssignedService.updateActive(false, entry.getKey());
					}
				});
			}
		}

	}

	
	

	public void scheduleTaskWithFixedRate() {
		// This task will execute every 15 minutes
		System.out.println("Task executed at....... ....... ..... : " + new Date());
	}

  
	
	
	public Integer dateDifference(Date startDate, Date endDate) {

		// Convert to LocalDate
		LocalDate startLocalDate = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate endLocalDate = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		// Calculate the difference in days
		long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

		Integer dayBetween = (int) daysBetween;

		return dayBetween;
	}
	
	

}


