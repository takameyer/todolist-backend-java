package com.takameyer.TodoListBackend.controller;

import com.takameyer.TodoListBackend.model.Item;
import com.takameyer.TodoListBackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable String id) {
        return itemRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody Item updatedItem) {
        return itemRepository.findById(id).map(item -> {
            item.setSummary(updatedItem.getSummary());
            item.setIsCompleted(updatedItem.getIsCompleted());
            return itemRepository.save(item);
        }).orElseGet(() -> {
            updatedItem.setId(id);
            return itemRepository.save(updatedItem);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemRepository.deleteById(id);
    }
}
