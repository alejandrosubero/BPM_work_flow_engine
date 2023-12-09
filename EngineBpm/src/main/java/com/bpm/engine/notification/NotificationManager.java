package com.bpm.engine.notification;

import com.bpm.engine.entitys.Assigned;
import com.bpm.engine.entitys.InstanceTask;
import com.bpm.engine.interfaces.IBaseModel;
import com.bpm.engine.model.*;
import com.bpm.engine.notification.componet.TemplateFilling;
import com.bpm.engine.notification.componet.TemplateFlexibleDataManager;
import com.bpm.engine.notification.model.EmailsModel;
import com.bpm.engine.notification.model.MailSenderModel;
import com.bpm.engine.service.AssignedService;
import com.bpm.engine.service.ParametersServices;
import com.bpm.engine.serviceImplement.RestTemplateService;
import com.bpm.engine.utility.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificationManager implements IBaseModel {

    @Autowired
    private TemplateFilling templateFilling;
    @Autowired
    private TemplateFlexibleDataManager templateFlexibleDataManager;
    @Autowired
    private ParametersServices parametersServices;
    @Autowired
    private RestTemplateService templateService;

    @Autowired
    private AssignedService assignedService;

    private Integer count = 0;

    public void taskNotification(InstanceTaskModel instanceTask, String errorStatus) {

        InstanceDataInfoModel instanceDataInfo = new InstanceDataInfoModel();
        MailSenderModel mailSenderModel = new MailSenderModel();
        EmailsModel emailsModel = new EmailsModel();
        String mailSend = null;

        instanceDataInfo.setInstanceID(instanceTask.getIdInstanceTask());
        instanceDataInfo.setInstanceTitle(instanceTask.getTask().getTitle());
        instanceDataInfo.setInstanceName(instanceTask.getName());
        instanceDataInfo.setInstanceCode(instanceTask.getTask().getCode());
        instanceDataInfo.setProcess(false);
        instanceDataInfo.setType(instanceTask.getTask().getType().getType());

        if (errorStatus == null) {
            instanceDataInfo.setState(instanceTask.getState());
        } else {
            instanceDataInfo.setState(errorStatus);
        }

        String urlMailSystem = parametersServices.findBykey(Constants.MAIL_CODE_SYSTEM).getValue();

        if (instanceTask.getassignes() != null && instanceTask.getassignes().size() > 0) {
            List<AssignedModel> assignesList = assignedService.findByTaskAssignedModel(instanceTask.getassignes());
            if(assignesList != null && assignesList.size() > 0){
                for (AssignedModel assignedModel : assignesList) {
                    mailSend = this.buildMail(instanceDataInfo, assignedModel, errorStatus);
                    ResponseEntity<String> response = templateService.sendPostRequest(urlMailSystem, mailSend);
                    this.handlerResponse(response, instanceTask);
                }
            }

        } else {
            //TODO: IMPLENET WHAT HAPPEN IF THE INSTANCEATASK DON'T HAD ASSIGNED. ERROR.
            if (instanceTask.getassignes() != null && instanceTask.getassignes().size() == 0) {
//                mailSend = this.buildMail(instanceDataInfo, instanceTask.getassignes().get(0), errorStatus);
//                ResponseEntity<String> response = templateService.sendPostRequest(urlMailSystem, mailSend);
//                this.handlerResponse(response, instanceTask);
            }
        }
    }

    private String buildMail(InstanceDataInfoModel instanceDataInfo, AssignedModel assigned, String errorStatus) {

        Gson gson = new Gson();
        MailSenderModel mailSenderModel = new MailSenderModel();
        EmailsModel emailsModel = new EmailsModel();
        String template = null;
        String mailSenderModelString = "";

        instanceDataInfo.setInstanceUserAssigned(assigned.getName());
        instanceDataInfo.setInstanceUserAssignedEmployeeCode(assigned.getCodeEmployee());
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

        String mailJson = gson.toJson(emailsModel);
        mailSenderModel = new MailSenderModel(template, mailJson);
        mailSenderModelString = gson.toJson(mailSenderModel);
        return mailSenderModelString;
    }

    private void handlerResponse(ResponseEntity<String> responseEntity, InstanceTaskModel instanceTask){
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
