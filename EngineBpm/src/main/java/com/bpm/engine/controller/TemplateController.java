package com.bpm.engine.controller;

import com.bpm.engine.dto.EntityRespone;
import com.bpm.engine.mappers.MapperEntityRespone;
import com.bpm.engine.mappers.TemplateMapper;
import com.bpm.engine.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private PageController pageController;


    @PostMapping("/processForm")
    public String processForm(@RequestParam String codeTemplate, @RequestParam String typeTemplate,@RequestParam String company, @RequestParam String TempleteDescription, @RequestParam("file") MultipartFile file) {
       Boolean isSave = false;
       String menssage = "";
        if (!file.isEmpty()) {
            isSave =  templateService.uploadTemplate(codeTemplate,typeTemplate,company,TempleteDescription,file);
             if(isSave){
                 this.pageController.showMyPage();
                 menssage = "success";
             }else {
                 menssage = "Fail the save process";
             }
        }
      return menssage ;

    }


    @GetMapping("/code/{codeTemplete}")
    private ResponseEntity<EntityRespone> findBycodeTemplate(@PathVariable("codeTemplete") String codeTemplete, @RequestHeader("Company")  String company) {

        if(codeTemplete == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(templateService.findBycodeTemplateAndCompany(codeTemplete, company));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/id/{id}")
    private boolean delete(@PathVariable("id") Long id) {
        return templateService.deleteTemplete(id);
    }

}


//    @PostMapping("/save")
//    private Boolean  save(@RequestBody TemplateModel template){
//        return templateService.save(templateMapper.pojoToEntity(template));
//    }


//    @PostMapping("/saves")
//    private Boolean  saves(@RequestBody TemplateModel template){
//        return templateService.save2(templateMapper.pojoToEntity(template));
//    }
