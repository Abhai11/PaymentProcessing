package com.learning.payment.message.queue;

import com.learning.payment.dao.PaymentDAO;
import com.learning.payment.dao.PaymentDAOImpl;
import com.learning.payment.model.PaymentInformation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private static BlockingQueue<PaymentInformation> paymentQueue = new LinkedBlockingQueue<>();
    private static int NUMBER_OF_MESSAGE_HANDLERS = 3;

    static {
        instance = new MessageQueue();
    }

    private static MessageQueue instance;
    private PaymentDAO paymentDAO = new PaymentDAOImpl();


    private MessageQueue() {
        paymentQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < NUMBER_OF_MESSAGE_HANDLERS; i++) {
            System.out.println("Starting consumer " + i);
            new Thread(new MessageConsumer(paymentQueue)).start();
        }
    }

    public static MessageQueue getInstance() {
        return instance;
    }

    public boolean addToQueue(PaymentInformation paymentInformation) {
        return paymentQueue.add(paymentInformation);
    }

    //TODO add mechanism to abort consumer
    private class MessageConsumer implements Runnable {
        private BlockingQueue<PaymentInformation> queue;

        public MessageConsumer(BlockingQueue<PaymentInformation> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Polling");
                    paymentDAO.transferBetweenAccounts(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
