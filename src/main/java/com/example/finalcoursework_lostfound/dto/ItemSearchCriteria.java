package com.example.finalcoursework_lostfound.dto;

import com.example.finalcoursework_lostfound.entity.ItemStatus;
import lombok.Data;

@Data
public class ItemSearchCriteria {
    private ItemStatus status;
    private String location;
    private String nameContains;
}