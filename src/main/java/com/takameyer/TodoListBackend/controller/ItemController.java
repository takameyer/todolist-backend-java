package com.takameyer.TodoListBackend.controller;

import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.entity.ItemMongo;
import com.takameyer.TodoListBackend.infrastructure.persistence.mongodb.repository.MongoDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final MongoDBRepository<ItemMongo> itemRepository;

    @Autowired
    public ItemController(MongoDBRepository<ItemMongo> itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<ItemMongo> getAllItems() {
        return itemRepository.findAll();
    }

    @PostMapping
    public ItemMongo createItem(@RequestBody ItemMongo item) {
        return itemRepository.save(item);
    }

    @GetMapping("/{id}")
    public Optional<ItemMongo> getItemById(@PathVariable String id) {
        return itemRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ItemMongo updateItem(@PathVariable String id, @RequestBody ItemMongo updatedItem) {
        return itemRepository.findById(id).map(item -> {
            ItemMongo newItem = new ItemMongo(updatedItem.id(), updatedItem.summary(), updatedItem.isComplete(), updatedItem.ownerId());
            return itemRepository.save(newItem);
        }).orElseGet(() -> {
            ItemMongo newItem = new ItemMongo(new ObjectId(), updatedItem.summary(), updatedItem.isComplete(), updatedItem.ownerId());
            return itemRepository.save(newItem);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemRepository.deleteById(id);
    }
}
