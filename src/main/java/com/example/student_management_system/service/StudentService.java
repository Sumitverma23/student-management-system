package com.example.student_management_system.service;

import com.example.student_management_system.entity.Student;
import com.example.student_management_system.exception.StudentNotFoundException;
import com.example.student_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // for creating student
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    // get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student By Id
    public Student getStudentById(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(() ->
                new StudentNotFoundException(
                "student not found with the id: " +id
        )
                        );

    }

    // update the student by id
    public Student updateStudent(Integer id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with the id: " + id
                        )
                );

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setAge(student.getAge());

        return studentRepository.save(existingStudent);
    }


    // delete based on id
    public void deleteStudent(Integer id) {


        Student student= studentRepository.findById(id)
                        .orElseThrow(()->
                                new StudentNotFoundException(
                                        "Student not found with the id :" + id
                                )
                        );
        studentRepository.deleteById(id);
    }



}
