package com.activedge.stock.controller;

import com.activedge.stock.dto.CreateStockRequest;
import com.activedge.stock.dto.UpdateStockRequest;
import com.activedge.stock.model.Stock;
import com.activedge.stock.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping()
    public List<Stock> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Long limit
    ) {
        return stockService.getAll(page,limit);
    }

    @GetMapping("/{id}")
    public Stock getById(@PathVariable(value = "id") Long id) {
        return stockService.getById(id);
    }


    @PostMapping()
    public Stock create(@Valid() @RequestBody CreateStockRequest createStockRequest) {
        return stockService.create(createStockRequest);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable(value = "id") Long id,
                        @Valid() @RequestBody UpdateStockRequest updateStockRequest) {
        return stockService.update(id,updateStockRequest);
    }
}
