package com.metroassesment.demo.dto;

public class ErrorResponse {

    private int transactionStatusCode;
    private String transactionStatusDescription;

    public ErrorResponse(int code, String description) {
        this.transactionStatusCode = code;
        this.transactionStatusDescription = description;
    }

    public int getTransactionStatusCode() { return transactionStatusCode; }
    public String getTransactionStatusDescription() { return transactionStatusDescription; }
}
