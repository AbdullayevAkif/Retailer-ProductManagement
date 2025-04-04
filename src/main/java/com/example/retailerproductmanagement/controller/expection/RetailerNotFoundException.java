package com.example.retailerproductmanagement.controller.expection;

public class RetailerNotFoundException extends RuntimeException {
    public RetailerNotFoundException(String message) {
        super(message);
    }
}
