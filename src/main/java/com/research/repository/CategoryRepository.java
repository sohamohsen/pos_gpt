package com.research.repository;

import com.research.model.Category;

import java.util.Optional;

public class CategoryRepository extends BaseRepository<Category> {

    public Optional<Category> findByName(String name) {
        return storage.values()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
