package com.takameyer.TodoListBackend.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Document(collection = "Item")
public class Item {
    @Id
    private String id;
    private String summary;
    private Boolean isCompleted;
    private String owner_id;

    @CreatedDate
    private Instant created_at;

    @CreatedDate
    private Instant createdDate;

    public Item() {}

    public Item(String summary) {
        this.summary = summary;
        this.isCompleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}
