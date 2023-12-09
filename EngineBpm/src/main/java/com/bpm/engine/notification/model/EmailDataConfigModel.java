package com.bpm.engine.notification.model;

import java.util.Objects;

public class EmailDataConfigModel {

    private String host;
    private Long idEmailConfig;
    private String port;
    private String mailUsername;
    private String mailPassword;

    public EmailDataConfigModel() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getIdEmailConfig() {
        return idEmailConfig;
    }

    public void setIdEmailConfig(Long idEmailConfig) {
        this.idEmailConfig = idEmailConfig;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDataConfigModel that = (EmailDataConfigModel) o;
        return Objects.equals(host, that.host) && Objects.equals(idEmailConfig, that.idEmailConfig) && Objects.equals(port, that.port) && Objects.equals(mailUsername, that.mailUsername) && Objects.equals(mailPassword, that.mailPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, idEmailConfig, port, mailUsername, mailPassword);
    }
}
