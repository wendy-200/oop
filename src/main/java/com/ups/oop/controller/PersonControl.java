package com.ups.oop.controller;

import com.ups.oop.dto.Person;
import com.ups.oop.Service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonControl {
    private final PersonService personService;

    public PersonControl(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople() {
        return this.personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id) {
        return this.personService.getPersonById(id);
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person) {
        return this.personService.createPerson(person);
    }
    @PutMapping("update-person")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        return this.personService.updatePerson(person);
    }
    @DeleteMapping("/remove-person")
    public String deletePerson(@RequestParam String id){
        return this.personService.deletePersonById(id);
   }
}

