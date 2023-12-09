package com.bpm.engine.repository;

import com.bpm.engine.entitys.ApprovedProcess;
import com.bpm.engine.entitys.Assigned;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApprovedProcessRepository extends CrudRepository<ApprovedProcess, Long> {

    public Optional<ApprovedProcess> findByIdApprovedProcess(String id);

    public List<ApprovedProcess> findByProcessCodeContaining(String processCode);

    public List<ApprovedProcess> findByIdProcessContaining(String idProcess);

}
