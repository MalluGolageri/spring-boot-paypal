package com.paypal.converter;

import com.paypal.api.payments.Payment;
import com.paypal.model.PayPalPayment;
import com.paypal.model.UpdatePaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdatePaymentResponseConverter extends PayPalResponseConverter {

    @Override
    public PayPalPayment convert(Payment payment) {
        UpdatePaymentResponse paymentResponse=new UpdatePaymentResponse();
        populateResponse(payment,paymentResponse);
        return paymentResponse;
    }
}
