package com.takameyer.TodoListBackend.infrastructure.persistence.h2.entity;

import org.springframework.data.annotation.Id;


public record ItemH2(
    @Id Long id,
    String summary,
    Boolean isComplete,
    String ownerId
) {
    public ItemH2 {
        if (isComplete == null) {
            isComplete = false;
        }
    }

    public ItemH2(String summary) {
        this(null, summary, false, null);
    }
}
