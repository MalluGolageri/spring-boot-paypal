package com.paypal.converter;


import com.paypal.api.payments.Payment;
import com.paypal.model.CreatePaymentResponse;
import com.paypal.model.PayPalPayment;
import org.springframework.stereotype.Component;

@Component
public class CreatePaymentResponseConverter extends PayPalResponseConverter {

    public PayPalPayment convert(Payment payment) {
        CreatePaymentResponse createPaymentResponse=new CreatePaymentResponse();
        populateResponse(payment,createPaymentResponse);
        return createPaymentResponse;
    }
}
