package com.bpm.engine.serviceImplement;


import com.bpm.engine.mapper.ApprovedProcessMapper;
import com.bpm.engine.model.ApprovedProcessModel;
import com.bpm.engine.repository.ApprovedProcessRepository;
import com.bpm.engine.service.ApprovedProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovedProcessServiceImplement implements ApprovedProcessService {

    protected static final Log logger = LogFactory.getLog(ApprovedProcessServiceImplement.class);
    @Autowired
    private ApprovedProcessRepository repository;

    @Autowired
    private ApprovedProcessMapper mapper;

    @Override
    public ApprovedProcessModel findByIdApprovedProcess(String id) {
        logger.info("Starting => findByIdApprovedProcess");
            try {
                return  mapper.entityToPojo(repository.findByIdApprovedProcess(id).get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return new ApprovedProcessModel();
            }
    }

    @Override
    public List<ApprovedProcessModel> findByProcessCodeContaining(String processCode) {
        logger.info("Execute => findByProcessCodeContaining");
        List<ApprovedProcessModel> lista = new ArrayList<ApprovedProcessModel>();
        repository.findByProcessCodeContaining(processCode).forEach( ApprovedPM-> lista.add(mapper.entityToPojo(ApprovedPM)));
        return lista;
    }

    @Override
    public List<ApprovedProcessModel> findByIdProcessContaining(String idProcess) {
        logger.info("Execute => findByIdProcessContaining");
        List<ApprovedProcessModel> lista = new ArrayList<ApprovedProcessModel>();
        repository.findByIdProcessContaining(idProcess).forEach( ApprovedPM-> lista.add(mapper.entityToPojo(ApprovedPM)));
        return lista;
    }
}
