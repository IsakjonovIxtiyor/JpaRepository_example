package com.example.jparepository_example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResStudent {
    private Integer id;
    private String firstName;
    private String lastName;
    private String age;
    private String email;

}
