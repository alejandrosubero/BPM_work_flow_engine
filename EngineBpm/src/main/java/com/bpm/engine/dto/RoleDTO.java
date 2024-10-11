package com.bpm.engine.dto;

import java.io.Serializable;

import com.bpm.engine.models.RoleModel;
import com.google.gson.annotations.SerializedName;

public class RoleDTO implements Serializable {
	
    private static final long serialVersionUID = 454L;

    @SerializedName("idRole")
    private Long idRole;

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    @SerializedName("codeRole")
    private String codeRole;
    
    
    
    public RoleDTO() {
    }

    public RoleDTO(Long id, String code, String name, String description) {
        this.idRole = id;
        this.codeRole = code;
        this.name = name;
        this.description = description;
    }

    public RoleDTO(RoleModel ro) {
        this.idRole = ro.getIdRole();
        this.codeRole = ro.getCodeRole();
        this.name = ro.getName();
        this.description = ro.getDescription();
    }

    public Long getId() {
        return idRole;
    }

    public void setId(Long id) {
        this.idRole = id;
    }

    public String getCode() {
        return codeRole;
    }

    public void setCode(String code) {
        this.codeRole = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
