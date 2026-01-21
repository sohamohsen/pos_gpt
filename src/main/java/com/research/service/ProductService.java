package com.research.service;

import com.research.exception.BusinessRuleViolationException;
import com.research.exception.NotFoundException;
import com.research.model.Product;
import com.research.repository.ProductRepository;

import java.util.Collection;
import java.util.List;

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product.getId(), product);
    }

    public Product getById(int id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    public void reduceStock(Product product, int qty) {
        if (product.getStockQuantity() < qty) {
            throw new BusinessRuleViolationException("Insufficient stock");
        }
        product.reduceStock(qty);
    }

    public List<Product> search(String keyword) {
        return repository.findByName(keyword);
    }

    public Collection<Product> getAll() {
        return repository.findAll();
    }
}
