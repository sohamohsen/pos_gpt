package com.research.service;

import com.research.model.Payment;
import com.research.model.PaymentMethod;
import com.research.model.Sale;
import com.research.repository.PaymentRepository;

public class PaymentService {

    private final PaymentRepository repository;
    private int paymentSeq = 1;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment pay(Sale sale, PaymentMethod method, double amount) {
        Payment payment = new Payment(paymentSeq++, sale, method, amount);
        repository.save(payment.getId(), payment);
        return payment;
    }
}
