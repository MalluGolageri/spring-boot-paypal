package com.paypal.response;


import com.paypal.api.payments.Payment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayPalResponseConverter {

    public PaymentResponse convert(Payment payment) {
        PaymentResponse paymentResponse=new PaymentResponse();

        paymentResponse.setId(payment.getId());
        paymentResponse.setIntent(payment.getIntent());
        paymentResponse.setPayer(getPayer(payment));
        paymentResponse.setPotentialPayerInfo(getPotentialPayerInfo(payment));
        paymentResponse.setPayee(getPayee(payment));
        paymentResponse.setCart(payment.getCart());
        paymentResponse.setTransactions(getTransactions(payment));
        paymentResponse.setFailedTransactions(getErrors(payment));
//        BillingAgreementToken billingAgreementToken=new BillingAgreementToken();
//        paymentResponse.setBillingAgreementTokens(payment.getBillingAgreementTokens());
        paymentResponse.setCreditFinancingOffered(getCreditFinancingOffered(payment));
        paymentResponse.setState(payment.getState());
        paymentResponse.setExperienceProfileId(payment.getExperienceProfileId());
        paymentResponse.setNoteToPayer(payment.getNoteToPayer());
        paymentResponse.setRedirectUrls(getRedirectUrls(payment));
        paymentResponse.setFailureReason(payment.getFailureReason());
        paymentResponse.setCreateTime(payment.getCreateTime());
        paymentResponse.setUpdateTime(payment.getUpdateTime());
        paymentResponse.setLinks(getLinks(payment));


        return paymentResponse;
    }

    private List<Links> getLinks(Payment payment) {
        List<Links> links=new ArrayList<>();
        List<com.paypal.api.payments.Links> paypalLinks=payment.getLinks();
        if(!StringUtils.isEmpty(paypalLinks)) {
            for (com.paypal.api.payments.Links paypalLink : paypalLinks) {
                Links link = new Links();
                link.setEnctype(paypalLink.getEnctype());
                link.setHref(paypalLink.getHref());
                link.setMethod(paypalLink.getMethod());
                link.setRel(paypalLink.getRel());
                link.setSchema(paypalLink.getSchema());
                link.setTargetSchema(paypalLink.getTargetSchema());
                links.add(link);
            }
        }
        return links;
    }

    private RedirectUrls getRedirectUrls(Payment payment) {
        RedirectUrls redirectUrls=new RedirectUrls();
        com.paypal.api.payments.RedirectUrls urls=payment.getRedirectUrls();
        if(urls!=null){
            redirectUrls.setReturnUrl(urls.getReturnUrl());
            redirectUrls.setCancelUrl(urls.getCancelUrl());
        }
        return redirectUrls;
    }

    private CreditFinancingOffered getCreditFinancingOffered(Payment payment) {
        CreditFinancingOffered creditFinancingOffered=new CreditFinancingOffered();
        com.paypal.api.payments.CreditFinancingOffered financingOffered=payment.getCreditFinancingOffered();
        if(financingOffered!=null) {
            creditFinancingOffered.setCartAmountImmutable(financingOffered.getCartAmountImmutable());
            creditFinancingOffered.setMonthlyPayment(financingOffered.getMonthlyPayment());
            creditFinancingOffered.setPayerAcceptance(financingOffered.getPayerAcceptance());
            creditFinancingOffered.setTerm(financingOffered.getTerm());
            creditFinancingOffered.setTotalCost(financingOffered.getTotalCost());
            creditFinancingOffered.setTotalInterest(financingOffered.getTotalInterest());
        }
        return creditFinancingOffered;
    }

    private List<Error> getErrors(Payment payment) {
        List<Error> errors=new ArrayList<>();
        List<com.paypal.api.payments.Error> errorList=payment.getFailedTransactions();
        if (!CollectionUtils.isEmpty(errorList)) {
            for(com.paypal.api.payments.Error err:errorList){
                Error error = new Error();
                error.setDebugId(err.getDebugId());
                error.setDetails(err.getDetails());
                error.setInformationLink(err.getInformationLink());
                error.setLinks(err.getLinks());
                error.setMessage(err.getMessage());
                error.setName(err.getName());
                errors.add(error);
            }
        }
        return errors;
    }

    private List<Transaction> getTransactions(Payment payment) {
        List<Transaction> transactions = new ArrayList<>();
        List<com.paypal.api.payments.Transaction> paypalTransactions = payment.getTransactions();
        for (com.paypal.api.payments.Transaction trans : paypalTransactions) {
            Transaction transaction = new Transaction();
            transaction.setTransactions(trans.getTransactions());
            transactions.add(transaction);
        }
        return transactions;
    }

    private Payee getPayee(Payment payment) {
        Payee payee=new Payee();
        com.paypal.api.payments.Payee payeee=payment.getPayee();
        if(payeee!=null){
            payee.setAccountNumber(payeee.getAccountNumber());
            payee.setFirstName(payeee.getFirstName());
            payee.setLastName(payeee.getLastName());
            payee.setMerchantId(payeee.getMerchantId());
            payee.setEmail(payeee.getEmail());
            payee.setPhone(payeee.getPhone());

        }
        return payee;
    }

    private PotentialPayerInfo getPotentialPayerInfo(Payment payment) {
        com.paypal.api.payments.PotentialPayerInfo potentialPayerInfo=payment.getPotentialPayerInfo();
        PotentialPayerInfo payerInfo=new PotentialPayerInfo();
        if(potentialPayerInfo!=null){
            payerInfo.setAccountNumber(potentialPayerInfo.getAccountNumber());
            payerInfo.setBillingAddress(potentialPayerInfo.getBillingAddress());
            payerInfo.setEmail(potentialPayerInfo.getEmail());
            payerInfo.setExternalRememberMeId(potentialPayerInfo.getExternalRememberMeId());
        }
        return payerInfo;
    }

    private Payer getPayer(Payment payment) {
        Payer payer = new Payer();
        com.paypal.api.payments.Payer payr = payment.getPayer();
        if (payr != null) {
            payer.setAccountAge(payr.getAccountAge());
            payer.setAccountType(payr.getAccountType());
            payer.setExternalSelectedFundingInstrumentType(payr.getExternalSelectedFundingInstrumentType());
            payer.setFundingInstruments(payr.getFundingInstruments());
            payer.setFundingOption(payr.getFundingOption());
            payer.setFundingOptionId(payr.getFundingOptionId());
            payer.setPayerInfo(payr.getPayerInfo());
            payer.setPaymentMethod(payr.getPaymentMethod());
            payer.setRelatedFundingOption(payr.getRelatedFundingOption());
            payer.setStatus(payr.getStatus());
            payer.setExternalSelectedFundingInstrumentType(payr.getExternalSelectedFundingInstrumentType());
        }
        return payer;
    }

}
