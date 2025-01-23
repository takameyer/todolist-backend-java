package com.takameyer.TodoListBackend.infrastructure.persistence.h2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class ItemH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private Boolean isComplete;
    private String ownerId;

    // Default constructor required by JPA
    public ItemH2() {
        this.isComplete = false; // Default value
    }

    // Constructor for creating new items
    public ItemH2(String summary) {
        this.summary = summary;
        this.isComplete = false; // Default value
    }

    // Full constructor
    public ItemH2(Long id, String summary, Boolean isComplete, String ownerId) {
        this.id = id;
        this.summary = summary;
        this.isComplete = isComplete != null ? isComplete : false;
        this.ownerId = ownerId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
