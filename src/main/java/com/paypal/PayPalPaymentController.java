package com.paypal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.converter.CreatePaymentResponseConverter;
import com.paypal.converter.ExecutePaymentResponseConverter;
import com.paypal.converter.UpdatePaymentResponseConverter;
import com.paypal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/paypl")
public class PayPalPaymentController {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Autowired
    private ExecutePaymentResponseConverter executePaymentResponseConverter;

    @Autowired
    private CreatePaymentResponseConverter createPaymentResponseConverter;

    @Autowired
    private UpdatePaymentResponseConverter updatePaymentResponseConverter;

    @Autowired
    private PayPalClient payPalClient;



    @PostMapping("/checkout/payment")
    public CreatePaymentResponse checkoutPayment(@RequestBody String createPaymentRequest) throws IOException {
        CreatePaymentRequest paymentRequest=JSON_MAPPER.readValue(createPaymentRequest,CreatePaymentRequest.class);
        Payment payment=payPalClient.checkoutPayment(paymentRequest);
        if (payment != null) return (CreatePaymentResponse) createPaymentResponseConverter.convert(payment);
        return null;
    }

    @PostMapping("/authorize/payment")
    public ExecutePaymentResponse authorizePayment(@RequestBody ExecutePaymentRequest executePaymentRequest) {
        Payment payment = payPalClient.completePayment(executePaymentRequest.getPaymentId(), executePaymentRequest.getPayerId());
        if (payment != null) return (ExecutePaymentResponse) executePaymentResponseConverter.convert(payment);
        return null;
    }

    @RequestMapping(value = "/update/payment/{paymentId}",method = RequestMethod.POST)
    public UpdatePaymentResponse updatePayment(@RequestBody List<Patch> patches,
                                             @PathVariable("paymentId") String paymentId) throws IOException, PayPalRESTException {
        Payment payment = payPalClient.updatePayment(paymentId, patches);
        if (payment != null) return (UpdatePaymentResponse) updatePaymentResponseConverter.convert(payment);
        return null;

    }

    @RequestMapping(value = "/payment/{paymentId}",method = RequestMethod.GET)
    public PayPalPayment getPayment(@PathVariable("paymentId") String paymentId) throws PayPalRESTException {
        Payment payment = payPalClient.getPaymnent(paymentId);
        return updatePaymentResponseConverter.convert(payment);
    }

}