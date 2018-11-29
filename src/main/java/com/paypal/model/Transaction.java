package com.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.paypal.api.payments.TransactionBase;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction extends TransactionBase implements Serializable {

    private List<com.paypal.api.payments.Transaction> transactions;

    public List<com.paypal.api.payments.Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<com.paypal.api.payments.Transaction> transactions) {
        this.transactions = transactions;
    }
}
