package com.learning.payment.dao;

import com.learning.payment.model.BankAccount;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BankAcountInformation {

    static {
        accountMap = new HashMap<>();
        populateDate();
    }

    private static Map<String, BankAccount> accountMap;

    public static Map<String, BankAccount> getAccountMap() {
        return Collections.unmodifiableMap(accountMap);
    }

    private static void populateDate() {
        BankAccount account1 = new BankAccount();
        account1.setAccountNumber(1234);
        account1.setSortCode(567);
        account1.setBalance(BigDecimal.valueOf(150.50));
        accountMap.put("1234:567", account1);

        BankAccount account2 = new BankAccount();
        account2.setAccountNumber(1112);
        account2.setSortCode(134);
        account2.setBalance(BigDecimal.valueOf(50.75));
        accountMap.put("1112:134", account2);

        BankAccount account3 = new BankAccount();
        account3.setAccountNumber(2122);
        account3.setSortCode(234);
        account3.setBalance(BigDecimal.valueOf(10));
        accountMap.put("2122:234", account3);
    }
}
