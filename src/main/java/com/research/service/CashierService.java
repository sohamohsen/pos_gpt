package com.research.service;

import com.research.exception.NotFoundException;
import com.research.model.Cashier;
import com.research.model.CashierStatus;
import com.research.repository.CashierRepository;

import java.util.Collection;

public class CashierService {

    private final CashierRepository repository;
    private final ValidationService validation;

    public CashierService(CashierRepository repository, ValidationService validation) {
        this.repository = repository;
        this.validation = validation;
    }

    public void addCashier(Cashier cashier) {
        repository.save(cashier.getId(), cashier);
    }

    public void deactivate(int id) {
        Cashier cashier = getById(id);
        cashier.setStatus(CashierStatus.INACTIVE);
    }

    public void activate(int id) {
        Cashier cashier = getById(id);
        cashier.setStatus(CashierStatus.ACTIVE);
    }

    public Cashier getById(int id) {
        Cashier cashier = repository.findById(id);
        if (cashier == null) {
            throw new NotFoundException("Cashier not found");
        }
        return cashier;
    }

    public Collection<Cashier> getAll() {
        return repository.findAll();
    }
}
