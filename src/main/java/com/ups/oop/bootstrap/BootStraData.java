package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStraData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;

    public BootStraData(PersonRepository personRepository, AnimalRepository animalRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //person
        Person p1 = new Person();
        p1.setPersonId("0932467913");
        p1.setName("wendy");
        p1.setLastname("ortiz");
        p1.setAge(19);

        Person p2 = new Person();
        p2.setPersonId("0982765490");
        p2.setName("Mabelle");
        p2.setLastname("Mideros");
        p2.setAge(20);


        personRepository.save(p1);
        personRepository.save(p2);


        //Animals
        Animal a1 = new Animal();
        a1.setName("Bod");
        a1.setBread("Pitbull");
        a1.setColor("brown");
        a1.setWeight(20.1);
        a1.setHeight(3.2);
        a1.setLength(3.3);

        Animal a2 = new Animal();
        a2.setName("Simba");
        a2.setBread("Leonberger");
        a2.setColor("black");
        a2.setWeight(11.3);
        a2.setHeight(2.2);
        a2.setLength(3.2);

        animalRepository.save(a1);
        animalRepository.save(a2);

        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " + personRepository.count());
        System.out.println("Number of animal: " + animalRepository.count());


    }
}

