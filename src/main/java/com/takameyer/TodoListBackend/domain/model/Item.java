package com.takameyer.TodoListBackend.domain.model;

public record Item(String id, String summary, Boolean isComplete, String ownerId) {
}
