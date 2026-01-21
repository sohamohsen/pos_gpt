package com.research.model;

import java.time.LocalDateTime;

public class Payment {

    private int id;
    private Sale sale;
    private PaymentMethod method;
    private double amountPaid;
    private LocalDateTime paymentTime;
    private PaymentStatus status;

    public Payment(int id, Sale sale, PaymentMethod method, double amountPaid) {
        this.id = id;
        this.sale = sale;
        this.method = method;
        this.amountPaid = amountPaid;
        this.paymentTime = LocalDateTime.now();
        validate();
    }

    private void validate() {
        if (sale.getStatus() != SaleStatus.COMPLETED) {
            status = PaymentStatus.FAILED;
            throw new IllegalStateException("Sale must be COMPLETED before payment");
        }
        if (amountPaid < sale.getTotalAmount()) {
            status = PaymentStatus.FAILED;
            throw new IllegalStateException("Insufficient payment amount");
        }
        status = PaymentStatus.SUCCESS;
    }

    public int getId() {
        return id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public double getAmountPaid() {   // ✅ THIS FIXES YOUR ERROR
        return amountPaid;
    }

    public double getChange() {
        return amountPaid - sale.getTotalAmount();
    }
}
