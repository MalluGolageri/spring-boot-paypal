package com.paypal.response;

import java.io.Serializable;
import java.util.List;

public class Transaction implements Serializable {
    private List<com.paypal.api.payments.Transaction> transactions;

    public List<com.paypal.api.payments.Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<com.paypal.api.payments.Transaction> transactions) {
        this.transactions = transactions;
    }
}
