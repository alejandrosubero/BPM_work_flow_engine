package com.bpm.engine.service;

import java.util.List;

import com.bpm.engine.models.RulerModel;

public interface RulerService {

    public RulerModel findByIdRuler(Long id);

    public List<RulerModel> findByCondition(String condition);

    public List<RulerModel> findByAction(Integer action);

    public List<RulerModel> findByTaskCode(String taskCode);
}
