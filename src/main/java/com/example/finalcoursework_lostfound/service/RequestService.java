package com.example.finalcoursework_lostfound.service;


import com.example.finalcoursework_lostfound.dto.RequestDto;
import com.example.finalcoursework_lostfound.dto.RequestResponse;
import com.example.finalcoursework_lostfound.entity.Item;
import com.example.finalcoursework_lostfound.entity.ItemStatus;
import com.example.finalcoursework_lostfound.entity.Request;
import com.example.finalcoursework_lostfound.entity.RequestStatus;
import com.example.finalcoursework_lostfound.exception.ResourceNotFoundException;
import com.example.finalcoursework_lostfound.repository.ItemRepository;
import com.example.finalcoursework_lostfound.repository.RequestRepository;
import com.example.finalcoursework_lostfound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestService {
    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public RequestService(RequestRepository requestRepository, ItemRepository itemRepository, UserRepository userRepository, EmailService emailService) {
        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RequestResponse createRequest(RequestDto requestDto) {
        // Validate item exists and is not already claimed
        Item item = itemRepository.findById(requestDto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + requestDto.getItemId()));

        if (item.getStatus() == ItemStatus.CLAIMED) {
            throw new IllegalStateException("Item already claimed");
        }

        // Validate requester exists
        User requester = userRepository.findById(requestDto.getRequesterId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getRequesterId()));

        // Create and save request
        Request request = Request.builder()
                .item(item)
                .requester(requester)
                .status(RequestStatus.PENDING)
                .requestDate(LocalDateTime.now())
                .build();

        Request savedRequest = requestRepository.save(request);
        return mapToResponse(savedRequest);
    }

    public RequestResponse updateRequestStatus(Long requestId, RequestStatus newStatus) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + requestId));

        request.setStatus(newStatus);

        // If approved, mark item as claimed
        if (newStatus == RequestStatus.APPROVED) {
            request.getItem().setStatus(ItemStatus.CLAIMED);
            emailService.sendEmail(
                    request.getRequester().getEmail(),
                    "Claim Approved",
                    "Your claim for " + request.getItem().getName() + " has been approved!"
            );
        }

        return mapToResponse(requestRepository.save(request));
    }

    public RequestResponse getRequestById(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + requestId));
        return mapToResponse(request);
    }

    private RequestResponse mapToResponse(Request request) {
        return RequestResponse.builder()
                .id(request.getId())
                .itemName(request.getItem().getName())
                .requesterName(request.getRequester().getUsername())
                .requestDate(request.getRequestDate())
                .status(request.getStatus().name())
                .build();
    }
}