package com.bpm.engine.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class AssignedDTO {

    
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("codeEmployee")
    private String codeEmployee;

    @SerializedName("employeeRole")
    private RoleDTO employeeRole;

    @SerializedName("mail")
    private String mail;
    

    public AssignedDTO() {
    }

    public AssignedDTO(String name, String codeEmployee, RoleDTO employeeRole, String mail) {
        this.name = name;
        this.codeEmployee = codeEmployee;
        this.employeeRole = employeeRole;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(String codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public RoleDTO getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(RoleDTO employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignedDTO that = (AssignedDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(codeEmployee, that.codeEmployee) && Objects.equals(employeeRole, that.employeeRole) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, codeEmployee, employeeRole, mail);
    }
}
