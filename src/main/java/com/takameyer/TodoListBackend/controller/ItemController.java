package com.takameyer.TodoListBackend.controller;

import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.service.ItemService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable String id) {
        return itemService.findById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody Item updatedItem) {
        return itemService.findById(id).map(item -> {
            Item newItem = new Item(updatedItem.id(), updatedItem.summary(), updatedItem.isComplete(), updatedItem.ownerId());
            return itemService.save(newItem);
        }).orElseGet(() -> {
            // If the item does not exist, create a new item with the provided ID
            // TODO: Should this be different for SQL databases?
            Item newItem = new Item(new ObjectId().toHexString(), updatedItem.summary(), updatedItem.isComplete(), updatedItem.ownerId());
            return itemService.save(newItem);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemService.deleteById(id);
    }
}
