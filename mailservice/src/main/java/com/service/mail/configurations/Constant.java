package com.service.mail.configurations;

public class Constant {

    private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
    private static String workDir = System.getProperty("user.dir");
    public static String templateFile = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates"+separador;
    public static String templateFile2 = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates";

    public static String templateFileA = workDir+separador;
    public static String templateFileB = workDir;
    public static final String MAIL_CODE_PORT = "MAIL_CODE_PORT";
    public static final String CREATE ="create";
    public static final String APPROVED ="APPROVED";
    public static final String CANCELED ="CANCELED";
    public static final String IN_PROGRESS ="IN PROGRESS";
    public static final String SEND = "SEND";
    public static final String FINALIZED = "FINALIZED";
    public static final String PAUSE = "PAUSE";

    //    public static final String CODETEMPLATE = "code-1";
//    public static final String TEMPLATENAME = "templete1.html";
//    public static final String FILENAMEHTML = "templeteSending.html";
//    public static final String FILENAMEPDF = "Tpdf.pdf";
//
//    public static final String FILENAMEFINAL = "pdf.pdf";
//    public static final String FILENAMEDOCINVOICE = "pdfI.pdf";
//
//    public static final String FILENAMEHTMLPAY = "templeteSendingPay.html";
//    public static final String FILESIGNATURE = "Signature.html";
//    public static final String FILESIGNATUREPDF = "Signature.pdf";
//    public static final String FILENAMEPDFPAY = "TallyHandyManPay.pdf";
//    public static final String FILENAME_PDF_INVOICE = "TallyHandyMan_invoice.pdf";
//    public static final String FILENAME_PDF_ESTIMATE = "TallyHandyMan_Estimate.pdf";
}


