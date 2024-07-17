package com.ups.oop.Service;

import com.ups.oop.dto.AnimalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public ResponseEntity createAnimal(AnimalDTO animalDTO){
        int index = findIndexById(animalDTO.getId());
        if(index!= -1){
            String errorMessage = "animal with id" + animalDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else{
            animalDTOList.add(animalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        }
    }

    private int findIndexById(String id){
        int index = 0;
        for (AnimalDTO animalDTO : animalDTOList){
            if(id.equalsIgnoreCase(animalDTO.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public ResponseEntity getAllAnimals(){
        if(animalDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalDTOList);
    }

    public ResponseEntity getAnimalById(String id){
        int index = findIndexById(id);
        if(index!= -1){
            return ResponseEntity.status(HttpStatus.OK).body(animalDTOList.get(index));
        }else{
            String errorMessage = "animal with id" + id +  " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateAnimal(AnimalDTO animalDTO){
        int index = findIndexById(animalDTO.getId());
        if(index!= -1){
            animalDTOList.set(index, animalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("animal with id " + animalDTO.getId()+" doesn't exist");
        }
    }

    public ResponseEntity deleteAnimalById(String id){
        int index = findIndexById(id);
        if(index!= -1){
            animalDTOList.remove(index);
            String message = "animal with id " + id + " removed successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else{
            String message = "animal with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}