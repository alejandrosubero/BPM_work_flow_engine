package com.service.mail.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_Parameters_generator")
    @SequenceGenerator(name = "sequence_mat_id_Parameters_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idParameter", updatable = true, nullable = false, length = 25)
    private Long idParameter;

    @Column(name = "key", updatable = true, nullable = true, length = 500)
    private String key;

    @Column(name = "value", updatable = true, nullable = true, length = 4000)
    private String value;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "note", updatable = true, nullable = true, length = 2000)
    private String note;

    @Column(name = "active", updatable = true, nullable = true, length = 10)
    private Boolean active;

    public Parameters() {
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
        Parameters that = (Parameters) o;
        return Objects.equals(idParameter, that.idParameter) && Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(description, that.description) && Objects.equals(note, that.note) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParameter, key, value, description, note, active);
    }
}

