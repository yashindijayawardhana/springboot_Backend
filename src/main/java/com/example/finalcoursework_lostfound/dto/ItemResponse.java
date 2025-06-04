package com.example.finalcoursework_lostfound.dto;


import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private String locationFound;
    private String status;
    private String reportedBy;

    public static Snippet builder() {
        return null;
    }
}