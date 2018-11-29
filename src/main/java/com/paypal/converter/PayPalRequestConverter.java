//package com.paypal.converter;
//
//import com.paypal.model.*;
//import com.paypal.api.payments.Payment;
//import com.paypal.model.Error;
//import com.paypal.util.PayPalUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class PayPalRequestConverter {
//
//    @Autowired
//    private PayPalUtil payPalUtil;
//
//
//    public Payment convert(CreatePaymentRequest paymentRequest) {
//        Payment paymentResponse=new Payment();
//        //ExecutePaymentResponse paymentResponse=new ExecutePaymentResponse();
//        paymentResponse.setId(paymentRequest.getId());
//        paymentResponse.setIntent(paymentRequest.getIntent());
//        paymentResponse.setPayer(getPayer(paymentRequest));
//        paymentResponse.setPotentialPayerInfo(getPotentialPayerInfo(paymentRequest));
//        paymentResponse.setPayee(getPayee(paymentRequest));
//        paymentResponse.setCart(paymentRequest.getCart());
//        paymentResponse.setTransactions(getTransactions(paymentRequest));
//        paymentResponse.setFailedTransactions(getErrors(paymentRequest));
//        paymentResponse.setBillingAgreementTokens(paymentRequest.getBillingAgreementTokens());
//        paymentResponse.setCreditFinancingOffered(getCreditFinancingOffered(paymentRequest));
//        paymentResponse.setState(paymentRequest.getState());
//        paymentResponse.setExperienceProfileId(paymentRequest.getExperienceProfileId());
//        paymentResponse.setNoteToPayer(paymentRequest.getNoteToPayer());
//        paymentResponse.setRedirectUrls(getRedirectUrls(paymentRequest));
//        paymentResponse.setFailureReason(paymentRequest.getFailureReason());
//        paymentResponse.setCreateTime(paymentRequest.getCreateTime());
//        paymentResponse.setUpdateTime(paymentRequest.getUpdateTime());
//        paymentResponse.setLinks(getLinks(paymentRequest));
//        return paymentResponse;
//    }
//
//    public List<com.paypal.api.payments.Links> getLinks(CreatePaymentRequest payment) {
//        List<com.paypal.api.payments.Links> links = new ArrayList<>();
//        List<Links> payPalLinks = payment.getLinks();
//        if (!StringUtils.isEmpty(payPalLinks)) {
//            for (Links paypalLink : payPalLinks) {
//                com.paypal.api.payments.Links link = new com.paypal.api.payments.Links();
//                link.setEnctype(paypalLink.getEnctype());
//                link.setHref(paypalLink.getHref());
//                link.setMethod(paypalLink.getMethod());
//                link.setRel(paypalLink.getRel());
//                link.setSchema(paypalLink.getSchema());
//                link.setTargetSchema(paypalLink.getTargetSchema());
//                links.add(link);
//            }
//        }
//        return links;
//    }
//
//    public com.paypal.api.payments.RedirectUrls getRedirectUrls(CreatePaymentRequest payment) {
//        com.paypal.api.payments.RedirectUrls redirectUrls=new com.paypal.api.payments.RedirectUrls();
//        RedirectUrls urls=payment.getRedirectUrls();
//        if(urls!=null){
//            redirectUrls.setReturnUrl(urls.getReturnUrl());
//            redirectUrls.setCancelUrl(urls.getCancelUrl());
//        }
//        return redirectUrls;
//    }
//
//    public com.paypal.api.payments.CreditFinancingOffered getCreditFinancingOffered(CreatePaymentRequest payment) {
//        com.paypal.api.payments.CreditFinancingOffered creditFinancingOffered=new com.paypal.api.payments.CreditFinancingOffered();
//        CreditFinancingOffered financingOffered=payment.getCreditFinancingOffered();
//        if(financingOffered!=null) {
//            creditFinancingOffered.setCartAmountImmutable(financingOffered.getCartAmountImmutable());
//            creditFinancingOffered.setMonthlyPayment(financingOffered.getMonthlyPayment());
//            creditFinancingOffered.setPayerAcceptance(financingOffered.getPayerAcceptance());
//            creditFinancingOffered.setTerm(financingOffered.getTerm());
//            creditFinancingOffered.setTotalCost(financingOffered.getTotalCost());
//            creditFinancingOffered.setTotalInterest(financingOffered.getTotalInterest());
//        }
//        return creditFinancingOffered;
//    }
//
//    public List<com.paypal.api.payments.Error> getErrors(CreatePaymentRequest payment) {
//        List<com.paypal.api.payments.Error> errors=new ArrayList<>();
//        List<Error> errorList=payment.getFailedTransactions();
//        if (!CollectionUtils.isEmpty(errorList)) {
//            for(Error err:errorList){
//                com.paypal.api.payments.Error error = new com.paypal.api.payments.Error();
//                error.setDebugId(err.getDebugId());
//                error.setDetails(err.getDetails());
//                error.setInformationLink(err.getInformationLink());
//                error.setLinks(err.getLinks());
//                error.setMessage(err.getMessage());
//                error.setName(err.getName());
//                errors.add(error);
//            }
//        }
//        return errors;
//    }
//
//    public List<com.paypal.api.payments.Transaction> getTransactions(CreatePaymentRequest payment) {
//        List<com.paypal.api.payments.Transaction> transactions = new ArrayList<>();
//        List<Transaction> paypalTransactions = payment.getTransactions();
//        for (Transaction trans : paypalTransactions) {
//            com.paypal.api.payments.Transaction transaction = new com.paypal.api.payments.Transaction();
//            transaction.setTransactions(trans.getTransactions());
//            transactions.add(transaction);
//        }
//        return transactions;
//    }
//
//    public com.paypal.api.payments.Payee getPayee(CreatePaymentRequest payment) {
//        com.paypal.api.payments.Payee payee=new com.paypal.api.payments.Payee();
//        Payee payeee=payment.getPayee();
//        if(payeee!=null){
//            payee.setAccountNumber(payeee.getAccountNumber());
//            payee.setFirstName(payeee.getFirstName());
//            payee.setLastName(payeee.getLastName());
//            payee.setMerchantId(payeee.getMerchantId());
//            payee.setEmail(payeee.getEmail());
//            payee.setPhone(payeee.getPhone());
//
//        }
//        return payee;
//    }
//
//    public com.paypal.api.payments.PotentialPayerInfo getPotentialPayerInfo(CreatePaymentRequest payment) {
//        PotentialPayerInfo potentialPayerInfo=payment.getPotentialPayerInfo();
//         com.paypal.api.payments.PotentialPayerInfo payerInfo=new com.paypal.api.payments.PotentialPayerInfo();
//        if(potentialPayerInfo!=null){
//            payerInfo.setAccountNumber(potentialPayerInfo.getAccountNumber());
//            payerInfo.setBillingAddress(potentialPayerInfo.getBillingAddress());
//            payerInfo.setEmail(potentialPayerInfo.getEmail());
//            payerInfo.setExternalRememberMeId(potentialPayerInfo.getExternalRememberMeId());
//        }
//        return payerInfo;
//    }
//
//    public com.paypal.api.payments.Payer getPayer(CreatePaymentRequest payment) {
//        com.paypal.api.payments.Payer payer = new com.paypal.api.payments.Payer();
//        Payer payr = payment.getPayer();
//        if (payr != null) {
//            payer.setAccountAge(payr.getAccountAge());
//            payer.setAccountType(payr.getAccountType());
//            payer.setExternalSelectedFundingInstrumentType(payr.getExternalSelectedFundingInstrumentType());
//            payer.setFundingInstruments(payr.getFundingInstruments());
//            payer.setFundingOptionId(payr.getFundingOptionId());
//            payer.setPayerInfo(payr.getPayerInfo());
//            payer.setPaymentMethod(payr.getPaymentMethod());
//            payer.setRelatedFundingOption(payr.getRelatedFundingOption());
//            payer.setStatus(payr.getStatus());
//            payer.setExternalSelectedFundingInstrumentType(payr.getExternalSelectedFundingInstrumentType());
//        }
//        return payer;
//    }
//
//
//
//
//}
