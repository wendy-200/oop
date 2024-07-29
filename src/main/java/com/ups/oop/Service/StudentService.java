package com.ups.oop.Service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.dto.StudentDTO;
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
    private List<StudentDTO>studentDTOList = new ArrayList<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity createStudent(StudentDTO studentDTO) {
        // Verificar si el estudiante ya existe
        Optional<Student> existingStudent = studentRepository.findByStudentId(studentDTO.getId());
        if (existingStudent.isPresent()) {
            String errorMessage = "Student with id " + studentDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            // Crear nuevo estudiante
            Student student = new Student();
            student.setStudentId(studentDTO.getId());
            student.setName(studentDTO.getName());
            student.setLastname(studentDTO.getLastname());
            student.setAge(studentDTO.getAge());
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
        }
    }

    public ResponseEntity getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getStudentId());
            studentDTO.setName(student.getName() + " " + student.getLastname());
            studentDTO.setAge(student.getAge());
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
            studentDTO.setId(studentFound.getStudentId());
            studentDTO.setName(studentFound.getName() + " " + studentFound.getLastname());
            studentDTO.setAge(studentFound.getAge());
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
        int upadateIndex = findIndexById(studentDTO.getId());
        if (upadateIndex != -1) {
            studentDTOList.set(upadateIndex, studentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student with id " + studentDTO.getId() + " doesn't exits ");

    }
    public ResponseEntity deleteStudentById(String id) {
        String message = "student with id " + id;
        for (StudentDTO ani : studentDTOList) {
            if (id.equalsIgnoreCase(ani.getId())) {
                studentDTOList.remove(ani);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}