package com.activedge.stock.core.exception;


public class NotAcceptableException extends RuntimeException {

    public NotAcceptableException(String message) {
        super(message);
    }

    public NotAcceptableException() {
        super("not acceptable request");
    }

}

