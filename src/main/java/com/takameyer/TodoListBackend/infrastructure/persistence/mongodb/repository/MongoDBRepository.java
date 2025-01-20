package com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoDBRepository<T> {
    private final MongoCollection<T> collection;
    private final Class<T> entityClass;

    public MongoDBRepository(MongoClient mongoClient, String databaseName, String collectionName, Class<T> entityClass) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName, entityClass);
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        collection.insertOne(entity);
        return entity;
    }

    public List<T> saveAll(List<T> entities) {
        collection.insertMany(entities);
        return entities;
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(
            collection.find(Filters.eq("_id", new ObjectId(id)))
                    .first()
        );
    }

    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        collection.find().into(results);
        return results;
    }

    public List<T> findByField(String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        collection.find(Filters.eq(fieldName, value)).into(results);
        return results;
    }

    public boolean update(String id, T entity) {
        UpdateResult result = collection.replaceOne(
                Filters.eq("_id", new ObjectId(id)), entity
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
