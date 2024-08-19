package com.ups.oop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String personId;
    private String name;
    private String lastname;
    private Integer age;

    public Person(String personId, String name, String lastname, Integer age) {
        this.personId = personId;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
}

}