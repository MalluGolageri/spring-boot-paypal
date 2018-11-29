package com.paypal.util;

import com.paypal.api.payments.Payment;
import com.paypal.model.*;
import com.paypal.model.Error;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayPalUtil {


    public List<Links> getLinks(Payment payment) {
        List<Links> links = new ArrayList<>();
        List<com.paypal.api.payments.Links> payPalLinks = payment.getLinks();
        if (!StringUtils.isEmpty(payPalLinks)) {
            for (com.paypal.api.payments.Links paypalLink : payPalLinks) {
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

    public RedirectUrls getRedirectUrls(Payment payment) {
        RedirectUrls redirectUrls=new RedirectUrls();
        com.paypal.api.payments.RedirectUrls urls=payment.getRedirectUrls();
        if(urls!=null){
            redirectUrls.setReturnUrl(urls.getReturnUrl());
            redirectUrls.setCancelUrl(urls.getCancelUrl());
        }
        return redirectUrls;
    }

    public CreditFinancingOffered getCreditFinancingOffered(Payment payment) {
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

    public List<com.paypal.model.Error> getErrors(Payment payment) {
        List<com.paypal.model.Error> errors=new ArrayList<>();
        List<com.paypal.api.payments.Error> errorList=payment.getFailedTransactions();
        if (!CollectionUtils.isEmpty(errorList)) {
            for(com.paypal.api.payments.Error err:errorList){
                com.paypal.model.Error error = new Error();
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

    public List<Transaction> getTransactions(Payment payment) {
        List<Transaction> transactions = new ArrayList<>();
        List<com.paypal.api.payments.Transaction> paypalTransactions = payment.getTransactions();
        for (com.paypal.api.payments.Transaction trans : paypalTransactions) {
            Transaction transaction = new Transaction();
            transaction.setTransactions(trans.getTransactions());
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
            //transaction.setRelatedResources(trans.getRelatedResources());
            transaction.setSoftDescriptor(trans.getSoftDescriptor());
            transaction.setSoftDescriptorCity(trans.getSoftDescriptorCity());
            transactions.add(transaction);

        }
        return transactions;
    }

    public Payee getPayee(Payment payment) {
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

    public PotentialPayerInfo getPotentialPayerInfo(Payment payment) {
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

    public Payer getPayer(Payment payment) {
        Payer payer = new Payer();
        com.paypal.api.payments.Payer payr = payment.getPayer();
        if (payr != null) {
            payer.setAccountAge(payr.getAccountAge());
            payer.setAccountType(payr.getAccountType());
            payer.setExternalSelectedFundingInstrumentType(payr.getExternalSelectedFundingInstrumentType());
            payer.setFundingInstruments(payr.getFundingInstruments());
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
