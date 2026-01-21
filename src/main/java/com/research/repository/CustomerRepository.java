package com.research.repository;

import com.research.model.Customer;

import java.util.Optional;

public class CustomerRepository extends BaseRepository<Customer> {

    public Optional<Customer> findByPhone(String phone) {
        return storage.values()
                .stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst();
    }
}
