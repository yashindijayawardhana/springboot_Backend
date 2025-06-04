package com.example.finalcoursework_lostfound.dto;


import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private String description;
    private String locationFound;
    private String status;
    private Long reportedById; // Must match exactly what you're calling in ItemService

    public Long getReportedById() {
        return 0L;
    }

    public Object getName() {
        return null;
    }

    public Object getDescription() {
        return null;
    }

    public Object getLocationFound() {
        return null;
    }

    public String getStatus() {
        return null;
    }
}