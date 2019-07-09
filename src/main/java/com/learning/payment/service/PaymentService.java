package com.learning.payment.service;

import com.learning.payment.message.queue.MessageQueue;
import com.learning.payment.model.PaymentInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RestController
public class PaymentService {

    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity transferMoney(@RequestBody PaymentInformation paymentInformation) {
        System.out.println("Received request " + paymentInformation.toString());
        MessageQueue.getInstance().addToQueue(paymentInformation);
        return new ResponseEntity(HttpStatus.OK);
    }
}
