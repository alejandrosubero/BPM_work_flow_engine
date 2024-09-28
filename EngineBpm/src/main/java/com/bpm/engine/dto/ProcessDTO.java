package com.bpm.engine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.model.ProcessModel;
import com.bpm.engine.model.StageModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String Title;
    private String state;
    private String Code;
    private Boolean visible;
    private Boolean global;
    
    @Builder.Default
    private List<StageDTO> stages = new ArrayList<>();

  

    public ProcessDTO(ProcessModel process) {
        if( process.getId_process()!=null)
        this.id = process.getId_process();

        if( process.getName() !=null)
        this.name = process.getName();

        if( process.getName() !=null)
        Title = process.getName();

        if( process.getState() !=null)
        this.state = process.getState();

        if( process.getProcesCode() !=null)
        Code = process.getProcesCode();

        if( process.getVisible()!=null)
        this.visible = process.getVisible();

        if( process.getGlobal()!=null)
        this.global = process.getGlobal();

       if( process.getstages() != null && process.getstages().size() >0)
           this.stages = this.getStageList(process.getstages());
    }


    private List<StageDTO> getStageList(List<StageModel> listaStage){
        List<StageDTO> stageDTOList = new ArrayList<>();
        for (StageModel stg: listaStage) {
            stageDTOList.add(new StageDTO(stg));
        }
        return stageDTOList;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public List<StageDTO> getStages() {
        return stages;
    }

    public void setStages(List<StageDTO> stages) {
        this.stages = stages;
    }
}
