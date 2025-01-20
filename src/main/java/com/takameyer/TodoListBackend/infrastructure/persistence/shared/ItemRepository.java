package com.takameyer.TodoListBackend.infrastructure.persistence.shared;

import com.takameyer.TodoListBackend.domain.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository {
    Item save(Item item);
    List<Item> saveAll(List<Item> items);
    Optional<Item> findById(String id);
    boolean deleteById(String id);
    long deleteAll();
    List<Item> findAll();
    List<Item> findByField(String fieldName, Object value);
    boolean update(String id, Item item);
    long count();
    boolean existsById(String id);
}
