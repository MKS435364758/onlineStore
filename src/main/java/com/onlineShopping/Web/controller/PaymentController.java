package com.onlineShopping.Web.controller;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Payments;
import com.onlineShopping.Web.pojo.CardDetails;
import com.onlineShopping.Web.service.PaymentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("payment")
@AllArgsConstructor
public class PaymentController {

    PaymentsService paymentsService;

    @PostMapping(value = "/start/", params = {"orderId"})
    public ResponseEntity<Orders> PaymentGateWay(@RequestBody CardDetails cardDetails, @RequestParam("orderId") String orderId) {
        return new ResponseEntity<>(paymentsService.savaPayments(cardDetails, orderId),
                HttpStatus.OK
        );
    }


    @PostMapping(value = "/start/test/", params = {"orderId", "amount"})
    public ResponseEntity<Payments> getTransaction(@RequestBody CardDetails cardDetails, @RequestParam("orderId") String orderId,
                                                   @RequestParam("amount") BigDecimal amount) {

        return new ResponseEntity<>(paymentsService.getPayment(cardDetails, orderId, amount),
                HttpStatus.OK
        );

    }


}
