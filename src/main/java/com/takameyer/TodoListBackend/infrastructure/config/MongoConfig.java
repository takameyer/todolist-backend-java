package com.takameyer.TodoListBackend.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.entity.ItemMongo;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository.MongoDBRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MongoConfig {
    @Value("${mongodb.uri}")
    private String mongoUri;

    @Value("${mongodb.database}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    public MongoDBRepository<ItemMongo> itemRepository(MongoClient mongoClient) {
        return new MongoDBRepository<>(mongoClient, databaseName, "Item", ItemMongo.class);
    }
}
