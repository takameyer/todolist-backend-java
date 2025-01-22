package com.takameyer.TodoListBackend.controller;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBItemRepositoryTests extends AbstractItemRepositoryTests{

    static MongoDBContainer mongodb = new MongoDBContainer("mongo:7.0");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // ReplicaSetUrl automatically gets a database set
        registry.add("mongodb.uri", mongodb::getReplicaSetUrl);
    }
}
