package com.paypal.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutePaymentRequest {

    private String paymentId;

    private String payerId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayerId() {
        return payerId;
    }
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

}
