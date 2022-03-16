package com.example.jparepository_example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqStudent {
    private  Integer id;
    private String firstName;
    private String lastName;
    private String age;
    private String email;

    public ReqStudent(String firstName, String lastName, String age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
}
