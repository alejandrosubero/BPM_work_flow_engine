package com.bpm.engine.service;

import com.bpm.engine.entitys.Template;
import com.bpm.engine.models.TemplateModel;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface TemplateService {

    public Boolean saveNewTemplate(Template template);
    public List<TemplateModel> findBycodeTemplateContaining(String codeTemplate);
    public TemplateModel findBycodeTemplateAndCompany(String codeTemplate, String company);
    public TemplateModel findByCodeTemplate(String codeTemplate);
    public boolean deleteTemplete(Long id);
    public List<Template> findByActive(Boolean active);
    public Boolean uploadTemplate(String codeTemplate, String typeTemplate, String company, String TempleteDescription, MultipartFile file);
    public String getFileOfTemplateFromFile(MultipartFile multipartFile);

    //    public  Boolean save(Template template);
    //    public  Boolean save2(Template template);
    //    public String getFileOfTemplate(String archivo);

}
