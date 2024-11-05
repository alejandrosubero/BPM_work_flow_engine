package com.bpm.engine.configurations.schedule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app")
@Component
public class AppScheduleProperties {
	
    private String cronExpressionCheckReturnCommand;

    public String getCronExpression() {
        return cronExpressionCheckReturnCommand;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpressionCheckReturnCommand = cronExpression;

    }
    
}
