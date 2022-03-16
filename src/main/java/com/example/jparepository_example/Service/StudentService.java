package com.example.jparepository_example.Service;

import com.example.jparepository_example.Entity.Student;
import com.example.jparepository_example.Repository.StudentRepository;
import com.example.jparepository_example.payload.ReqStudent;
import com.example.jparepository_example.payload.ResStudent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public ResStudent createStudent(ReqStudent reqStudent) {
        Student student = studentRepository.save(new Student(reqStudent.getFirstName(), reqStudent.getLastName(), reqStudent.getAge(), reqStudent.getEmail()));
        return new ResStudent(student.getId(), student.getFirstName(), student.getLastName(), student.getAge(), student.getEmail());
    }

    @Override
    public HttpEntity<?> getStudent(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(getResStudent(byId.get()));
        } else {
            return ResponseEntity.ok().body("id not found");
        }
    }

    @Override
    public List<ResStudent> getAllStudents() {
        List<ResStudent> resStudents = new ArrayList<>();
        studentRepository.findAll().forEach(user -> {
            resStudents.add(getResStudent(user));
        });
        return resStudents;
    }

    @Override
    public HttpEntity<?> editStudent(ReqStudent reqStudent) {
        Optional<Student> byId = studentRepository.findById(reqStudent.getId());
        if (byId.isPresent()) {
            Student student = byId.get();
            student.setFirstName(reqStudent.getFirstName());
            student.setLastName(reqStudent.getLastName());
            student.setAge(reqStudent.getAge());
            student.setEmail(reqStudent.getEmail());
            Student saved = studentRepository.save(student);
            return ResponseEntity.ok().body(getResStudent(saved));
        } else {
            return ResponseEntity.status(400).body("id not found");
        }

    }

    @Override
    public HttpEntity<?> editColumn(Integer id, JsonPatch jsonPatch) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            try {
                Student editedStudent = applyPatchToUser(jsonPatch, student);
                Student save = studentRepository.save(editedStudent);
                return ResponseEntity.ok().body(getResStudent(save));
            } catch (JsonPatchException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("JsonPatchException");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("JsonProcessingException");
            }

        } else {
            return ResponseEntity.status(400).body("id not found");
        }


    }

    @Override
    public HttpEntity<?> deleteStudent(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            studentRepository.delete(byId.get());
            return ResponseEntity.ok().body("O'chirildi");
        } else {
            return ResponseEntity.status(400).body("id not found");
        }

    }

    public ResStudent getResStudent(Student student) {
        return new ResStudent(student.getId(), student.getFirstName(), student.getLastName(), student.getAge(), student.getEmail());
    }

    private Student applyPatchToUser(
            JsonPatch patch, Student targetCustomer) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, Student.class);
    }

}
