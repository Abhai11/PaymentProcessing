package com.learning.payment.message.queue;

import com.learning.payment.model.PaymentInformation;
import org.junit.Test;

import java.math.BigDecimal;

public class TestMessageQueue {

    @Test
    public void testQueue(){
        MessageQueue queue = MessageQueue.getInstance();
        PaymentInformation information1 = new PaymentInformation();
        information1.setFromAccountNumber(1234);
        information1.setFromAccountSortCode(567);
        information1.setToAccountNumber(1112);
        information1.setToAccountSortCode(134);
        information1.setAmount(new BigDecimal(34.5));

        PaymentInformation information2 = new PaymentInformation();
        information2.setFromAccountNumber(1112);
        information2.setFromAccountSortCode(456);
        information2.setToAccountNumber(1234);
        information2.setToAccountSortCode(231);
        information2.setAmount(new BigDecimal(4.5));

        PaymentInformation information3 = new PaymentInformation();
        information3.setFromAccountNumber(1234);
        information3.setFromAccountSortCode(456);
        information3.setToAccountNumber(1234);
        information3.setToAccountSortCode(231);
        information3.setAmount(new BigDecimal(1.5));


        queue.addToQueue(information1);
        queue.addToQueue(information2);
        queue.addToQueue(information3);
    }

}
