package com.research.repository;

import com.research.model.Sale;
import com.research.model.SaleStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SaleRepository extends BaseRepository<Sale> {

    public List<Sale> findByStatus(SaleStatus status) {
        return storage.values()
                .stream()
                .filter(s -> s.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Sale> findByDate(LocalDate date) {
        return storage.values()
                .stream()
                .filter(s -> s.getSaleTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
