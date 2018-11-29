package com.paypal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.Payment;
//import com.paypal.converter.PayPalRequestConverter;
import com.paypal.converter.CreatePaymentResponseConverter;
import com.paypal.model.CreatePaymentRequest;
import com.paypal.model.CreatePaymentResponse;
import com.paypal.model.ExecutePaymentRequest;
import com.paypal.model.ExecutePaymentResponse;
import com.paypal.converter.PayPalResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("paypl")
public class PayPalPaymentController {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Autowired
    private PayPalResponseConverter payPalResponseConverter;

    @Autowired
    private CreatePaymentResponseConverter paymentResponseConverter;

    @Autowired
    private PayPalClient payPalClient;



    @PostMapping("/checkout/payment")
    public CreatePaymentResponse checkoutPayment(@RequestBody String createPaymentRequest) throws IOException {
        CreatePaymentRequest paymentRequest=JSON_MAPPER.readValue(createPaymentRequest,CreatePaymentRequest.class);
        Payment payment=payPalClient.checkoutPayment(paymentRequest);
        if(payment!=null){
            return (CreatePaymentResponse) paymentResponseConverter.convert(payment);
        }
        return null;
    }

    @PostMapping("/authorize/payment")
    public ExecutePaymentResponse authorizePayment(@RequestBody ExecutePaymentRequest executePaymentRequest) {
        Payment payment = payPalClient.completePayment(executePaymentRequest.getPaymentId(), executePaymentRequest.getPayerId());
        if(payment!=null){
            return (ExecutePaymentResponse) payPalResponseConverter.convert(payment);
        }
        return null;
    }
}