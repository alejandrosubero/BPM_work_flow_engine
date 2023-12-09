package com.bpm.engine.notification.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MailSenderModel {


    private String port;

    private String template;

    private Boolean active;

    private String wasSend;

    private String email;// en esta variable se guarda el objeto EmailsModel en String

    private String attachments;

    private Date createDay;

    private EmailsModel emailsModel;

    private List<String> adjuntos = new ArrayList<String>();

    private String company;

    private List<AttachmentModel> ListAttachments = new ArrayList<AttachmentModel>();

    private EmailDataConfigModel Emailconfiguration;

    public MailSenderModel() {
        this.setWasSend("N");
        this.setActive(true);
    }

    public MailSenderModel(String template, String email) {

        if (null != template)
            this.template = template;

        if (null != email)
            this.email = email;


        this.setWasSend("N");
        this.setActive(true);

    }

    public MailSenderModel(String template, String email, String attachments) {

        if (null != template)
            this.template = template;

        if (null != email)
            this.email = email;

        if (null != attachments)
            this.attachments = attachments;

        this.setWasSend("N");
        this.setActive(true);

    }

    public MailSenderModel(String port, String template, String email, String attachments, Date createDay, EmailsModel emailsModel, List<AttachmentModel> listAttachments) {
        if (null != port)
            this.port = port;

        if (null != template)
            this.template = template;

        if (null != email)
            this.email = email;

        if (null != attachments)
            this.attachments = attachments;

        if (null != createDay)
            this.createDay = createDay;

        if (null != emailsModel)
            this.emailsModel = emailsModel;

        this.setWasSend("N");
        this.setActive(true);

        if (null != listAttachments && listAttachments.size() > 0)
            ListAttachments = listAttachments;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getWasSend() {
        return wasSend;
    }

    public void setWasSend(String wasSend) {
        this.wasSend = wasSend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public EmailsModel getEmailsModel() {
        return emailsModel;
    }

    public void setEmailsModel(EmailsModel emailsModel) {
        this.emailsModel = emailsModel;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<AttachmentModel> getListAttachments() {
        return ListAttachments;
    }

    public void setListAttachments(List<AttachmentModel> listAttachments) {
        ListAttachments = listAttachments;
    }

    public EmailDataConfigModel getEmailconfiguration() {
        return Emailconfiguration;
    }

    public void setEmailconfiguration(EmailDataConfigModel emailconfiguration) {
        Emailconfiguration = emailconfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailSenderModel that = (MailSenderModel) o;
        return Objects.equals(port, that.port) && Objects.equals(template, that.template) && Objects.equals(active, that.active) && Objects.equals(wasSend, that.wasSend) && Objects.equals(email, that.email) && Objects.equals(attachments, that.attachments) && Objects.equals(createDay, that.createDay) && Objects.equals(emailsModel, that.emailsModel) && Objects.equals(company, that.company) && Objects.equals(ListAttachments, that.ListAttachments) && Objects.equals(Emailconfiguration, that.Emailconfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, template, active, wasSend, email, attachments, createDay, emailsModel, company, ListAttachments, Emailconfiguration);
    }
}
