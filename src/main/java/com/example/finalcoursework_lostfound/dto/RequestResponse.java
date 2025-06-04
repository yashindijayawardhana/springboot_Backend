package com.example.finalcoursework_lostfound.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestResponse {
    private Long id;
    private String itemName;
    private String requesterName;
    private LocalDateTime requestDate;
    private String status;
}
