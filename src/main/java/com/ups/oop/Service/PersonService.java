package com.ups.oop.Service;

import com.ups.oop.dto.StudentDTO;
import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static PersonRepository personRepository;
    private List<PersonDTO> personDTOList = new ArrayList<>();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personid = personDTO.getName();
        Optional<Person> personOptional = personRepository.findByPersonId(personid);
        if (personOptional.isPresent()) {
            String errorMessage = "person with id" + personDTO.getId() + "already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
        }
        if (personDTO.getName().contains(" ")) {
            Person personRecord = new Person();
            personRecord.setPersonId(personid);
            String[] nameStrings = personDTO.getName()
                    .split(" ");
            String name = nameStrings[0];
            String lastname = nameStrings[1];
            personRecord.setName(name);
            personRecord.setLastname(lastname);
            personRecord.setAge(personDTO.getAge());
            personRepository.save(personRecord);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contrain two strings separated by a whitespace");

        }
    }

    private boolean findPerson(String id) {
        for (PersonDTO personDTO : personDTOList) {
            if (id.equalsIgnoreCase(personDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public static ResponseEntity getAllPeople() {
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();


        for (Person p : personIterable) {
            PersonDTO person = new PersonDTO(p.getPersonId(), p.getName() + "-" + p.getLastname(), p.getAge());
            peopleList.add(person);
        }

        if (peopleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public ResponseEntity getPersonById(String personid) {
        //Optional<Person> personOptional = personRepository.findById(long.valueOf(id));
        Optional<Person> personOptional = personRepository.findByPersonId(personid);
        if (personOptional.isPresent()) {
            //if record was found
            Person personFound = personOptional.get();
            PersonDTO person = new PersonDTO(personFound.getPersonId(),
                    personFound.getName() + "-" + personFound.getLastname(),
                    personFound.getAge());
            return ResponseEntity.status(HttpStatus.OK).body(person);

        } else {
            //if record wasn't found
        }

        String errorMessage = "person with id" + personid + " doesn't exist :c";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id) {
        int index = 0;
        for (PersonDTO p : personDTOList) {
            if (id.equalsIgnoreCase(p.getId())) {
                return index;
            }
            index++;
        }
        return -1;

    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        String personId = personDTO.getId();
        Optional<Person> personOptional = personRepository.findByPersonId(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();

            if (personDTO.getName().contains(" ")) {
                person.setPersonId(personId);
                String[] nameStrings = new String[0];
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                person.setName(name);
                person.setLastname(lastname);
                person.setAge(personDTO.getAge());
                personRepository.save(person);
                return ResponseEntity.status(HttpStatus.OK).body(person);

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contrain two strings separated by a whitespace");

            }
        } else {
            String errorMenssage = " Person with id " + personId + "Not found";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contrain separated by a whitespaces");
        }
    }

    public ResponseEntity deletePersonById(String id) {
        String message = "person with id " + id;
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + "removed succesfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + "Not found");

        }
    }
}



