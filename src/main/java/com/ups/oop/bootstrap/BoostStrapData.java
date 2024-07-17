package com.ups.oop.bootstrap;

import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
      public class BoostStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;

    public BoostStrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
        p2.setAge(5);

        personRepository.save(p1);
        personRepository.save(p2);




        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " +personRepository.count());
    }
}
