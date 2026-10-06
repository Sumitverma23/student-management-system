package com.example.student_management_system.controller;

import com.example.student_management_system.dto.StudentRequestDTO;
import com.example.student_management_system.dto.StudentResponseDTO;
import com.example.student_management_system.entity.Student;
import com.example.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public StudentResponseDTO addStudent(
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        return studentService.saveStudent(studentRequestDTO);
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }


    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        return studentService.updateStudent(id, studentRequestDTO);
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return "student with"+"  "+ id +" "+" is delted successfully";
    }

}
