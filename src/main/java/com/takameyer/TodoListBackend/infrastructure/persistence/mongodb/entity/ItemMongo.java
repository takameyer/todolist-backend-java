package com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.entity;

import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//@Data
//@NoArgsConstructor
//@Document(collection="Item")
//public class ItemMongo {
//    @Id
//    private ObjectId id;
//    private String summary;
//    private Boolean isComplete;
//    @Field(value="owner_id")
//    private String ownerId;
//
//    public ItemMongo(String summary) {
//        this.summary = summary;
//        this.isComplete = false;
//    }
//}

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