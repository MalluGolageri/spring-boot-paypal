package com.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.paypal.api.payments.Address;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PotentialPayerInfo implements Serializable {

    private String email;
    private String externalRememberMeId;
    private String accountNumber;
    private Address billingAddress;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExternalRememberMeId() {
        return externalRememberMeId;
    }

    public void setExternalRememberMeId(String externalRememberMeId) {
        this.externalRememberMeId = externalRememberMeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
