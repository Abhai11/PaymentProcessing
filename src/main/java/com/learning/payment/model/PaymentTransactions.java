package com.learning.payment.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class PaymentTransactions {

    private String id;
    private String sendingAccount;
    private String recievingAccount;
    private BigDecimal amount;
    private Date time;
    private Status status;

    public PaymentTransactions() {
        id = UUID.randomUUID().toString();
        time = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public String getSendingAccount() {
        return sendingAccount;
    }

    public String getRecievingAccount() {
        return recievingAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTime() {
        return time;
    }

    public Status getStatus() {
        return status;
    }

    public void setSendingAccount(String sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public void setRecievingAccount(String recievingAccount) {
        this.recievingAccount = recievingAccount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentTransactions{" +
                "id='" + id + '\'' +
                ", sendingAccount='" + sendingAccount + '\'' +
                ", recievingAccount='" + recievingAccount + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
