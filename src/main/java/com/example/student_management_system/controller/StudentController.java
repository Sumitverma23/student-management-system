package com.example.student_management_system.controller;

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
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }


    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Integer id,
           @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return "student with"+"  "+ id +" "+" is delted successfully";
    }

}
