package com.example.finalcoursework_lostfound.dto;


import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

@Getter
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String email;
    private String role;

    public static ElasticsearchConnectionDetails.Node builder() {
        return null;
    }
}