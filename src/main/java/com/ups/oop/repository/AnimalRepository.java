package com.ups.oop.repository;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, Long> {


}
