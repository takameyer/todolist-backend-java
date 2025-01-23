package com.takameyer.TodoListBackend.infrastructure.persistence.h2.respository;

import com.takameyer.TodoListBackend.domain.model.Item;

import com.takameyer.TodoListBackend.infrastructure.persistence.h2.entity.ItemH2;
import com.takameyer.TodoListBackend.infrastructure.persistence.shared.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class H2ItemRepository implements ItemRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public H2ItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Item save(Item item) {
        ItemH2 itemH2 = new ItemH2(item.id() != null ? Long.parseLong(item.id()) : null, item.summary(), item.isComplete(), item.ownerId());
        entityManager.persist(itemH2);
        return new Item(itemH2.getId().toString(), itemH2.getSummary(), itemH2.getIsComplete(), itemH2.getOwnerId());
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        List<ItemH2> itemH2s = items.stream()
                .map(item -> new ItemH2(item.id() != null ? Long.parseLong(item.id()) : null, item.summary(), item.isComplete(), item.ownerId()))
                .toList();
        itemH2s.forEach(entityManager::persist);
        return itemH2s.stream()
                .map(savedItem -> new Item(savedItem.getId().toString(), savedItem.getSummary(), savedItem.getIsComplete(), savedItem.getOwnerId()))
                .toList();
    }

    @Override
    public Optional<Item> findById(String id) {
        ItemH2 itemH2 = entityManager.find(ItemH2.class, Long.parseLong(id));
        return Optional.ofNullable(itemH2).map(item -> new Item(item.getId().toString(), item.getSummary(), item.getIsComplete(), item.getOwnerId()));
    }

    @Override
    public boolean deleteById(String id) {
        ItemH2 itemH2 = entityManager.find(ItemH2.class, Long.parseLong(id));
        if (itemH2 != null) {
            entityManager.remove(itemH2);
            return true;
        }
        return false;

    }

    @Override
    public long deleteAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemH2> query = cb.createQuery(ItemH2.class);
        Root<ItemH2> root = query.from(ItemH2.class); // Ensure this line is present
        query.select(root);
        List<ItemH2> items = entityManager.createQuery(query).getResultList();
        items.forEach(entityManager::remove);
        return items.size();
    }

    @Override
    public List<Item> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemH2> query = cb.createQuery(ItemH2.class);
        Root<ItemH2> root = query.from(ItemH2.class); // Ensure this line is present
        query.select(root);
        List<ItemH2> items = entityManager.createQuery(query).getResultList();
        return items.stream()
                .map(item -> new Item(item.getId().toString(), item.getSummary(), item.getIsComplete(), item.getOwnerId()))
                .toList();

    }

    @Override
    public List<Item> findByField(String fieldName, Object value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemH2> query = cb.createQuery(ItemH2.class);
        Root<ItemH2> root = query.from(ItemH2.class);

        Predicate predicate = cb.equal(root.get(fieldName), value);
        query.where(predicate);

        List<ItemH2> results = entityManager.createQuery(query).getResultList();
        return results.stream().map(item -> new Item(item.getId().toString(), item.getSummary(), item.getIsComplete(), item.getOwnerId())).toList();
    }

    @Override
    public boolean update(String id, Item item) {
        ItemH2 itemH2 = entityManager.find(ItemH2.class, Long.parseLong(id));
        if (itemH2 != null) {
            itemH2 = new ItemH2(Long.parseLong(id), item.summary(), item.isComplete(), item.ownerId());
            entityManager.merge(itemH2);
            return true;
        }
        return false;
    }

    @Override
    public long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ItemH2> root = query.from(ItemH2.class);
        query.select(cb.count(root));
        return entityManager.createQuery(query).getSingleResult();

    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }
}
