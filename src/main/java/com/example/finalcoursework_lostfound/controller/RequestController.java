package com.example.finalcoursework_lostfound.controller;


import com.example.finalcoursework_lostfound.dto.RequestDto;
import com.example.finalcoursework_lostfound.dto.RequestResponse;
import com.example.finalcoursework_lostfound.entity.RequestStatus;
import com.example.finalcoursework_lostfound.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    // This single field will automatically generate the constructor via Lombok
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestResponse createRequest(@RequestBody RequestDto requestDto) {
        return requestService.createRequest(requestDto);
    }

    @PatchMapping("/{requestId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public RequestResponse approveRequest(@PathVariable Long requestId) {
        return requestService.updateRequestStatus(requestId, RequestStatus.APPROVED);
    }

    @PatchMapping("/{requestId}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public RequestResponse rejectRequest(@PathVariable Long requestId) {
        return requestService.updateRequestStatus(requestId, RequestStatus.REJECTED);
    }

    @GetMapping("/{requestId}")
    public RequestResponse getRequest(@PathVariable Long requestId) {
        return requestService.getRequestById(requestId);
    }
}