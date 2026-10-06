package com.example.student_management_system.service;

import com.example.student_management_system.dto.StudentRequestDTO;
import com.example.student_management_system.dto.StudentResponseDTO;
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
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {

        Student student = new Student();

        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setCourse(studentRequestDTO.getCourse());
        student.setAge(studentRequestDTO.getAge());

        Student savedStudent = studentRepository.save(student);

        return convertToResponseDTO(savedStudent);
    }


    private StudentResponseDTO convertToResponseDTO(Student student) {

        StudentResponseDTO responseDTO = new StudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setCourse(student.getCourse());
        responseDTO.setAge(student.getAge());

        return responseDTO;
    }

    // get all students
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // Get student By Id
    public StudentResponseDTO getStudentById(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with the id: " + id
                        )
                );

        return convertToResponseDTO(student);
    }

    // update the student by id
    public StudentResponseDTO updateStudent(
            Integer id,
            StudentRequestDTO studentRequestDTO) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with the id: " + id
                        )
                );

        existingStudent.setName(studentRequestDTO.getName());
        existingStudent.setEmail(studentRequestDTO.getEmail());
        existingStudent.setCourse(studentRequestDTO.getCourse());
        existingStudent.setAge(studentRequestDTO.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);

        return convertToResponseDTO(updatedStudent);
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
