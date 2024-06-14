package com.ups.oop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello world, this is my first Project!....";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name,
                        @RequestParam String lastname,
                         @RequestParam String age ){
        return "This is my first SpringBootProject!" +
                ", and my name is: "+ name + " " + lastname +" " + age +" years old";
    }

    @GetMapping("/concat/{name}/{lastname}/{age}")
    public String concatenate(@PathVariable String name, @PathVariable String lastname,@PathVariable String age){
        return "This is my second rest service!, and my name is: "
                + name + " " + lastname +" " + age +" years old";
    }
}
