package com.activedge.stock.dto;

import com.activedge.stock.core.exception.BadRequestException;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class UpdateStockRequest {
    private String name;
    private BigDecimal currentPrice;

    public void validate(){
        if(name.isBlank() && Objects.isNull(currentPrice)){
            throw new BadRequestException("Nothing to update");
        }
    }
}
