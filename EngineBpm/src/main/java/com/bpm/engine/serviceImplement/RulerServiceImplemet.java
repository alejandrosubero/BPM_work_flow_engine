package com.bpm.engine.serviceImplement;

import com.bpm.engine.mappers.RulerMapper;
import com.bpm.engine.models.RulerModel;
import com.bpm.engine.repository.RulerRepository;
import com.bpm.engine.service.RulerService;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class RulerServiceImplemet implements RulerService {

	 private static final Logger logger = LogManager.getLogger(RulerServiceImplemet.class);
	
	@Autowired
    private RulerRepository repository;
	
	@Autowired
    private RulerMapper mapper;


    
    @Autowired
    public RulerServiceImplemet(RulerRepository repository, RulerMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

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
    public List<RulerModel> findByAction(Integer action) {
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
