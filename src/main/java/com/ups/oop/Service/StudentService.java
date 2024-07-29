package com.ups.oop.Service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private List<StudentDTO> studentDTOList = new ArrayList<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity createStudent(StudentDTO studentDTO) {
        String studentid = studentDTO.getName();
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentDTO.getId());
        if (studentOptional.isPresent()) {
            String errorMessage = "Student with id " + studentDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
c            Student student = new Student();
            student.setStudentCode(studentDTO.getId());
            student.setName(studentDTO.getName());
            studentRepository.save(student);
            student.setLastname(studentDTO.getLastname());

            return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
        }
    }

    public ResponseEntity getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();


        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getStudentCode());
            studentDTO.setName(student.getName() + " " + student.getLastname());
            studentDTOList.add(studentDTO);
        }

        if (studentDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentDTOList);
    }

    public ResponseEntity getStudentById(String studentId) {
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if (studentOptional.isPresent()) {
            Student studentFound = studentOptional.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(studentFound.getStudentCode());
            studentDTO.setName(studentFound.getName() + " " + studentFound.getLastname());
            return ResponseEntity.status(HttpStatus.OK).body(studentDTO);

        } else {

            String errorMessage = "Student with id " + studentId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

    }

    private int findIndexById(String id) {
        int index = 0;
        for (StudentDTO a : studentDTOList) {
            if (id.equalsIgnoreCase(a.getId())) {
                return index;
            }
            index++;
        }
        return -1;

    }

    public ResponseEntity updateStudent(StudentDTO studentDTO) {
        String studentId = studentDTO.getId();
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (studentDTO.getName().contains(" ")) {
                student.setStudentCode(studentId);
                String[] nameStrings = studentDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                student.setName(name);
                student.setLastname(lastname);
                studentRepository.save(student);
                return ResponseEntity.status(HttpStatus.OK).body(student);

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student name must contain two strings separated by a whitespace");

            }
        } else {
            String errorMessage = "Student with id " + studentId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteStudentById(String id) {
        String message = "Student with id " + id;
        Optional<Student> studentOptional = studentRepository.findByStudentId(id);
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");

        }
    }
}