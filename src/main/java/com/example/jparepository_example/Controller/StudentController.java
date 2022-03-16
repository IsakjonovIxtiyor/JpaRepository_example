package com.example.jparepository_example.Controller;

import com.example.jparepository_example.Service.StudentService;
import com.example.jparepository_example.payload.ReqStudent;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public HttpEntity<?> getAllStudents() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @PostMapping
    public HttpEntity<?> createStudent(@RequestBody ReqStudent reqStudent) {
        return ResponseEntity.ok().body(studentService.createStudent(reqStudent));
    }

    @GetMapping("{id}")
    public HttpEntity<?> getStudent(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @PutMapping
    public HttpEntity<?> editStudent(@RequestBody ReqStudent reqStudent) {
        return studentService.editStudent(reqStudent);
    }

    @PatchMapping("{id}")
    public HttpEntity<?> editColumn(@PathVariable Integer id, @RequestBody JsonPatch jsonPatch) {
        return studentService.editColumn(id, jsonPatch);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }

}
