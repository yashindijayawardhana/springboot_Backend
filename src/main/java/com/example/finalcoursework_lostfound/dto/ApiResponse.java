package com.example.finalcoursework_lostfound.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String loginSuccessful, T response) {
    }
}