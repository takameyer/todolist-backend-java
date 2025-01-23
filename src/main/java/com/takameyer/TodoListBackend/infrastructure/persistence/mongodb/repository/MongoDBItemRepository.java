package com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.entity.ItemMongo;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoDBItemRepository implements ItemRepository {
    private final MongoCollection<ItemMongo> collection;

    @Autowired
    public MongoDBItemRepository(MongoDatabase database) {
        this.collection = database.getCollection("Item", ItemMongo.class);
    }

    public Item save(Item item) {
        ItemMongo entity = new ItemMongo(item.summary());
        collection.insertOne(entity);
        return new Item(entity.id().toString(), entity.summary(), entity.isComplete(), entity.ownerId());
    }

    public List<Item> saveAll(List<Item> items) {
        List<ItemMongo> mongoEntities = items.stream()
                .map(item -> new ItemMongo(item.summary()))
                .toList();
        collection.insertMany(mongoEntities);
        return items;
    }

    public Optional<Item> findById(String id) {
        Optional<ItemMongo> entity =  Optional.ofNullable(
            collection.find(Filters.eq("_id", new ObjectId(id)))
                    .first()
        );

        return entity.map(itemMongo -> new Item(itemMongo.id().toString(), itemMongo.summary(), itemMongo.isComplete(), itemMongo.ownerId()));

    }

    public List<Item> findAll() {
        List<ItemMongo> results = new ArrayList<>();
        collection.find().into(results);
        return results.stream()
                .map(itemMongo -> new Item(itemMongo.id().toString(), itemMongo.summary(), itemMongo.isComplete(), itemMongo.ownerId()))
                .toList();
    }

    public List<Item> findByField(String fieldName, Object value) {
        List<ItemMongo> results = new ArrayList<>();
        collection.find(Filters.eq(fieldName, value)).into(results);
        return results.stream()
                .map(itemMongo -> new Item(itemMongo.id().toString(), itemMongo.summary(), itemMongo.isComplete(), itemMongo.ownerId()))
                .toList();
    }

    public boolean update(String id, Item item) {
        UpdateResult result = collection.replaceOne(
                Filters.eq("_id", new ObjectId(id)), new ItemMongo(new ObjectId(id), item.summary(), item.isComplete(), item.ownerId())
        );
        return result.getModifiedCount() > 0;
    }

    public boolean deleteById(String id) {
        DeleteResult result = collection.deleteOne(
                Filters.eq("_id", new ObjectId(id))
                );
       return result.getDeletedCount() > 0;
    }

    public long deleteAll() {
        DeleteResult result = collection.deleteMany(new Document());
        return result.getDeletedCount();
    }

    public long count() {
        return collection.countDocuments();
    }

    public boolean existsById(String id){
        return collection.countDocuments(Filters.eq("_id", new ObjectId(id))) > 0;
    }




}
