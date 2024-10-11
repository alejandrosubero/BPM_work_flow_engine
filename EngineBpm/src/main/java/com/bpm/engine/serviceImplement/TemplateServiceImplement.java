package com.bpm.engine.serviceImplement;

import com.bpm.engine.utility.Constants;
import com.bpm.engine.entitys.Template;
import com.bpm.engine.mappers.TemplateMapper;
import com.bpm.engine.models.TemplateModel;
import com.bpm.engine.repository.TemplateRepository;
import com.bpm.engine.service.TemplateService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
public class TemplateServiceImplement implements TemplateService {

	private static final Logger logger = LogManager.getLogger(TemplateServiceImplement.class);

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public Boolean uploadTemplate(String codeTemplate,String typeTemplate,String company,String TempleteDescription,MultipartFile file){
        try {
            String templeteTemp =  this.getFileOfTemplateFromFile(file);
            if(templeteTemp != null){
                return this.saveNewTemplate(
                        templateMapper.pojoToEntity(new TemplateModel(
                                company, TempleteDescription, typeTemplate, codeTemplate, templeteTemp)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private File convertMultuploadipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }

    @Override
    public Boolean saveNewTemplate(Template template) {
        Template templateBase = null;
        try {
            templateBase = templateRepository.findByCodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getCodeTemplate() != null &&
                    !templateBase.getCodeTemplate().equals(template.getCodeTemplate())) {

                templateBase.setTemplate(template.getTemplate());
                templateBase.setCompany(template.getCompany());
                templateBase.setDescripcion(template.getDescripcion());
                templateBase.setTipo(template.getTipo());
                templateRepository.save(templateBase);
                return false;
            }
            templateRepository.save(template);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTemplete(Long id) {
        try {
            templateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TemplateModel findBycodeTemplateAndCompany(String codeTemplate, String company) {
        TemplateModel pojo = null;
        try {
            // pojo = templateMapper.entityToPojo(templateRepository.findBycodeTemplate(codeTemplate));
            return TemplateModel.newTemplate(templateRepository.findByCodeTemplateAndCompany(codeTemplate, company));
            //  return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    @Override
    public TemplateModel findByCodeTemplate(String codeTemplate) {
        TemplateModel templateBase = null;
        try {
            templateBase = templateMapper.entityToPojo(templateRepository.findByCodeTemplate(codeTemplate));
        }catch (Exception e){
            e.printStackTrace();
            return templateBase;
        }
        return templateBase;
    }

    @Override
    public List<TemplateModel> findBycodeTemplateContaining(String codeTemplate) {
        List<TemplateModel> lista = new ArrayList<TemplateModel>();
        try {
            templateRepository.findByCodeTemplateContaining(codeTemplate)
                    .stream().forEach(template -> lista.add(templateMapper.entityToPojo(template)));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return lista;
        }
    }



    @Override
    public String getFileOfTemplateFromFile(MultipartFile multipartFile) {
        String stringOfFile;
        FileReader fileReader = null;
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            fileReader = convertMultipartFileToFileReader(multipartFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((stringOfFile = bufferedReader.readLine()) != null) {
                stringBuilder.append(stringOfFile);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException a) {
            a.printStackTrace();
            return null;
        }
        return stringBuilder.toString();
    }

    public FileReader convertMultipartFileToFileReader(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt");
        multipartFile.transferTo(tempFile);
        return new FileReader(tempFile);
    }

    @Override
    public List<Template> findByActive(Boolean active) {
        List<Template> lista = new ArrayList<Template>();
        try {
            templateRepository.findByActive(active).stream().forEach(template -> lista.add(template));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return lista;
        }
    }


    //    @Override
    public String getFileOfTemplate(String archivo) {
        this.logger.info("get-File-Of-Template...");
        String cadena;
        FileReader f = null;
        String content = "";
        StringBuilder sb = new StringBuilder("");

        try {
            content = new String(Files.readAllBytes(Paths.get(Constants.templateFile + archivo)));
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

        try {
            f = new FileReader(Constants.templateFile + archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                sb.append(cadena);
            }
            b.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException a) {
            a.printStackTrace();
        }
        return sb.toString();
    }

    //    @Override
    public Boolean save2(Template template) {
        this.logger.info("Start the set template");
        Template templateBase = null;
        try {
            templateBase = templateRepository.findByCodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getIdTemplete() != null) {
                templateRepository.deleteById(templateBase.getIdTemplete());
            }
            templateRepository.save(template);
            this.logger.info("end el set template");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("end set template in error");
            this.logger.error("end set template in error", e);
            return false;
        }
    }
    //    @Override
    public Boolean save(Template template) {
        this.logger.info("Start the set template");
        Template templateBase = null;
        try {
            templateBase = templateRepository.findByCodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getIdTemplete() != null) {
                templateRepository.deleteById(templateBase.getIdTemplete());
            }
            template.setTemplate(this.getFileOfTemplate(Constants.TEMPLATENAME));
            templateRepository.save(template);
            this.logger.info("end el set template");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("end set template in error");
            this.logger.error("end set template in error", e);
            return false;
        }
    }
}
