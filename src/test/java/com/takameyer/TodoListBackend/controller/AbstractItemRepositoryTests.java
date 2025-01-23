package com.takameyer.TodoListBackend.controller;


import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import com.takameyer.TodoListBackend.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
abstract class AbstractItemRepositoryTests {
    @Autowired
    protected ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService.deleteAll();
    }

    @Test
    void shouldSaveAndRetrieveItems() {
        Item item = new Item(null, "Buy milk", false, "owner1");
        itemService.save(item);

        List<Item> items = itemService.findAll();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).summary()).isEqualTo("Buy milk");
    }
}
