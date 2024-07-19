package com.ups.oop.Service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.entity.Animal;
import com.ups.oop.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public ResponseEntity createAnimal(AnimalDTO animaLDTO) {
        boolean wasFound = findAnimal(animaLDTO.getId());
        if (wasFound) {
            String errorMessage = "person with id" + animaLDTO.getId() + "already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalDTOList.add(animaLDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animaLDTO);
        }
    }

    private boolean findAnimal(String id) {
        for (AnimalDTO animaLDTO : animalDTOList) {
            if (id.equalsIgnoreCase(animaLDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllAnimals() {
        Iterable<Animal> animalIterable = animalRepository.findAll();
        List<AnimalDTO> animals = new ArrayList<>();
        for (Animal a : animalIterable) {
            AnimalDTO animal = new AnimalDTO();
                    animal.setAnimalCode(a.getName()+ "-" + a.getBread() + "-"+ a.getColor());
                    animal.setWeight(a.getWeight());
                    animal.setLength(a.getLength());
                    animal.setHeight(a.getHeight());
                    animals.add(animal);
            animals.add(animal);
        }

        if (animals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    public ResponseEntity getAnimalById(String id) {
        Optional<Animal>animalOptional = animalRepository.findById(Long.valueOf(id));
        if(animalOptional.isPresent()){
            Animal animalFound = animalOptional.get();
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(animalFound.getName()+ "-" + animalFound.getBread() + "-"+ animalFound.getColor());
            animal.setWeight(animalFound.getWeight());
            animal.setHeight(animalFound.getHeight());
            animal.setWeight(animalFound.getHeight());
            return  ResponseEntity.status(HttpStatus.OK).body(animal);

        }else{

        String errorMessage = "animal with id" + id + " already exists";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
      }

    }

    private int findIndexById(String id) {
        int index = 0;
        for (AnimalDTO a : animalDTOList) {
            if (id.equalsIgnoreCase(a.getId())) {
                return index;
            }
            index++;
        }
        return -1;

    }

    public ResponseEntity updateAnimal(AnimalDTO animaLDTO) {
        int upadateIndex = findIndexById(animaLDTO.getId());
        if (upadateIndex != -1) {
            animalDTOList.set(upadateIndex, animaLDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animaLDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("animal with id " + animaLDTO.getId() + " doesn't exits ");

    }

    public ResponseEntity deleteAnimalById(String id) {
        String message = "animal with id " + id;
        for (AnimalDTO ani : animalDTOList) {
            if (id.equalsIgnoreCase(ani.getId())) {
                animalDTOList.remove(ani);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}