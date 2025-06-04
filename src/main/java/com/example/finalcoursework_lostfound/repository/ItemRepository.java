package com.example.finalcoursework_lostfound.repository;


import com.example.finalcoursework_lostfound.dto.ItemSearchCriteria;
import com.example.finalcoursework_lostfound.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE " +
            "(:#{#criteria.status} IS NULL OR i.status = :#{#criteria.status}) AND " +
            "(:#{#criteria.location} IS NULL OR i.locationFound LIKE %:#{#criteria.location}%) AND " +
            "(:#{#criteria.nameContains} IS NULL OR i.name LIKE %:#{#criteria.nameContains}%)")
    List<Item> searchItems(@Param("criteria") ItemSearchCriteria criteria);
}