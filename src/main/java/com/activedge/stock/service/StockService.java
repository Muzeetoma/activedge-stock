package com.activedge.stock.service;

import com.activedge.stock.core.exception.NotAcceptableException;
import com.activedge.stock.core.exception.NotFoundException;
import com.activedge.stock.dto.CreateStockRequest;
import com.activedge.stock.dto.UpdateStockRequest;
import com.activedge.stock.model.Stock;
import com.activedge.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Page<Stock> getAll(int page,int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return stockRepository.findAll(pageable);
    }

    public List<Stock> getStocks(int page,int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return stockRepository.findAll(pageable).toList();
    }

    public Stock getById(Long stockId){
        return stockRepository.findById(stockId)
                .orElseThrow(()-> new NotFoundException(
                String.format("Stock with id %s not found", stockId)));
    }

    public Stock create(CreateStockRequest createStockRequest){
        if(stockRepository.existsByName(createStockRequest.getName())){
            throw new NotAcceptableException(
                    String.format("A stock with the name %s exists already",
                            createStockRequest.getName()
                    ));
        }
        Stock stock = new Stock();
        stock.setCurrentPrice(createStockRequest.getCurrentPrice());
        stock.setName(createStockRequest.getName());
        stock.setCreatedAt(Instant.now());
        return stockRepository.save(stock);
    }

    public Stock update(Long stockId, UpdateStockRequest updateStockRequest){
        updateStockRequest.validate();
        Stock stock = stockRepository.findById(stockId)
                      .orElseThrow(()-> new NotFoundException(
                              String.format("Stock with id %s not found", stockId)));

        if(Objects.nonNull(updateStockRequest.getName())) {
            if(stockRepository.existsByName(updateStockRequest.getName())){
                throw new NotAcceptableException(
                        String.format("A stock with the name %s exists already",
                                updateStockRequest.getName()
                        ));
            }
            stock.setName(updateStockRequest.getName());
        }

        if(Objects.nonNull(updateStockRequest.getCurrentPrice())) {
            stock.setCurrentPrice(updateStockRequest.getCurrentPrice());
        }
        stock.setUpdatedAt(Instant.now());
        return stockRepository.save(stock);
    }
}
