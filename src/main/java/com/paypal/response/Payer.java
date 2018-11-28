package com.paypal.response;

import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.FundingOption;
import com.paypal.api.payments.PayerInfo;

import java.io.Serializable;
import java.util.List;
public class Payer implements Serializable {

    private String paymentMethod;
    private String status;
    private String accountType;
    private String accountAge;
    private List<FundingInstrument> fundingInstruments;
    private String fundingOptionId;
    private String externalSelectedFundingInstrumentType;
    private FundingOption relatedFundingOption;
    private PayerInfo payerInfo;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountAge() {
        return accountAge;
    }

    public void setAccountAge(String accountAge) {
        this.accountAge = accountAge;
    }

    public List<FundingInstrument> getFundingInstruments() {
        return fundingInstruments;
    }

    public void setFundingInstruments(List<FundingInstrument> fundingInstruments) {
        this.fundingInstruments = fundingInstruments;
    }

    public String getFundingOptionId() {
        return fundingOptionId;
    }

    public void setFundingOptionId(String fundingOptionId) {
        this.fundingOptionId = fundingOptionId;
    }

    public String getExternalSelectedFundingInstrumentType() {
        return externalSelectedFundingInstrumentType;
    }

    public void setExternalSelectedFundingInstrumentType(String externalSelectedFundingInstrumentType) {
        this.externalSelectedFundingInstrumentType = externalSelectedFundingInstrumentType;
    }

    public FundingOption getRelatedFundingOption() {
        return relatedFundingOption;
    }

    public void setRelatedFundingOption(FundingOption relatedFundingOption) {
        this.relatedFundingOption = relatedFundingOption;
    }

    public PayerInfo getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(PayerInfo payerInfo) {
        this.payerInfo = payerInfo;
    }
}
