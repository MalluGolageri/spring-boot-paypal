package com.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayPalClient {
    private String clientId = "AQ8RRyaOCg7cwJa5eR9qEDFMAvKBSpzkZY5JB8T8mmZvhdwyFOv72eKLUC6Q3ix-m-N7_aEYfnHQpRKp";
    private String clientSecret = "EJ_s6F5f_Z_cQdeVnzm-8-GLUZCFX63sUUmf60OzMh-l2TJmxHXtOnJQqZmwtUFa-EpQrpm7-5ZTmnsE";

    public Map<String, Object> createPayment(String sum){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/paypal/confirm");
        redirectUrls.setReturnUrl("http://localhost:8080/paypal/confirm?amount=" + sum);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectURI = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectURI = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirectURI", redirectURI);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }


    public Payment completePayment(String paymentId, String payerId){
        Payment payment = new Payment();
        payment.setId(paymentId.replaceAll("^\"|\"$", ""));
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId.replaceAll("^\"|\"$", ""));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                return createdPayment;
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return null;
    }



}
