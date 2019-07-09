package com.learning.payment.dao;

import com.learning.payment.model.BankAccount;
import com.learning.payment.model.PaymentInformation;
import com.learning.payment.model.PaymentTransactions;
import com.learning.payment.model.Status;

public interface PaymentDAO {

    BankAccount fetchBankInformation(int accountNumber, int sortCode);
    boolean transferBetweenAccounts(PaymentInformation paymentInformation);
    PaymentTransactions updateTransactionLedger(PaymentInformation paymentInformation, Status status);
}
