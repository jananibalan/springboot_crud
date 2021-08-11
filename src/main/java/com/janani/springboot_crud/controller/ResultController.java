package com.janani.springboot_crud.controller;

import com.janani.springboot_crud.entity.Result;
import com.janani.springboot_crud.entity.Student;
import com.janani.springboot_crud.exception.ResourceNotFoundException;
import com.janani.springboot_crud.repository.ResultRepository;
import com.janani.springboot_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping()
    public List<Result> getResults(){
        return this.resultRepository.findAll();
    }
//
//    @GetMapping()
//    public Result getResultById(@PathVariable(value = "id") long studentId){
//        return this.resultRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id :" + studentId));
//
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteResult(@PathVariable("id") long studentId){
        Result existingResult=this.resultRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException(""));
        this.resultRepository.delete(existingResult);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Result createResult(@RequestBody Result result,@RequestParam("student_id") long studentId){
        Optional<Student> student=studentRepository.findById(studentId);
        result.setStudent(student.get());
        return this.resultRepository.save(result);
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Result updateResult(@RequestBody Result result) {
        Result existingResult = this.resultRepository.findById(result.getId()).orElseThrow(() -> new ResourceNotFoundException(""));
        existingResult.setMarks(result.getMarks());

        existingResult.setResult(result.getResult());
        existingResult.setPercentage(result.getPercentage());

        return this.resultRepository.save(existingResult);
    }


    @GetMapping("/update")
    public String getUpdate(){
        return "hi";
    }








}
