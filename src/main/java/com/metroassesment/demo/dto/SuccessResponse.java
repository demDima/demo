package com.metroassesment.demo.dto;


public class SuccessResponse {

    private Long customerNumber;
    private int transactionStatusCode;
    private String transactionStatusDescription;

    public SuccessResponse(Long customerNumber, int code, String description) {
        this.customerNumber = customerNumber;
        this.transactionStatusCode = code;
        this.transactionStatusDescription = description;
    }

    public Long getCustomerNumber() { return customerNumber; }
    public int getTransactionStatusCode() { return transactionStatusCode; }
    public String getTransactionStatusDescription() { return transactionStatusDescription; }
}
