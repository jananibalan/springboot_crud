package com.janani.springboot_crud.controller;

import com.janani.springboot_crud.entity.Student;
import com.janani.springboot_crud.exception.ResourceNotFoundException;
import com.janani.springboot_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") long studentId){
        return this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id :" + studentId));

    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return this.studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") long studentId) {
        Student existingStudent=this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException(""));
        existingStudent.setName(student.getName());
        existingStudent.setDob(student.getDob());
        existingStudent.setBranch(student.getBranch());
        return this.studentRepository.save(existingStudent);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable ("id") long studentId){
        Student existingStudent=this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException(""));
        this.studentRepository.delete(existingStudent);
        return ResponseEntity.ok().build();

    }


}
