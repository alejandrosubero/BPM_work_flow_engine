package com.bpm.engine.notification.componet;

import com.bpm.engine.model.StateModel;
import com.bpm.engine.model.TemplateFlexibleDataModel;
import com.bpm.engine.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateFilling {

    @Autowired
    private TemplateService templateService;

    public String fillTemplate(String templateCode, List<TemplateFlexibleDataModel> objectFlexible){
        String template = this.templateService.findByCodeTemplate(templateCode).getTemplate();
        for (TemplateFlexibleDataModel model: objectFlexible) {
            template = template.replace(model.getField(), model.getValue());
        }
        return template;
    }




}
