package com.paypal.response;

import com.paypal.api.payments.Currency;

import java.io.Serializable;

public class CreditFinancingOffered implements Serializable {

    private Currency totalCost;
    private float term;
    private Currency monthlyPayment;
    private Currency totalInterest;
    private Boolean payerAcceptance;
    private Boolean cartAmountImmutable;

    public Currency getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Currency totalCost) {
        this.totalCost = totalCost;
    }

    public float getTerm() {
        return term;
    }

    public void setTerm(float term) {
        this.term = term;
    }

    public Currency getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Currency monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Currency getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Currency totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Boolean getPayerAcceptance() {
        return payerAcceptance;
    }

    public void setPayerAcceptance(Boolean payerAcceptance) {
        this.payerAcceptance = payerAcceptance;
    }

    public Boolean getCartAmountImmutable() {
        return cartAmountImmutable;
    }

    public void setCartAmountImmutable(Boolean cartAmountImmutable) {
        this.cartAmountImmutable = cartAmountImmutable;
    }
}
