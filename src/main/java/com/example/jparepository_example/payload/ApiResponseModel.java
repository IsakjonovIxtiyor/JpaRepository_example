package com.example.jparepository_example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponseModel {
    private String message;
    private Object data;
    private boolean success;
}
