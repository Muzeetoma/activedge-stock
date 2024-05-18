package com.activedge.stock.repository;

import com.activedge.stock.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StockRepository {
   private final List<Stock> stocks = new ArrayList<>();

   public void save(Stock stock){
      Number lastIndex = stocks.isEmpty() ? 0 : stocks.get(stocks.size()-1).getId();
      Number newIndex = lastIndex.longValue() + 1;
      stock.setId(newIndex);
      stocks.add(stock);
   }

    public Optional<Stock> findById(Number stockId){
        return stocks.stream()
                .filter(stock-> stock.getId().equals(stockId))
                .findFirst();
    }

    public boolean existsByName(String stockName){
        return stocks.stream().anyMatch(stock-> stock.getName().equals(stockName));
    }

    public List<Stock> findAll(Long skip, Long limit) {

        long skipNonNull = Objects.requireNonNullElse(skip, 0L);
        long limitNonNull = Objects.requireNonNullElse(limit, 20L);

        if (skipNonNull < 0) skipNonNull = 0;

        return stocks.stream()
                .skip(skipNonNull)
                .limit(limitNonNull)
                .toList();
    }

}
