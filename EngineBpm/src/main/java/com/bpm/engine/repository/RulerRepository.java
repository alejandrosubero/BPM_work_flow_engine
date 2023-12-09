package com.bpm.engine.repository;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.entitys.Ruler;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RulerRepository extends CrudRepository<Ruler, Long> {

    public Optional<Ruler> findByIdRuler(String id);

    public List<Ruler> findByCondition(String condition);

    public List<Ruler> findByAction(String action);

    public List<Ruler> findByTaskCode(String taskCode);

}
