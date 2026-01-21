package com.research.repository;

import com.research.model.Payment;
import com.research.model.PaymentStatus;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepository extends BaseRepository<Payment> {

    public List<Payment> findSuccessfulPayments() {
        return storage.values()
                .stream()
                .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .collect(Collectors.toList());
    }
}
