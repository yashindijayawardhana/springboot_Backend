package com.example.finalcoursework_lostfound.service;


import com.example.finalcoursework_lostfound.dto.RegisterRequest;
import com.example.finalcoursework_lostfound.entity.Role;
import com.example.finalcoursework_lostfound.entity.User;
import com.example.finalcoursework_lostfound.exception.DuplicateEmailException;
import com.example.finalcoursework_lostfound.exception.DuplicateUsernameException;
import com.example.finalcoursework_lostfound.exception.RoleNotFoundException;
import com.example.finalcoursework_lostfound.repository.RoleRepository;
import com.example.finalcoursework_lostfound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername().trim();
        String email = registerRequest.getEmail() != null ?
                registerRequest.getEmail().trim() : null;

        // Check for existing username
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Username '" + username + "' already exists");
        }

        // Check for existing email if provided
        if (email != null && userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email '" + email + "' already in use");
        }

        // Determine role name with ROLE_ prefix
        String roleName = "ROLE_" + ((registerRequest.getRole() != null &&
                !registerRequest.getRole().isBlank()) ?
                registerRequest.getRole().toUpperCase() : "USER");

        // Find role in database
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role '" + roleName + "' not found"));

        // Build and save user
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(email)
                .role(role)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username.trim());
    }
}