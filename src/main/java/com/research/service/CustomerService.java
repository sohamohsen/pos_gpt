package com.research.service;

import com.research.exception.NotFoundException;
import com.research.model.Customer;
import com.research.repository.CustomerRepository;

import java.util.Collection;

public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void add(Customer customer) {
        repository.save(customer.getId(), customer);
    }

    public Customer getById(int id) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }
        return customer;
    }

    public Collection<Customer> getAll() {
        return repository.findAll();
    }
}
