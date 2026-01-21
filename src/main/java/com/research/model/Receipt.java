package com.research.model;

import java.time.LocalDateTime;

public class Receipt {

    private int id;
    private Sale sale;
    private LocalDateTime receiptTime;
    private boolean printed;

    public Receipt(int id, Sale sale) {
        if (sale.getStatus() != SaleStatus.COMPLETED) {
            throw new IllegalStateException("Receipt can only be generated for COMPLETED sales");
        }
        this.id = id;
        this.sale = sale;
        this.receiptTime = LocalDateTime.now();
        this.printed = false;
    }

    public int getId() {
        return id;
    }

    public Sale getSale() {   // ✅ THIS FIXES THE ERROR
        return sale;
    }

    public LocalDateTime getReceiptTime() {
        return receiptTime;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void print() {
        this.printed = true;
        System.out.println("========== RECEIPT ==========");
        System.out.println("Sale ID: " + sale.getId());
        System.out.println("Total: " + sale.getTotalAmount());
        System.out.println("Time: " + receiptTime);
        System.out.println("=============================");
    }
}
