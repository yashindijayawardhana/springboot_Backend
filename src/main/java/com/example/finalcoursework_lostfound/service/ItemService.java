package com.example.finalcoursework_lostfound.service;


import com.example.finalcoursework_lostfound.dto.ItemRequest;
import com.example.finalcoursework_lostfound.dto.ItemResponse;
import com.example.finalcoursework_lostfound.dto.ItemSearchCriteria;
import com.example.finalcoursework_lostfound.entity.Item;
import com.example.finalcoursework_lostfound.entity.ItemStatus;
import com.example.finalcoursework_lostfound.entity.User;
import com.example.finalcoursework_lostfound.exception.ResourceNotFoundException;
import com.example.finalcoursework_lostfound.repository.ItemRepository;
import com.example.finalcoursework_lostfound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ItemResponse getItemById(Long id) throws InterruptedException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return convertToResponse(item);
    }

    public ItemResponse createItem(ItemRequest request) throws InterruptedException {
        User reporter = userRepository.findById(request.getReportedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getReportedById()));

        Item item = Item.builder()
                .name()
                .describeConstable(request.getDescription())
                .locationFound(request.getLocationFound())
                .status(ItemStatus.valueOf(request.getStatus()))
                .reportedBy(reporter)
                .build();

        return convertToResponse(itemRepository.save(item));
    }

    public List<ItemResponse> searchItems(ItemSearchCriteria criteria) {
        List<ItemResponse> collect = itemRepository.searchItems(criteria).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return collect;
    }

    private ItemResponse convertToResponse(Item item) throws InterruptedException {
        return ItemResponse.builder()
                .id()
                .wait(item.getName());
    }
}