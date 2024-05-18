package com.activedge.stock.core.exception;

import com.activedge.stock.core.exception.response.ErrorResponse;
import com.activedge.stock.core.exception.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class StockExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseDto<?> handleMethodArgumentException(
                MethodArgumentNotValidException exception) {
            ResponseDto<?> serviceResponse = new ResponseDto<>();
            List<ErrorResponse> errors = new ArrayList<>();
            exception.getBindingResult().getFieldErrors().forEach(error -> {
                ErrorResponse errorResponse = new ErrorResponse(error.getField(),
                        error.getDefaultMessage());
                errors.add(errorResponse);
            });
            serviceResponse.setStatus("FAILED");
            serviceResponse.setErrors(errors);
            serviceResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return serviceResponse;
        }

        @ExceptionHandler(value = { NotFoundException.class })
        @ResponseStatus(value = HttpStatus.NOT_FOUND)
        public ResponseDto<?> notFoundErrorHandler(NotFoundException ex) {
            ResponseDto<?> responseDto = new ResponseDto<>();
            responseDto.setStatus("FAILED");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseDto.setErrors(
                    Collections.singletonList(new ErrorResponse("", ex.getMessage())));
            return responseDto;
        }

    @ExceptionHandler(value = { NotAcceptableException.class })
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ResponseDto<?> notAcceptableErrorHandler(NotAcceptableException ex) {
        ResponseDto<?> responseDto = new ResponseDto<>();
        responseDto.setStatus("FAILED");
        responseDto.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        responseDto.setErrors(
                Collections.singletonList(new ErrorResponse("", ex.getMessage())));
        return responseDto;
    }


    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDto<?> badRequestExceptionHandler(BadRequestException ex) {
        ResponseDto<?> responseDto = new ResponseDto<>();
        responseDto.setStatus("FAILED");
        responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        responseDto.setErrors(
                Collections.singletonList(new ErrorResponse("", ex.getMessage())));
        return responseDto;
    }


}
