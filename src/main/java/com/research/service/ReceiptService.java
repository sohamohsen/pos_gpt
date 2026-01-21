package com.research.service;

import com.research.model.Receipt;
import com.research.model.Sale;
import com.research.repository.ReceiptRepository;

public class ReceiptService {

    private final ReceiptRepository repository;
    private int receiptSeq = 1;

    public ReceiptService(ReceiptRepository repository) {
        this.repository = repository;
    }

    public Receipt generate(Sale sale) {
        Receipt receipt = new Receipt(receiptSeq++, sale);
        repository.save(receipt.getId(), receipt);
        return receipt;
    }
}
