package com.bpm.engine.repository;

import com.bpm.engine.entitys.Parameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends CrudRepository<Parameters, Long> {
    public Parameters findByKey(String key);
    public  Parameters findByKeyAndActive(String key, Boolean active);
}
