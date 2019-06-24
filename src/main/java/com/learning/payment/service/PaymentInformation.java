package com.learning.payment.service;

import java.math.BigDecimal;

public class PaymentInformation {

    private int fromAccountNumber;
    private int toAccountNumber;
    private int fromAccountSortCode;
    private int toAccountSortCode;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "PaymentInformation{" +
                "fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", fromAccountSortCode=" + fromAccountSortCode +
                ", toAccountSortCode=" + toAccountSortCode +
                ", amount=" + amount +
                '}';
    }

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(int fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(int toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getFromAccountSortCode() {
        return fromAccountSortCode;
    }

    public void setFromAccountSortCode(int fromAccountSortCode) {
        this.fromAccountSortCode = fromAccountSortCode;
    }

    public int getToAccountSortCode() {
        return toAccountSortCode;
    }

    public void setToAccountSortCode(int toAccountSortCode) {
        this.toAccountSortCode = toAccountSortCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
