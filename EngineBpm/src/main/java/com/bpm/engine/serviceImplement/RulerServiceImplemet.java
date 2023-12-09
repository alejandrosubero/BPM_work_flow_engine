package com.bpm.engine.serviceImplement;

import com.bpm.engine.mapper.RulerMapper;
import com.bpm.engine.model.RulerModel;
import com.bpm.engine.repository.RulerRepository;
import com.bpm.engine.service.RulerService;

import java.util.ArrayList;
import java.util.List;

public class RulerServiceImplemet implements RulerService {

    private RulerRepository repository;
    private RulerMapper mapper;


    @Override
    public RulerModel findByIdRuler(Long id) {
        return  mapper.entityToPojo(repository.findById(id).get());
    }

    @Override
    public  List<RulerModel> findByCondition(String condition) {
        List<RulerModel> find = new ArrayList<>();
        repository.findByCondition(condition).stream().forEach(com ->{
            find.add(mapper.entityToPojo(com));
        });
        return find;
    }

    @Override
    public List<RulerModel> findByAction(String action) {
        List<RulerModel> find = new ArrayList<>();
        repository.findByAction(action).stream().forEach(act ->{
            find.add(mapper.entityToPojo(act));
        });
        return find;
    }

    @Override
    public List<RulerModel> findByTaskCode(String taskCode) {
        List<RulerModel> find = new ArrayList<>();
        repository.findByTaskCode(taskCode).stream().forEach(tas ->{
            find.add(mapper.entityToPojo(tas));
        });
        return find;
    }
}
