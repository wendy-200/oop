package com.ups.oop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello world, this is my first Project!....";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam (required = false)String name,
                        @RequestParam(required = false) String lastname,
                        @RequestParam(required = false) Integer age) {
        String message = "This is my first SpringBootProject!";
        if (name != null)
           message += " " + name;
        if (lastname != null)
            message += " " + lastname;
        if (age != null)
            message += ", and my age is: " + age + " years old";
        return message;
    }

    @GetMapping("/concat/")
    public String concat(){
        return "This is my second rest service!, and my name is";
    }



    @GetMapping("/concat/{name}/{lastname}/{age}")
    public String concatenate(@PathVariable String name,
                              @PathVariable(required = false) String lastname,
                              @PathVariable(required = false) String age) {
        String message = "This is my second rest service!, and my name is: " + name;
        if (lastname != null)
            message += " " + lastname;
        return message;
    }

    @GetMapping("/concat/{name}")
    public String concatenate
            (@PathVariable String name) {
        String message = "This is my second rest service!, and my name is";
        if (name != null)
            message += " " + name;
        return message;
    }
}

