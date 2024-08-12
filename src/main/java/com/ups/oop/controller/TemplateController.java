package com.ups.oop.controller;

import com.ups.oop.Service.AnimalService;
import com.ups.oop.Service.BookService;
import com.ups.oop.Service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private  final PersonService personService;
    private final AnimalService animalService;
    private final BookService bookService;

    public TemplateController(PersonService personService, AnimalService animalService, BookService bookService) {
        this.personService = personService;
        this.animalService = animalService;
        this.bookService = bookService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model){
        return "template";
    }
    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", PersonService.getAllPeople());
        return "person/list";

    }
    @GetMapping("/animal")
        public String getAnimal(Model model) {
        model.addAttribute("animal", animalService.getAllAnimals());
        return "animal/list";

    }
            @GetMapping("/book")
        public String getBook(Model model) {
            model.addAttribute("book", bookService.getBook());
            return "book/templatebook";
        }

    }
