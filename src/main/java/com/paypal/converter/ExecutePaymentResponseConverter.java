package com.paypal.converter;

import com.paypal.api.payments.Payment;
import com.paypal.model.ExecutePaymentResponse;
import com.paypal.model.PayPalPayment;
import org.springframework.stereotype.Component;

@Component
public class ExecutePaymentResponseConverter extends PayPalResponseConverter {

    public PayPalPayment convert(Payment payment) {
        ExecutePaymentResponse paymentResponse = new ExecutePaymentResponse();
        populateResponse(payment, paymentResponse);
        return paymentResponse;
    }

}
