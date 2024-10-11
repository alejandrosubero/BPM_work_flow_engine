package com.bpm.engine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bpm.engine.models.ProcessModel;
import com.bpm.engine.models.StageModel;
import com.bpm.engine.utility.InstanOf;

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
    private String userCreate;
    private String instanceOf;
    
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
        
        this.instanceOf = InstanOf.PROCESS.getValue();

       if( process.getstages() != null && !process.getstages().isEmpty())
           this.stages = this.getStageList(process.getstages());
    }


    private List<StageDTO> getStageList(List<StageModel> listaStage){
        List<StageDTO> stageDTOList = new ArrayList<>();
        listaStage.parallelStream().forEach(stage->  stageDTOList.add(new StageDTO(stage)));
//        for (StageModel stg: listaStage) {
//            stageDTOList.add(new StageDTO(stg));
//        }
        return stageDTOList;
    }

    
}
