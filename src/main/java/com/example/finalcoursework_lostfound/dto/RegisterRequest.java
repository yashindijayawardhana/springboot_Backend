package com.example.finalcoursework_lostfound.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be 3-20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;  // Optional field

    private String role = "USER"; // Default value

    public String getUsername() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public String getEmail() {
        return null;
    }

    public String getRole() {
        return null;
    }
}
