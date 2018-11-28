package com.paypal.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.paypal.api.payments.BillingAgreementToken;
import com.paypal.api.payments.Transaction;

import java.io.Serializable;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse implements Serializable {

    private String id;
    private String intent;
    private Payer payer;
    private PotentialPayerInfo potentialPayerInfo;
    private Payee payee;
    private String cart;
    private List<com.paypal.response.Transaction> transactions;
    private List<Error> failedTransactions;
    private List<com.paypal.api.payments.BillingAgreementToken> billingAgreementTokens;
    private CreditFinancingOffered creditFinancingOffered;
    private String state;
    private String experienceProfileId;
    private String noteToPayer;
    private RedirectUrls redirectUrls;
    private String failureReason;
    private String createTime;
    private String updateTime;
    private List<Links> links;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public PotentialPayerInfo getPotentialPayerInfo() {
        return potentialPayerInfo;
    }

    public void setPotentialPayerInfo(PotentialPayerInfo potentialPayerInfo) {
        this.potentialPayerInfo = potentialPayerInfo;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public List<com.paypal.response.Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<com.paypal.response.Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Error> getFailedTransactions() {
        return failedTransactions;
    }

    public void setFailedTransactions(List<Error> failedTransactions) {
        this.failedTransactions = failedTransactions;
    }

    public List<com.paypal.api.payments.BillingAgreementToken> getBillingAgreementTokens() {
        return billingAgreementTokens;
    }

    public void setBillingAgreementTokens(List<BillingAgreementToken> billingAgreementTokens) {
        this.billingAgreementTokens = billingAgreementTokens;
    }

    public CreditFinancingOffered getCreditFinancingOffered() {
        return creditFinancingOffered;
    }

    public void setCreditFinancingOffered(CreditFinancingOffered creditFinancingOffered) {
        this.creditFinancingOffered = creditFinancingOffered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExperienceProfileId() {
        return experienceProfileId;
    }

    public void setExperienceProfileId(String experienceProfileId) {
        this.experienceProfileId = experienceProfileId;
    }

    public String getNoteToPayer() {
        return noteToPayer;
    }

    public void setNoteToPayer(String noteToPayer) {
        this.noteToPayer = noteToPayer;
    }

    public RedirectUrls getRedirectUrls() {
        return redirectUrls;
    }

    public void setRedirectUrls(RedirectUrls redirectUrls) {
        this.redirectUrls = redirectUrls;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }
}

