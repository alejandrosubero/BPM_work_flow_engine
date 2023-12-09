package com.bpm.engine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//http://localhost:1111/bpm/template/addTemplate
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/template")
public class PageController {
    @GetMapping("/add")
    public String showMyPage() {
        return "templateForm.html";
    }
}
