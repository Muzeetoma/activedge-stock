package com.activedge.stock.service;

import com.activedge.stock.dto.CreateStockRequest;
import com.activedge.stock.dto.UpdateStockRequest;
import com.activedge.stock.core.exception.NotAcceptableException;
import com.activedge.stock.core.exception.NotFoundException;
import com.activedge.stock.model.Stock;
import com.activedge.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> getAll(long page,long limit){
        long skip = page * limit;
        return stockRepository.findAll(skip,limit);
    }

    public Stock getById(Number stockId){
        return stockRepository.findById(stockId)
                .orElseThrow(()-> new NotFoundException(
                String.format("Stock with id %s not found", stockId)));
    }

    public void create(CreateStockRequest createStockRequest){
        if(stockRepository.existsByName(createStockRequest.getName())){
            throw new NotAcceptableException(
                    String.format("A stock with the name %s exist already",
                            createStockRequest.getName()
                    ));
        }
        Stock stock = new Stock();
        stock.setCurrentPrice(createStockRequest.getCurrentPrice());
        stock.setName(createStockRequest.getName());
        stock.setCreatedAt(Instant.now());
        stockRepository.save(stock);
    }

    public void update(Number stockId,UpdateStockRequest updateStockRequest){
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
        stockRepository.save(stock);
    }
}
