package com.activedge.stock.core.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    private T results;

    private int statusCode;

    private String status;

    private List<ErrorResponse> errors;

}