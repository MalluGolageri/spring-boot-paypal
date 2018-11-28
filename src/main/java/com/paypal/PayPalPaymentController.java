package com.paypal;


import com.paypal.api.payments.Payment;
import com.paypal.response.PaymentResponse;
import com.paypal.response.PayPalResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("paypl")
public class PayPalPaymentController {

    @Autowired
    private PayPalResponseConverter payPalResponseConverter;

    @Autowired
    private PayPalClient payPalClient;

    @PostMapping("/make/payment")
    public Map<String, Object> makePayment(@RequestBody MakePaymentRequest makePaymentRequest) {
        return payPalClient.createPayment(makePaymentRequest.getTotalAmount());
    }

    @PostMapping("/complete/payment")
    public PaymentResponse completePayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = payPalClient.completePayment(paymentRequest.getPaymentId(), paymentRequest.getPayerId());
        PaymentResponse paymentResponse = payPalResponseConverter.convert(payment);
        return paymentResponse;
    }
}