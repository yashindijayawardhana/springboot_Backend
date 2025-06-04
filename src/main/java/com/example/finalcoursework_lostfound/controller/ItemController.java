package com.example.finalcoursework_lostfound.controller;


import com.example.finalcoursework_lostfound.dto.ItemRequest;
import com.example.finalcoursework_lostfound.dto.ItemResponse;
import com.example.finalcoursework_lostfound.dto.ItemSearchCriteria;
import com.example.finalcoursework_lostfound.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createItem(@RequestBody ItemRequest request) throws InterruptedException {
        return itemService.createItem(request);
    }

    @GetMapping("/{id}")
    public ItemResponse getItem(@PathVariable Long id) throws InterruptedException {
        return itemService.getItemById(id);
    }

    @GetMapping("/search")
    public List<ItemResponse> searchItems(@RequestBody ItemSearchCriteria criteria) {
        return itemService.searchItems(criteria);
    }
}