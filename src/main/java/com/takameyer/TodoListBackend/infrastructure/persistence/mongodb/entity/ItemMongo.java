package com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.entity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public record ItemMongo(
    @BsonId ObjectId id,
    String summary,
    Boolean isComplete,
    @BsonProperty("owner_id") String ownerId
) {
    public ItemMongo {
        if (isComplete == null) {
            isComplete = false;
        }
    }

    public ItemMongo(String summary) {
        this(new ObjectId(), summary, false, null);
    }
}