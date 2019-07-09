package com.learning.payment.dao;

import com.learning.payment.model.BankAccount;
import com.learning.payment.model.PaymentInformation;
import com.learning.payment.model.PaymentTransactions;
import com.learning.payment.model.Status;

import java.math.BigDecimal;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    private final static String DELIMITER = ":";

    @Override
    public BankAccount fetchBankInformation(int accountNumber, int sortCode) {
        String key = new StringBuilder().append(accountNumber)
                .append(DELIMITER)
                .append(sortCode).toString();
        return BankAcountInformation.getAccountMap().get(key);
    }

    /**
     * Not handled for bank account not present.
     * TODO handle better check for locking
     *
     * @param paymentInformation
     * @return
     */
    @Override
    public boolean transferBetweenAccounts(PaymentInformation paymentInformation) {
        System.out.println("Transaction request received "+paymentInformation.toString());
        BankAccount fromAccount = fetchBankInformation(paymentInformation.getFromAccountNumber(), paymentInformation.getFromAccountSortCode());
        BankAccount toAccount = fetchBankInformation(paymentInformation.getToAccountNumber(), paymentInformation.getToAccountSortCode());
        if(fromAccount == null|| toAccount == null){
            System.out.println("Account not found");
            return false;
        }
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(paymentInformation.getAmount()) >= 0) {
                    BigDecimal balanceOfFromAccountBeforeTrans = fromAccount.getBalance();
                    BigDecimal balanceOfToAccountBeforeTrans = toAccount.getBalance();
                    System.out.println("Balance of from account before transaction "+balanceOfFromAccountBeforeTrans.toString());
                    System.out.println("Balance of to account before transaction "+balanceOfToAccountBeforeTrans.toString());
                    fromAccount.setBalance(balanceOfFromAccountBeforeTrans.subtract(paymentInformation.getAmount()));
                    toAccount.setBalance(balanceOfToAccountBeforeTrans.add(paymentInformation.getAmount()));
                    System.out.println("Balance of from account after transaction "+fromAccount.getBalance().toString());
                    System.out.println("Balance of to account after transaction "+toAccount.getBalance().toString());
                    updateTransactionLedger(paymentInformation, Status.SUCCESS);
                    System.out.println("Transaction successful "+paymentInformation.toString());
                    return true;
                } else {
                    updateTransactionLedger(paymentInformation, Status.FAIL);
                    System.out.println("Transaction failed "+paymentInformation.toString());
                    return false;
                }
            }
        }
    }

    @Override
    public PaymentTransactions updateTransactionLedger(PaymentInformation paymentInformation, Status status) {
        PaymentTransactions transactions = new PaymentTransactions();
        transactions.setSendingAccount(new StringBuilder().append(paymentInformation.getFromAccountNumber())
                .append(DELIMITER)
                .append(paymentInformation.getFromAccountSortCode()).toString());
        transactions.setRecievingAccount(new StringBuilder().append(paymentInformation.getToAccountNumber())
                .append(DELIMITER)
                .append(paymentInformation.getToAccountSortCode()).toString());
        transactions.setAmount(paymentInformation.getAmount());
        transactions.setStatus(status);

        PaymentTransactionLedger.addToTransactionLedger(transactions,status);
        return transactions;
    }
}
