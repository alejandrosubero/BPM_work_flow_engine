package com.bpm.engine.notification.model;

import java.util.Arrays;
import java.util.Objects;

public class AttachmentModel {

    private Long idAttachment;
    private  byte[] fileAttachment;
    private String fileName;

    public AttachmentModel() {
    }

    public AttachmentModel(byte[] fileAttachment, String fileName) {
        this.fileAttachment = fileAttachment;
        this.fileName = fileName;
    }

    public AttachmentModel(Long idAttachment, byte[] fileAttachment, String fileName) {
        this.idAttachment = idAttachment;
        this.fileAttachment = fileAttachment;
        this.fileName = fileName;
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public byte[] getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(byte[] fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachmentModel that = (AttachmentModel) o;
        return Objects.equals(idAttachment, that.idAttachment) && Arrays.equals(fileAttachment, that.fileAttachment) && Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idAttachment, fileName);
        result = 31 * result + Arrays.hashCode(fileAttachment);
        return result;
    }
}
