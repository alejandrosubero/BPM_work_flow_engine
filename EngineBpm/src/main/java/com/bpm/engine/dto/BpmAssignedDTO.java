package com.bpm.engine.dto;

import com.bpm.engine.model.AssignedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BpmAssignedDTO {

    private AssignedModel assigned;
    private List<String> codeTaskOrProces = new ArrayList<>();

    public BpmAssignedDTO() {
    }

    public AssignedModel getAssigned() {
        return assigned;
    }

    public void setAssigned(AssignedModel assigned) {
        this.assigned = assigned;
    }

    public List<String> getCodeTaskOrProces() {
        return codeTaskOrProces;
    }

    public void setCodeTaskOrProces(List<String> codeTaskOrProces) {
        this.codeTaskOrProces = codeTaskOrProces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BpmAssignedDTO that = (BpmAssignedDTO) o;
        return Objects.equals(assigned, that.assigned) && Objects.equals(codeTaskOrProces, that.codeTaskOrProces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assigned, codeTaskOrProces);
    }
}
