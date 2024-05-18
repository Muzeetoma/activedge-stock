package com.activedge.stock.repository;

import com.activedge.stock.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StockRepository {
   private final List<Stock> stocks = new ArrayList<>();

   public Stock save(Stock stock){
      long lastIndex = stocks.isEmpty() ? 0 : stocks.get(stocks.size()-1).getId();
      Long newIndex = lastIndex + 1;
      stock.setId(newIndex);
      stocks.add(stock);
      return stock;
   }

   public Stock update(Long id, Stock newStock){
       stocks.removeIf(stock-> stock.getId().equals(id));
       stocks.add(newStock);
       return newStock;
   }

    public Optional<Stock> findById(Long stockId){
        System.out.printf("StockId->"+ stockId.getClass());
        return stocks.stream()
                .filter(stock-> Objects.equals(stock.getId(), stockId))
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
                .sorted(Comparator.comparing(Stock::getId))
                .toList();
    }

}
