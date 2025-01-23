package com.takameyer.TodoListBackend.infrastructure.config;

import com.mongodb.client.MongoDatabase;
import com.takameyer.TodoListBackend.infrastructure.persistence.h2.respository.H2ItemRepository;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository.MongoDBItemRepository;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {

    @Bean(name = "itemRepository")
    @ConditionalOnProperty(prefix = "persist", name = "type", havingValue = "mongodb")
    public ItemRepository mongoRepository(MongoDatabase database) {
        return new MongoDBItemRepository(database);
    }

    @Bean(name = "itemRepository")
    @ConditionalOnProperty(prefix = "persist", name = "type", havingValue = "h2")
    public ItemRepository h2Repository(H2ItemRepository h2ItemRepository) {
        return h2ItemRepository;
    }
}
