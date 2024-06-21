package com.ups.oop.controller;

import com.ups.oop.dto.Person;
import com.ups.oop.Service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonControl {
    private final PersonService personService;

    public PersonControl(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public List<Person> getAllPeople() {
        return this.personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public Person getPersonById(@RequestParam String id) {
        return this.personService.getPersonById(id);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return this.personService.createPerson(person);
    }
}

