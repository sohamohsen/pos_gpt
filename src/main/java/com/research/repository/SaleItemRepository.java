package com.research.repository;

import com.research.model.SaleItem;

import java.util.List;
import java.util.stream.Collectors;

public class SaleItemRepository extends BaseRepository<SaleItem> {

    public List<SaleItem> findBySaleId(int saleId) {
        return storage.values()
                .stream()
                .filter(item -> item.getSale() == saleId)
                .collect(Collectors.toList());
    }
}
