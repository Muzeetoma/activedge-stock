package com.activedge.stock.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateStockRequest {

    @NotBlank()
    private String name;

    @NotNull()
    @DecimalMin(value = "0")
    private BigDecimal currentPrice;
}
