package com.bpm.engine.repository;

import com.bpm.engine.entitys.Template;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    public Template findByCodeTemplate(String codeTemplate);

    public Template findByCodeTemplateAndCompany(String codeTemplate, String company);

    public List<Template> findByCodeTemplateContaining(String codeTemplate);

    public List<Template> findByActive(Boolean active);


//    @Transactional
//    @Modifying
//    @Query("update Template u set u.wasSend = ?1 where u.idMailSender = ?2")
//    void updateWasSend(String wasSend, Long idMailSender);

}