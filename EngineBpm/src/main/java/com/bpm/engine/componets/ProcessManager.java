package com.bpm.engine.componets;

import com.bpm.engine.utility.Constants;
import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.dto.ProcessDTO;
import com.bpm.engine.entitys.ControlProcessReferent;
import com.bpm.engine.interfaces.RadomCode;
import com.bpm.engine.mapper.MapperEntityRespone;
import com.bpm.engine.mapper.ProcessMapper;
import com.bpm.engine.model.*;
import com.bpm.engine.service.ControlProcessReferentService;
import com.bpm.engine.service.ProcessService;
import com.bpm.engine.utility.SystemSate;
import com.bpm.engine.validation.ProcessValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.bpm.engine.utility.SystemSate.CREATE;

@Service
public class ProcessManager implements RadomCode {

    private ProcessService processService;
    private ProcessValidation processValidationService;
    private ProcessMapper processMapper;
    private MapperEntityRespone mapperEntityRespone;
    private ControlProcessReferentService controlProcessReferent;

    @Autowired
    public ProcessManager(ProcessService processService, ProcessValidation processValidationService, ProcessMapper processMapper, MapperEntityRespone mapperEntityRespone, ControlProcessReferentService controlProcessReferent) {
        this.processService = processService;
        this.processValidationService = processValidationService;
        this.processMapper = processMapper;
        this.mapperEntityRespone = mapperEntityRespone;
        this.controlProcessReferent = controlProcessReferent;
    }

    public EntityRespone createProcess(ProcessModel processModel) {
        EntityRespone entityRespone = null;
        SystemInternalResponseModel response = processValidationService.isValid(processModel);
        if (response != null && response.getCondition()) {
            processModel.setCreateDate(new Date());
            processModel.setActivo(true);
            processModel.setState(CREATE.name());

            for (StageModel stage : processModel.getstages()) {
                stage.setDateCreate(new Date());
                for (TaskModel task : stage.gettasks()) {
                    task.setDateCreate(new Date());
                }
            }

            ProcessModel process = processService.save(processMapper.pojoToEntity(processModel));

            if (process != null && process.getId_process() != null) {

                process.setProcesCode(this.getCode());
                controlProcessReferent.saveControlProcess(
                        new ControlProcessReferent(process.getProcesCode(), process.getName(), process.getProcesTitle(),
                                process.getState(), Constants.TYPE_PROCESS, process.getId_process(), true, new Date()));
                for (StageModel stage : process.getstages()) {
                    stage.setStageCode(this.getCode());
                    controlProcessReferent.saveControlProcess(
                            new ControlProcessReferent(stage.getStageCode(), stage.getName(), stage.getTitle(),
                                    CREATE.name(), Constants.TYPE_STAGE, stage.getIdStage(), true, new Date()));
                    for (TaskModel task : stage.gettasks()) {
                        task.setCode(this.getCode());
                        controlProcessReferent.saveControlProcess(
                                new ControlProcessReferent(task.getCode(), task.getName(), task.getTitle(),
                                        CREATE.name(), Constants.TYPE_TASK, task.getIdTask(), true, new Date()));
                    }
                }
                ProcessModel retrunModel = processService.update(processMapper.pojoToEntity(process));
                return entityRespone = mapperEntityRespone.setEntityResponT(
                        new ProcessDTO(retrunModel),
                        "the process is save",
                        "The process format are correct");
                // TODO: IN THIS POINT PUT THE CODE FOR NOTIFICATION POSITIVE.
            }
        } else {

            return entityRespone = mapperEntityRespone.setEntityResponT(null, "Error in Data", response.getMensaje());
        }
        return entityRespone;
    }

    /***
     *
     * @return    // the code length can be more to 16 if
     */
    private String getCode() {
        String code = null;
        String initialCode = this.generateCodeLikeNumber(16);
        ControlProcessReferentModel controlProcessReferentModel = controlProcessReferent.findByCode(initialCode);
        if (controlProcessReferentModel.getId() != null) {
            this.getCode();
        } else {
            code = initialCode;
        }
        return code;
    }
}

