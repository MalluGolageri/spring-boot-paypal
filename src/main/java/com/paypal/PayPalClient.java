package com.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.model.CreatePaymentRequest;
import com.paypal.model.UpdatePaymentRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
            Transaction transaction=new Transaction();
            transaction.setTransactions(trans.getTransactions());
            transaction.setAmount(trans.getAmount());
            transaction.setCustom(trans.getCustom());
            transaction.setDescription(trans.getCustom());
            transaction.setExternalFunding(trans.getExternalFunding());
            transaction.setInvoiceNumber(trans.getInvoiceNumber());
            transaction.setItemList(trans.getItemList());
            transaction.setNoteToPayee(trans.getNoteToPayee());
            transaction.setNotifyUrl(trans.getNotifyUrl());
            transaction.setOrderUrl(trans.getOrderUrl());
            transaction.setPayee(trans.getPayee());
            transaction.setPaymentOptions(trans.getPaymentOptions());
            transaction.setPurchaseUnitReferenceId(trans.getPurchaseUnitReferenceId());
            transaction.setReferenceId(trans.getReferenceId());
            transaction.setRelatedResources(trans.getRelatedResources());
            transaction.setSoftDescriptor(trans.getSoftDescriptor());
            transaction.setSoftDescriptorCity(trans.getSoftDescriptorCity());
            transactions.add(transaction);
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


    Payment updatePayment(String paymentId, List<Patch> patches) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId.replaceAll("^\"|\"$", ""));
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        payment.update(context, patches);
        return Payment.get(context, paymentId);
    }

    public Payment getPaymnent(String paymentId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId.replaceAll("^\"|\"$", ""));
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        return Payment.get(context, paymentId);
    }


//
//    public Payment checkoutPayment(String sum){
//        Amount amount = new Amount();
//        amount.setCurrency("USD");
//        amount.setTotal(sum);
//        Transaction transaction = new Transaction();
//        transaction.setAmount(amount);
//        List<Transaction> transactions = new ArrayList<Transaction>();
//        transactions.add(transaction);
//
//        Payer payer = new Payer();
//        payer.setPaymentMethod("paypal");
//
//        Payment payment = new Payment();
//        payment.setIntent("sale");
//        payment.setPayer(payer);
//
//
//        payment.setTransactions(transactions);
//
//        RedirectUrls redirectUrls = new RedirectUrls();
//        redirectUrls.setCancelUrl("http://localhost:8080/paypal/confirm");//cancel Url
//        redirectUrls.setReturnUrl("http://localhost:8080/paypal/confirm?amount=" + sum);
//        payment.setRedirectUrls(redirectUrls);
//        Payment createdPayment=null;
//        try {
//            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
//            createdPayment = payment.create(context);
//        } catch (PayPalRESTException e) {
//            System.out.println("Error happened during payment creation!");
//        }
//        return createdPayment;
//    }


}





