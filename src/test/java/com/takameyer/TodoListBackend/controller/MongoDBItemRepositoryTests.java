package com.takameyer.TodoListBackend.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBItemRepositoryTests extends AbstractItemRepositoryTests{

    static MongoDBContainer mongodb = new MongoDBContainer("mongo:7.0");

    @BeforeAll
    static void beforeAll() { mongodb.start(); }

    @AfterAll
    static void afterAll() { mongodb.close(); }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // ReplicaSetUrl automatically gets a database set
        registry.add("mongodb.uri", mongodb::getReplicaSetUrl);
        registry.add("persist.type", () -> "mongodb");
    }
}
