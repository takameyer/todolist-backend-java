package com.takameyer.TodoListBackend.domain.model;

import lombok.Data;

@Data
public class ItemDomain {
    private String id;
    private String summary;
    private String ownerId;
    private Boolean isComplete;
}
