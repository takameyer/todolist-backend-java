package com.takameyer.TodoListBackend.infrastructure.persistence.h2.respository;

import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.infrastructure.persistence.h2.entity.ItemH2;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

//public class H2ItemRepositoryImpl implements ItemRepository {
//
//    private final H2ItemRepository h2ItemRepository;
//    private EntityManager entityManager;
//
//    @Autowired
//    public H2ItemRepositoryImpl(H2ItemRepository h2ItemRepository) {
//        this.h2ItemRepository = h2ItemRepository;
//    }
//
//    @Autowired
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public Item save(Item item) {
//        ItemH2 itemH2 = new ItemH2(item.id() != null ? Long.parseLong(item.id()) : null, item.summary(), item.isComplete(), item.ownerId());
//        ItemH2 savedItem = h2ItemRepository.save(itemH2);
//        return new Item(savedItem.id().toString(), savedItem.summary(), savedItem.isComplete(), savedItem.ownerId());
//
//    }
//
//    @Override
//    public List<Item> saveAll(List<Item> items) {
//        List<ItemH2> itemH2s = items.stream()
//                .map(item -> new ItemH2(item.id() != null ? Long.parseLong(item.id()) : null, item.summary(), item.isComplete(), item.ownerId()))
//                .toList();
//        List<ItemH2> savedItems = h2ItemRepository.saveAll(itemH2s);
//        return savedItems.stream()
//                .map(savedItem -> new Item(savedItem.id().toString(), savedItem.summary(), savedItem.isComplete(), savedItem.ownerId()))
//                .toList();
//    }
//
//    @Override
//    public Optional<Item> findById(String id) {
//        Optional<ItemH2> itemH2 = h2ItemRepository.findById(Long.parseLong(id));
//        return itemH2.map(item -> new Item(item.id().toString(), item.summary(), item.isComplete(), item.ownerId()));
//    }
//
//    @Override
//    public boolean deleteById(String id) {
//        h2ItemRepository.deleteById(Long.parseLong(id));
//        return true;
//    }
//
//    @Override
//    public long deleteAll() {
//        long count = h2ItemRepository.count();
//        h2ItemRepository.deleteAll();
//        return count;
//    }
//
//    @Override
//    public List<Item> findAll() {
//        List<ItemH2> items = h2ItemRepository.findAll();
//        return items.stream().map(item -> new Item(item.id().toString(), item.summary(), item.isComplete(), item.ownerId())).toList();
//    }
//
//    @Override
//    public List<Item> findByField(String fieldName, Object value) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ItemH2> query = cb.createQuery(ItemH2.class);
//        Root<ItemH2> root = query.from(ItemH2.class);
//
//        Predicate predicate = cb.equal(root.get(fieldName), value);
//        query.where(predicate);
//
//        List<ItemH2> results = entityManager.createQuery(query).getResultList();
//        return results.stream().map(item -> new Item(item.id().toString(), item.summary(), item.isComplete(), item.ownerId())).toList();
//    }
//
//    @Override
//    public boolean update(String id, Item item) {
//        Optional<ItemH2> itemH2 = h2ItemRepository.findById(Long.parseLong(id));
//        if (itemH2.isPresent()) {
//            ItemH2 updatedItem = new ItemH2(Long.parseLong(id), item.summary(), item.isComplete(), item.ownerId());
//            h2ItemRepository.save(updatedItem);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public long count() {
//        return h2ItemRepository.count();
//    }
//
//    @Override
//    public boolean existsById(String id) {
//        return h2ItemRepository.existsById(Long.parseLong(id));
//    }
//}
