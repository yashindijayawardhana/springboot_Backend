package com.example.finalcoursework_lostfound.service;


import com.example.finalcoursework_lostfound.dto.AuthResponse;
import com.example.finalcoursework_lostfound.entity.RefreshToken;
import com.example.finalcoursework_lostfound.entity.Role;
import com.example.finalcoursework_lostfound.entity.User;
import com.example.finalcoursework_lostfound.exception.*;
import com.example.finalcoursework_lostfound.repository.RoleRepository;
import com.example.finalcoursework_lostfound.repository.UserRepository;
import com.example.finalcoursework_lostfound.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService, CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public AuthResponse register(String username, String password, String email, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already in use");
        }

        Role role = roleRepository.findByName("ROLE_" + roleName.toUpperCase())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(role)
                .build();

        User savedUser = userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());

        String accessToken = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser.getId());

        return buildAuthResponse(savedUser, accessToken, refreshToken.getToken());
    }

    @Transactional
    public AuthResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String accessToken = jwtUtil.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

            return buildAuthResponse(user, accessToken, refreshToken.getToken());
        } catch (BadCredentialsException e) {
            throw new AuthenticationFailedException("Invalid credentials");
        }
    }

    @Transactional
    public AuthResponse refreshToken(String refreshToken) {
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtil.generateToken(
                            userDetailsService.loadUserByUsername(user.getClass()));
                    return buildAuthResponse(user , accessToken, refreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token not valid"));
    }

    private AuthResponse buildAuthResponse(User user, String accessToken, String refreshToken) {
        return AuthResponse.builder()
                .username()
                .email(user.getEmail())
                .role(user.getRole().getName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void logout(Long userId) {
        refreshTokenService.deleteByUserId(userId);
    }
}
