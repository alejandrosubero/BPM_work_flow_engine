package com.bpm.engine.serviceImplement;


import com.bpm.engine.entitys.Parameters;
import com.bpm.engine.mappers.ParametersMapper;
import com.bpm.engine.models.ParametersModel;
import com.bpm.engine.repository.ParametersRepository;
import com.bpm.engine.service.ParametersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametersServicesImplement implements ParametersServices {

    @Autowired
    private ParametersRepository repository;

    @Autowired
    private ParametersMapper mapper;


    @Override
    public boolean save(Parameters parameters) {
        try {
            repository.save(parameters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOfFrom(Parameters parameters) {
        ParametersModel pojo = null;
        try {
            pojo = this.findBykey(parameters.getKey());
            if (pojo != null && pojo.getIdParameter() != null) {
                this.delete(pojo.getIdParameter());
            }
            repository.save(parameters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ParametersModel findBykey(String key) {
        try {
            return mapper.entityToPojo(repository.findByKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String findValue(String key) {
        ParametersModel parametro = null;
        try {
            parametro = mapper.entityToPojo(repository.findByKeyAndActive(key, true));
            if (parametro != null && parametro.getValue() != null) {
                return parametro.getValue();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}