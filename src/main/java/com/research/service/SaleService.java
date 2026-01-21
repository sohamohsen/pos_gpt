package com.research.service;

import com.research.exception.BusinessRuleViolationException;
import com.research.model.*;
import com.research.repository.SaleItemRepository;
import com.research.repository.SaleRepository;

import java.time.LocalDateTime;

public class SaleService {

    private final SaleRepository saleRepo;
    private final SaleItemRepository itemRepo;
    private final ProductService productService;

    private int saleItemSeq = 1;

    public SaleService(SaleRepository saleRepo,
                       SaleItemRepository itemRepo,
                       ProductService productService) {
        this.saleRepo = saleRepo;
        this.itemRepo = itemRepo;
        this.productService = productService;
    }

    public Sale startSale(int saleId, Cashier cashier, Customer customer) {
        if (cashier.getStatus() != CashierStatus.ACTIVE) {
            throw new BusinessRuleViolationException("Cashier is inactive");
        }
        Sale sale = new Sale(saleId, cashier, customer, LocalDateTime.now());
        saleRepo.save(saleId, sale);
        return sale;
    }

    public void addItem(Sale sale, Product product, int qty) {
        if (sale.getStatus() != SaleStatus.OPEN) {
            throw new BusinessRuleViolationException("Sale not open");
        }
        productService.reduceStock(product, qty);
        SaleItem item = new SaleItem(saleItemSeq++, sale.getId(), product, qty);
        itemRepo.save(item.getId(), item);
        sale.addItem(item);
    }

    public void completeSale(Sale sale) {
        sale.complete();
    }

    public void cancelSale(Sale sale) {
        sale.cancel();
    }
}
