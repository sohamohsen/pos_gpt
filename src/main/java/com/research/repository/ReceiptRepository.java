package com.research.repository;

import com.research.model.Receipt;

import java.util.Optional;

public class ReceiptRepository extends BaseRepository<Receipt> {

    /**
     * Find receipt by sale ID.
     * Ensures one receipt per sale.
     */
    public Optional<Receipt> findBySaleId(int saleId) {
        return storage.values()
                .stream()
                .filter(r -> r.getSale().getId() == saleId)
                .findFirst();
    }

    /**
     * Check if a receipt already exists for a sale.
     */
    public boolean existsBySaleId(int saleId) {
        return storage.values()
                .stream()
                .anyMatch(r -> r.getSale().getId() == saleId);
    }
}
