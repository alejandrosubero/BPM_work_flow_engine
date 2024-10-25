package com.bpm.engine.service;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.models.ApprovedProcessModel;

import java.util.List;
import java.util.Optional;

public interface ApprovedProcessService {

    public ApprovedProcessModel findByIdApprovedProcess(String id);

    public List<ApprovedProcessModel> findByProcessCodeContaining(String processCode);

    public List<ApprovedProcessModel> findByIdProcessContaining(String idProcess);
}
