package com.learning.payment.service;

import com.learning.payment.dao.PaymentTransactionLedger;
import com.learning.payment.model.PaymentTransactions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionMonitoringService {

    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/paymentInfo", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentTransactions>> retrievePaymentInfo(@RequestParam(name = "type") String transactionType) {
        if (transactionType.equals("Success")) {
            return new ResponseEntity(PaymentTransactionLedger.getListOfSuccessfulTransactions(), HttpStatus.OK);
        } else {
            return new ResponseEntity(PaymentTransactionLedger.getListOfUnsuccessfulTransactions(), HttpStatus.OK);
        }
    }
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/totalTransactionAmount", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getTotalAmountTransferred(){
        return new ResponseEntity(PaymentTransactionLedger.getTransactionAmount(),HttpStatus.OK);
    }
}
