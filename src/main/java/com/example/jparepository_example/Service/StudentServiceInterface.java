package com.example.jparepository_example.Service;

import com.example.jparepository_example.payload.ReqStudent;
import com.example.jparepository_example.payload.ResStudent;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface StudentServiceInterface {
    public ResStudent createStudent(ReqStudent reqStudent);
    public HttpEntity<?> getStudent(Integer id);

    //get all
    public List<ResStudent> getAllStudents();

    //put
    public HttpEntity<?> editStudent(ReqStudent reqStudent);

    //patch
    public HttpEntity<?> editColumn(Integer id, JsonPatch jsonPatch);

    //delete
    public HttpEntity<?> deleteStudent(Integer id);

}
