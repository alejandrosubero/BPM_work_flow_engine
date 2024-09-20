package com.bpm.engine.utility;

public class Constants {
	
    public static final String TYPE_PROCESS ="PROCESS";
    public static final String TYPE_STAGE="STAGE";
    public static final String TYPE_TASK ="TASK";
    public static final String TYPE_INSTANCE_PROCESS ="INSTANCE_PROCESS";
    public static final String TYPE_INSTANCE_TASK = "INSTANCE_TASK";
    public static final String TYPE_INSTANCE_STAGE = "INSTANCE_STAGE";
    public static final String SYSTEM_STATE_OK ="OK";

    // PATH ...
    private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
    private static String workDir = System.getProperty("user.dir");
    public static String templateFile = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates"+separador;
    public static String templateFile2 = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates";
    public static String pathTemporalFile = workDir+separador;
    public static final String TEMPLATENAME = "templete1.html";
    public static final String FILENAMEHTML = "templeteSending.html";
    public static final String CODE_TEMPLATE = "code-1";
    public static final String SYSTEM_CODE_TEMPLATE = "SYSTEM_CODE_TEMPLATE";
    public static final String MAIL_CODE_PORT = "MAIL_CODE_PORT";
    public static final String MAIL_CODE_SYSTEM = "MAIL_CODE_SYSTEM";
    public static final String MAIL_CODE_BPM_SYSTEM = "MAIL_CODE_BPM_SYSTEM";
    public static final String MAIL_CODE_ERROR_NOTIFICATION = "MAIL_CODE_ERROR_NOTIFICATION";

    public static final String CREATE = "The new instance @type@ @title@ was created. ";
    public static final String ASSIGNED = "You have a new @type@ @title@ @instanceId@ was assigned";
    public static final String ASSIGNED_R = "The @type@ @title@ @instanceId@ was assigned";
    public static final String ASSIGNED_S = "The @type@ @title@ @instanceId@ request was sent successfully.";
    public static final String WAITING_FOR_ACTION ="You have assigned a @type@ @instanceId@ @title@ pending for actions.";
    public static final String STARTED = "The @userName@ received the @type@ @title@ @instanceId@ successfully.";
    public static final String RUNNING ="The @type@ @instanceId@ @title@ is in progress.";
    public static final String IN_PROGRESS ="The @type@ @instanceId@ @title@ is in progress.";
    public static final String COMPLETE ="The request @type@ @title@ @instanceId@ was processed.";
    public static final String PAUSE ="The @type@, ID: @instanceId@, title: @title@, was pause successfully.";
    public static final String STOP ="The @type@, ID: @instanceId@, title: @title@, was stop successfully.";
    public static final String CANCELING ="The canceling of @type@ ID: @instanceId@, title: @title@,  begins and you will be notified of its resolution.";
    public static final String CANCEL ="The @type@, ID: @instanceId@, title: @title@, was cancelled successfully.";
    public static final String ACTIVE ="";

    public static final String CREATE_P = "A new process was created for @title@.";
    public static final String STARTED_P = "The new instance @type@ @title@ was created.";
    public static final String STOP_P ="The process, ID: @instanceId@ , title: @title@, was stop successfully.";
    public static final String CANCEL_P ="The process, ID: @instanceId@ , title: @title@ , was cancel successfully.";
    public static final String ACTIVE_P ="";

    public static final String ERROR_ = "ERROR IN THE @type@ , ID: @instanceId@ , title: @title@";
    public static final String ERROR_NOTIFICATION = "ERROR IN NOTIFICATION OFF @type@ , ID: @instanceId@ , title: @title@";
    public static final String SUBJECT = "@idInstance@ @title@ Automatic Response - BPM System";
    public static final String CRITICAL_ERROR = "CRITICAL ERROR IN  @type@ , ID: @instanceId@ , title: @title@";
    public static final String IN_ERROR ="IN ERROR  @type@ , ID: @instanceId@ , title: @title@";

//    ERROR_, ERROR_NOTIFICATION, CRITICAL_ERROR, IN_ERROR
}





