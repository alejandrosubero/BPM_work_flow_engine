package com.bpm.engine.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SystemInternalResponseModel implements Serializable {

    private Boolean condition;
    private String mensaje;
    private Object objectCarrier;
    private List<Object> objectCarrierList;

    public SystemInternalResponseModel() {
    }

    public SystemInternalResponseModel(Boolean condition, String mensaje) {
        this.condition = condition;
        this.mensaje = mensaje;
    }

    public SystemInternalResponseModel(Boolean condition) {
        this.condition = condition;
    }

    public SystemInternalResponseModel(Boolean condition, String mensaje, Object objectCarrier, List<Object> objectCarrierList) {
        this.condition = condition;
        this.mensaje = mensaje;
        this.objectCarrier = objectCarrier;
        this.objectCarrierList = objectCarrierList;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getObjectCarrier() {
        return objectCarrier;
    }

    public void setObjectCarrier(Object objectCarrier) {
        this.objectCarrier = objectCarrier;
    }

    public List<Object> getObjectCarrierList() {
        return objectCarrierList;
    }

    public void setObjectCarrierList(List<Object> objectCarrierList) {
        this.objectCarrierList = objectCarrierList;
    }

    @Override
    public String toString() {
        return "SystemInternalResponseModel{" +
                "condition=" + condition +
                ", mensaje='" + mensaje + '\'' +
                ", objectCarrier=" + objectCarrier +
                ", objectCarrierList=" + objectCarrierList +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemInternalResponseModel that = (SystemInternalResponseModel) o;
        return Objects.equals(condition, that.condition) && Objects.equals(mensaje, that.mensaje) && Objects.equals(objectCarrier, that.objectCarrier) && Objects.equals(objectCarrierList, that.objectCarrierList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, mensaje, objectCarrier, objectCarrierList);
    }
}
