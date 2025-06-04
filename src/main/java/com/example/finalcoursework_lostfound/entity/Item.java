package com.example.finalcoursework_lostfound.entity;

import com.example.finalcoursework_lostfound.dto.RegisterRequest;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Resource builder() {
        return null;
    }

    public void setReportedBy(User user) {
    }

    public void setDateReported(LocalDateTime now) {
    }

    public void setResolved(boolean b) {
    }

    public void setStatus(ItemStatus itemStatus) {
    }

    public void setTitle(Object title) {
    }

    public void setDescription(Object description) {
    }

    public void setLocation(Object location) {
    }

    public void setImageUrl(Object imageUrl) {
    }

    public void setCategory(Object category) {
    }

    public void setClaimedBy(User user) {
    }

    public void setDateClaimed(LocalDateTime now) {
    }

    public void ItemsetDateReported(LocalDateTime now) {
    }

    public void setStatus(com.example.finalcoursework_lostfound.entity.ItemStatus itemStatus) {
    }

    public Object getId() {
        return null;
    }

    public long getName() {
        return 0;
    }

    public Object getDescription() {
        return null;
    }

    public Object getLocationFound() {
        return null;
    }

    public Object getStatus() {
        return null;
    }

    public RegisterRequest getReportedBy() {
        return null;
    }

    private class ItemStatus {
    }
}
