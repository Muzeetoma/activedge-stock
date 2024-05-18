package com.activedge.stock.core.initializer;

import com.activedge.stock.dto.CreateStockRequest;
import com.activedge.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockInitializer implements InitializingBean {

    private final StockService stockService;

    @Override
    public void afterPropertiesSet() {
        List<CreateStockRequest> stockRequestList = List.of(
                new CreateStockRequest("Mobil", BigDecimal.valueOf(120000.88)),
                new CreateStockRequest("NLNG", BigDecimal.valueOf(135.91)),
                new CreateStockRequest("NNPC", BigDecimal.valueOf(2891.03)),
                new CreateStockRequest("Dangote Sugar", BigDecimal.valueOf(1221.34)),
                new CreateStockRequest("Dangote Cement", BigDecimal.valueOf(1221.34))
        );
        stockRequestList.forEach(stockService::create);
    }
}
