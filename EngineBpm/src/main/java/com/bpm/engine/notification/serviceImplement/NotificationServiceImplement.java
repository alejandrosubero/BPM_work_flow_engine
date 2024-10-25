package com.bpm.engine.notification.serviceImplement;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bpm.engine.interfaces.IBaseModel;
import com.bpm.engine.models.AssignedModel;
import com.bpm.engine.models.InstanceAbstractionModel;
import com.bpm.engine.models.InstanceDataInfoModel;
import com.bpm.engine.models.TemplateFlexibleDataModel;
import com.bpm.engine.notification.componet.TemplateFilling;
import com.bpm.engine.notification.componet.TemplateFlexibleDataManager;
import com.bpm.engine.notification.model.EmailsModel;
import com.bpm.engine.notification.model.MailSenderModel;
import com.bpm.engine.notification.services.NotificationService;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.ParametersServices;
import com.bpm.engine.serviceImplement.RestTemplateService;
import com.bpm.engine.utility.Constants;
import com.google.gson.Gson;

@Service
public class NotificationServiceImplement implements NotificationService, IBaseModel {

	    private TemplateFilling templateFilling;
	   
	    private TemplateFlexibleDataManager templateFlexibleDataManager;
	    
	    private ParametersServices parametersServices;
	    
	    private RestTemplateService templateService;
	   
	    private AssignedService assignedService;

	    private Integer count = 0;

	    
	    @Autowired
	    public NotificationServiceImplement(TemplateFilling templateFilling, TemplateFlexibleDataManager templateFlexibleDataManager,
				ParametersServices parametersServices, RestTemplateService templateService,AssignedService assignedService) {
			this.templateFilling = templateFilling;
			this.templateFlexibleDataManager = templateFlexibleDataManager;
			this.parametersServices = parametersServices;
			this.templateService = templateService;
			this.assignedService = assignedService;
		}

	    
		@Override
		public void taskNotification(InstanceAbstractionModel instanceTask, String errorStatus) {

	        InstanceDataInfoModel instanceDataInfo = new InstanceDataInfoModel();

	        String mailSend = null;

	        instanceDataInfo.setInstanceID(instanceTask.getIdInstance());
	        instanceDataInfo.setInstanceTitle(instanceTask.getTitle());
	        instanceDataInfo.setInstanceName(instanceTask.getName());
	        instanceDataInfo.setInstanceCode(instanceTask.getCodeReferent());
	        instanceDataInfo.setProcess(false);
	        instanceDataInfo.setType(instanceTask.getType().getType());

	        if (errorStatus == null) {
	            instanceDataInfo.setState(instanceTask.getStatus());
	        } else {
	            instanceDataInfo.setState(errorStatus);
	        }

	        String urlMailSystem = parametersServices.findBykey(Constants.MAIL_CODE_SYSTEM).getValue();

	        
	        if (instanceTask.getUserWorked()!= null || instanceTask.getUserCreateInstance() != null ) {
	        	AssignedModel assignedModel = null;
	        
	        	if (instanceTask.getUserCreateInstance() != null ) {
	        		assignedModel = assignedService.findByCodeEmployeeAndActive(instanceTask.getUserCreateInstance(), true);
	        	} else {
	        		  assignedModel = assignedService.findByCodeEmployeeAndActive(instanceTask.getUserWorked(), true);
	        	}
	           
	            
	            if(assignedModel != null){
	               mailSend = this.buildMail(instanceDataInfo, assignedModel, errorStatus);
	               ResponseEntity<String> response = templateService.sendPostRequest(urlMailSystem, mailSend);
	               this.handlerResponse(response, instanceTask);
	            }
	        } 
	    }
	    
	    private EmailsModel generateEmailsModel(InstanceDataInfoModel instanceDataInfo, AssignedModel assigned, String errorStatus) {
	    	 EmailsModel emailsModel = new EmailsModel();
	    	 String template = null;
	    	    List<TemplateFlexibleDataModel> systemTemplateFlexibleData = this.templateFlexibleDataManager.getSystemTemplateFlexibleData(instanceDataInfo);
	            template = templateFilling.fillTemplate(Constants.SYSTEM_CODE_TEMPLATE, systemTemplateFlexibleData);
	            
	            if (template != null) {
	                emailsModel.setBody(template);
	            }
	            
	            String mailSystem = parametersServices.findBykey(Constants.MAIL_CODE_BPM_SYSTEM).getValue();
	            emailsModel.setFrom(mailSystem);

	            if (errorStatus == null) {
	                emailsModel.setTo(Arrays.asList(assigned.getMail()));
	            } else {
	                String mailErrorSystem = parametersServices.findBykey(Constants.MAIL_CODE_ERROR_NOTIFICATION).getValue();
	                emailsModel.setTo(Arrays.asList(mailErrorSystem));
	            }

	            String subject = Constants.SUBJECT;
	            subject = subject.replace("@idInstance@", instanceDataInfo.getInstanceID().toString());
	            subject = subject.replace("@title@", instanceDataInfo.getInstanceTitle());
	            emailsModel.setSubject(subject);

	            if (errorStatus != null) {
	                subject = this.stringEnsamble(Arrays.asList(subject, " IN ", errorStatus));
	            }
	            
	            return emailsModel;
	    }
	    

	    private String buildMail(InstanceDataInfoModel instanceDataInfo, AssignedModel assigned, String errorStatus) {

	        Gson gson = new Gson();
	        MailSenderModel mailSenderModel = new MailSenderModel();
	        String mailSenderModelString = "";

	        instanceDataInfo.setInstanceUserAssigned(assigned.getName());
	        instanceDataInfo.setInstanceUserAssignedEmployeeCode(assigned.getCodeEmployee());
	        
	        EmailsModel emailsModel = generateEmailsModel(instanceDataInfo,assigned,errorStatus);
	        
	        String mailJson = gson.toJson(emailsModel);
	        
	        mailSenderModel = new MailSenderModel(emailsModel.getBody(), mailJson);
	        mailSenderModelString = gson.toJson(mailSenderModel);
	        return mailSenderModelString;
	    }

	    
	    private void handlerResponse(ResponseEntity<String> responseEntity, InstanceAbstractionModel instanceTask){
	        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
	            if (count == 0) {
	                this.taskNotification(instanceTask, null);
	                count++;
	            } else {
	                this.taskNotification(instanceTask, Constants.ERROR_NOTIFICATION);
	            }
	        }
	        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
	        }
	    }


		

}
