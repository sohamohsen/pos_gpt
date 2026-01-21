package com.research.service;

import com.research.exception.NotFoundException;
import com.research.model.Category;
import com.research.model.CategoryStatus;
import com.research.repository.CategoryRepository;

import java.util.Collection;

public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void add(Category category) {
        repository.save(category.getId(), category);
    }

    public void deactivate(int id) {
        Category category = getById(id);
        category.setStatus(CategoryStatus.INACTIVE);
    }

    public Category getById(int id) {
        Category category = repository.findById(id);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        return category;
    }

    public Collection<Category> getAll() {
        return repository.findAll();
    }
}
