package com.research.repository;

import com.research.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository extends BaseRepository<Product> {

    public List<Product> findByName(String keyword) {
        return storage.values()
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> findOutOfStock() {
        return storage.values()
                .stream()
                .filter(p -> p.getStockQuantity() <= 0)
                .collect(Collectors.toList());
    }
}
