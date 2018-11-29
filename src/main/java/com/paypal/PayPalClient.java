package com.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.model.CreatePaymentRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayPalClient {
    private String clientId = "AQ8RRyaOCg7cwJa5eR9qEDFMAvKBSpzkZY5JB8T8mmZvhdwyFOv72eKLUC6Q3ix-m-N7_aEYfnHQpRKp";
    private String clientSecret = "EJ_s6F5f_Z_cQdeVnzm-8-GLUZCFX63sUUmf60OzMh-l2TJmxHXtOnJQqZmwtUFa-EpQrpm7-5ZTmnsE";


    public Payment checkoutPayment(CreatePaymentRequest paymentRequest){
        Payer payer = new Payer();
        payer.setPaymentMethod(paymentRequest.getPayer().getPaymentMethod());

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);


        List<Transaction> transactions = new ArrayList<Transaction>();
        List<com.paypal.model.Transaction> paypalTransactions=paymentRequest.getTransactions();
        for(com.paypal.model.Transaction trans:paypalTransactions){
            Transaction transaction1=new Transaction();
            transaction1.setTransactions(trans.getTransactions());
            transaction1.setAmount(trans.getAmount());
            transaction1.setCustom(trans.getCustom());
            transaction1.setDescription(trans.getCustom());
            transaction1.setExternalFunding(trans.getExternalFunding());
            transaction1.setInvoiceNumber(trans.getInvoiceNumber());
            transaction1.setItemList(trans.getItemList());
            transaction1.setNoteToPayee(trans.getNoteToPayee());
            transaction1.setNotifyUrl(trans.getNotifyUrl());
            transaction1.setOrderUrl(trans.getOrderUrl());
            transaction1.setPayee(trans.getPayee());
            transaction1.setPaymentOptions(trans.getPaymentOptions());
            transaction1.setPurchaseUnitReferenceId(trans.getPurchaseUnitReferenceId());
            transaction1.setReferenceId(trans.getReferenceId());
            transaction1.setRelatedResources(trans.getRelatedResources());
            transaction1.setSoftDescriptor(trans.getSoftDescriptor());
            transaction1.setSoftDescriptorCity(trans.getSoftDescriptorCity());
            transactions.add(transaction1);
        }

        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();

        com.paypal.model.RedirectUrls urls=paymentRequest.getRedirectUrls();
        redirectUrls.setCancelUrl(urls.getCancelUrl());
        redirectUrls.setReturnUrl(urls.getReturnUrl()+"?amount="+getAmount(transactions));
//        redirectUrls.setCancelUrl("http://localhost:8080/paypal/confirm");//cancel Url
//        redirectUrls.setReturnUrl("http://localhost:8080/paypal/confirm?amount=" + paymentRequest.getTotalAmount());
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment=null;
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return createdPayment;
    }

    private String getAmount(List<Transaction> transactions) {
        for (Transaction transaction:transactions){
            Amount amount=transaction.getAmount();
            if(amount!=null){
                return amount.getTotal();
            }
        }
        return "0";
    }


    public Payment checkoutPayment(String sum){
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
        redirectUrls.setCancelUrl("http://localhost:8080/paypal/confirm");//cancel Url
        redirectUrls.setReturnUrl("http://localhost:8080/paypal/confirm?amount=" + sum);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment=null;
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return createdPayment;
    }



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
        redirectUrls.setCancelUrl("http://localhost:8080/paypal/confirm");//cancel Url
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
