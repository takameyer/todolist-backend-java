package com.takameyer.TodoListBackend.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository.MongoDBItemRepository;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

//    public ItemService(@Value("${mongodb.uri}") String mongoUri, @Value("${mongodb.database}") String databaseName) {
//        MongoClient mongoClient = MongoClients.create(mongoUri);
//        this.itemRepository =  new MongoDBItemRepository(mongoClient, databaseName, "Item" );
//    }

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public boolean update(Item item) {
        return itemRepository.update(item.id(), item);
    }

    public List<Item> saveAll(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Optional<Item> findById(String id) {
        return itemRepository.findById(id);
    }

    public void deleteById(String id) {
        itemRepository.deleteById(id);
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
