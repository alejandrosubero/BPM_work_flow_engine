package com.bpm.engine.service;

import com.bpm.engine.entitys.Ruler;
import com.bpm.engine.model.RulerModel;

import java.util.List;
import java.util.Optional;

public interface RulerService {

    public RulerModel findByIdRuler(Long id);

    public List<RulerModel> findByCondition(String condition);

    public List<RulerModel> findByAction(String action);

    public List<RulerModel> findByTaskCode(String taskCode);
}
