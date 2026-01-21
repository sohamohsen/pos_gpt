package com.research.repository;

import com.research.model.Cashier;

import java.util.Optional;

public class CashierRepository extends BaseRepository<Cashier> {

    public Optional<Cashier> findByEmail(String email) {
        return storage.values()
                .stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Cashier> findByPhone(String phone) {
        return storage.values()
                .stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst();
    }
}
