package com.activedge.stock.repository;

import com.activedge.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    boolean existsByName(String name);
}
