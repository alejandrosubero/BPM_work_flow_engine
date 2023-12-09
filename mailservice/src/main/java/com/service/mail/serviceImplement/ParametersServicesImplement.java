package com.service.mail.serviceImplement;


import com.service.mail.entitys.Parameters;
import com.service.mail.mapper.ParametersMapper;
import com.service.mail.pojo.ParametersPojo;
import com.service.mail.repository.ParametersRepository;
import com.service.mail.service.ParametersServices;
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
        ParametersPojo pojo = null;
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
    public ParametersPojo findBykey(String key) {
        try {
            return mapper.entityToPojo(repository.findByKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String findValue(String key) {
        ParametersPojo parametro = null;
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
