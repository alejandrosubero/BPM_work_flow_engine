package com.bpm.engine.configurations.schedule;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bpm.engine.relief.ReliefManager;
import com.bpm.engine.relief.entity.ReliefAssigned;
import com.bpm.engine.relief.service.IReliefAssignedService;

@Component
public class returnCommandService {
	
    
    @Autowired
    private AppScheduleProperties app;
	
//    @Autowired
//    private IReliefAssignedService reliefAssignedService;
//    
//    @Autowired
//    private ReliefManager reliefManager;
	
    
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
	
	

	@Scheduled(cron = "${app.cronExpressionCheckReturnCommand}")
	public void scheduleTaskCheckReturnCommand() {

//		List<ReliefAssigned> reliefreturnCommandList = reliefAssignedService.getReliefreturnCommand();
//		
//		if(reliefreturnCommandList !=null &&  !reliefreturnCommandList.isEmpty()) {
//			
//			
//		}
		
//		reliefManager
		
		//executeTemporaryChanges(String userReliefCode, String userCode)

		System.out.println("Task executed at....... ....... ..... : " + new Date());

	}

	
	
	@Scheduled(cron = "0 0/15 * * * ?")
	public void scheduleTaskWithFixedRate() {
		// This task will execute every 15 minutes
		System.out.println("Task executed at....... ....... ..... : " + new Date());
	}

  
	
	

}

/***
	
	A CRON expression typically consists of six fields:
	
	Seconds: 0-59
	Minutes: 0-59
	Hours: 0-23
	Day of Month: 1-31
	Month: 1-12
	Day of Week: 0-7 (Sunday = 0, Sunday = 7)
	You can use special characters like *, ?, -, and / to create more flexible schedules:
	
	*: Any value
	?: No specific value
	-: Range
	/: Step
	Key Points to Remember:
	
	Time Zone: Make sure your Spring Boot application's time zone is configured correctly to ensure accurate scheduling.
	Task Complexity: For more complex tasks, consider using Spring Batch or other dedicated job scheduling frameworks.
	Error Handling: Implement robust error handling to prevent task failures from impacting your application.
	Testing: Thoroughly test your CRON-based tasks in different environments to ensure their reliability.
	By effectively using the @Scheduled annotation and understanding CRON expressions, you can automate 
	various tasks within your Spring Boot applications, improving efficiency and productivity.
 * 
 **/
