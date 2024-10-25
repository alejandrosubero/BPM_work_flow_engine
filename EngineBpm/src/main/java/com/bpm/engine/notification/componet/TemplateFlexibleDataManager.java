package com.bpm.engine.notification.componet;

import com.bpm.engine.models.InstanceDataInfoModel;
import com.bpm.engine.models.TemplateFlexibleDataModel;
import com.bpm.engine.notification.componet.SystenTemplateFlexibleDataComponet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TemplateFlexibleDataManager {

    @Autowired
    private SystenTemplateFlexibleDataComponet systenTemplateFlexibleDataComponet;

    public List<TemplateFlexibleDataModel> getSystemTemplateFlexibleData(InstanceDataInfoModel instanceDataInfo){
        return systenTemplateFlexibleDataComponet.getListBodyForTemplate(instanceDataInfo);
    }


}
