package com.paypal.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.paypal.api.payments.BillingAgreementToken;

import java.io.Serializable;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutePaymentResponse extends PayPalPayment implements Serializable {

}

