package com.research.service;

import com.research.model.Payment;
import com.research.model.PaymentStatus;
import com.research.repository.PaymentRepository;

public class ReportService {

    private final PaymentRepository paymentRepository;

    public ReportService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public double totalRevenue() {
        return paymentRepository.findSuccessfulPayments()
                .stream()
                .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .mapToDouble(Payment::getAmountPaid)
                .sum();
    }
}
