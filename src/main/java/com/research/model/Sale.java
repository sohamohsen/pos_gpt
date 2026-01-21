package com.research.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private int id;
    private Cashier cashier;
    private Customer customer;
    private LocalDateTime saleTime;
    private double totalAmount;
    private SaleStatus status;
    private final List<SaleItem> items = new ArrayList<>();

    public Sale(int id, Cashier cashier, Customer customer, LocalDateTime saleTime) {
        this.id = id;
        this.cashier = cashier;
        this.customer = customer;
        this.saleTime = saleTime;
        this.status = SaleStatus.OPEN;
        this.totalAmount = 0.0;
    }

    public int getId() {
        return id;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<SaleItem> getItems() {
        return new ArrayList<>(items);
    }

    /* ================= BUSINESS OPERATIONS ================= */

    public void addItem(SaleItem item) {
        ensureOpen();
        items.add(item);
        recalculateTotal();
    }

    public void removeItem(int saleItemId) {
        ensureOpen();
        items.removeIf(i -> i.getId() == saleItemId);
        recalculateTotal();
    }

    public void cancel() {
        ensureOpen();
        this.status = SaleStatus.CANCELED;
    }

    public void complete() {
        ensureOpen();
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot complete sale with no items");
        }
        this.status = SaleStatus.COMPLETED;
    }

    private void recalculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(SaleItem::getSubTotal)
                .sum();
    }

    private void ensureOpen() {
        if (status != SaleStatus.OPEN) {
            throw new IllegalStateException("Sale is not OPEN");
        }
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }
}
