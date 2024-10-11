package com.bpm.engine.models;

import java.util.Objects;

public class TemplateFlexibleDataModel {

    private Long idTemplateFlexible;
    private String field;
    private String value;

    public TemplateFlexibleDataModel() {
    }

    public static TemplateFlexibleDataModel instanceTemplateFlexibleData(String field, String value){
        return new TemplateFlexibleDataModel(field,value);
    }

    public TemplateFlexibleDataModel(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public Long getIdTemplateFlexible() {
        return idTemplateFlexible;
    }

    public void setIdTemplateFlexible(Long idTemplateFlexible) {
        this.idTemplateFlexible = idTemplateFlexible;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateFlexibleDataModel that = (TemplateFlexibleDataModel) o;
        return Objects.equals(field, that.field) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value);
    }
}
