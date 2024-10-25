package com.bpm.engine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bpm.engine.entitys.Ruler;

public interface RulerRepository extends CrudRepository<Ruler, Long> {

    public Optional<Ruler> findByIdRuler(String id);

    public List<Ruler> findByCondition(String condition);

    public List<Ruler> findByAction(Integer action);

    public List<Ruler> findByTaskCode(String taskCode);

}
