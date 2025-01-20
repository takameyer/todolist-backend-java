package com.takameyer.TodoListBackend.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository.MongoDBItemRepository;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    @ConditionalOnProperty(name = "persist.type", havingValue = "mongodb")
    public ItemRepository mongoRepository(MongoDatabase database) {
        return new MongoDBItemRepository(database);
    }
}
