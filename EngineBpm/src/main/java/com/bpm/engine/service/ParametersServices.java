package com.bpm.engine.service;

import com.bpm.engine.entitys.Parameters;
import com.bpm.engine.models.ParametersModel;

public interface ParametersServices {

    public String findValue(String key);
    public boolean saveOfFrom(Parameters parameters);
    public boolean save(Parameters parameters);
    public boolean delete( Long id);
    public ParametersModel findBykey(String key);

}
