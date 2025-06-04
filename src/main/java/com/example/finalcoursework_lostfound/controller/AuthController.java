package com.example.finalcoursework_lostfound.controller;


import com.example.finalcoursework_lostfound.dto.AuthResponse;
import com.example.finalcoursework_lostfound.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final com.example.finalcoursework_lostfound.controller.AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestParam String username, @RequestParam String password,
                                 @RequestParam String email, @RequestParam String role) {
        return authService.register(username, password, email, role);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam Long userId) {
        authService.logout(userId);
    }
}
