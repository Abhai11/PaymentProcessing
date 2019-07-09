package com.learning.payment.dao;

import com.learning.payment.model.PaymentTransactions;
import com.learning.payment.model.Status;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Resource
public class PaymentTransactionLedger {

    static {
        listOfSuccessfulTransactions = new ArrayList<>();
        listOfUnsuccessfulTransactions = new ArrayList<>();
    }

    private static List<PaymentTransactions> listOfSuccessfulTransactions;
    private static List<PaymentTransactions> listOfUnsuccessfulTransactions;
    private static BigDecimal transactionAmount = BigDecimal.ZERO;

    private PaymentTransactionLedger(){}

    public static void addToTransactionLedger(PaymentTransactions transaction, Status status){
        if(status == Status.SUCCESS){
            listOfSuccessfulTransactions.add(transaction);
            transactionAmount = transactionAmount.add(transaction.getAmount());
        }else {
            listOfUnsuccessfulTransactions.add(transaction);
        }
    }

    public static List<PaymentTransactions> getListOfSuccessfulTransactions() {
        return listOfSuccessfulTransactions;
    }

    public static List<PaymentTransactions> getListOfUnsuccessfulTransactions() {
        return listOfUnsuccessfulTransactions;
    }

    public static BigDecimal getTransactionAmount() {
        return transactionAmount;
    }
}
