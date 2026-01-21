package com.research.repository;

import java.util.*;

public abstract class BaseRepository<T> {
    protected Map<Integer, T> storage = new HashMap<>();

    public void save(int id, T entity) {
        if (storage.containsKey(id)) {
            throw new RuntimeException("Duplicate ID: " + id);
        }
        storage.put(id, entity);
    }

    public T findById(int id) {
        return storage.get(id);
    }

    public Collection<T> findAll() {
        return storage.values();
    }
}
