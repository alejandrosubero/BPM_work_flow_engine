package com.service.mail.pojo;


import java.util.Objects;

public class ParametersPojo {

    private Long idParameter;
    private String key;
    private String value;
    private String description;
    private String note;
    private Boolean active;

    public ParametersPojo() {
    }

    public Long getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Long idParameter) {
        this.idParameter = idParameter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametersPojo that = (ParametersPojo) o;
        return Objects.equals(idParameter, that.idParameter) && Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(description, that.description) && Objects.equals(note, that.note) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParameter, key, value, description, note, active);
    }
}