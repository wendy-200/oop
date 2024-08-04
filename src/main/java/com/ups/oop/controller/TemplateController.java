package com.ups.oop.controller;

import com.ups.oop.Service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    private final PersonService personService;

    public TemplateController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model){
        return "template";
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "person/list";
    }
}