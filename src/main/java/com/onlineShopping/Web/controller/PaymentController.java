package com.onlineShopping.Web.controller;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Payments;
import com.onlineShopping.Web.request.CardDetailsRequest;
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
    public ResponseEntity<Orders> PaymentGateWay(@RequestBody CardDetailsRequest cardDetailsRequest, @RequestParam("orderId") String orderId) {
        return new ResponseEntity<>(paymentsService.savaPayments(cardDetailsRequest, orderId),
                HttpStatus.OK
        );
    }


    @PostMapping(value = "/start/test/", params = {"orderId", "amount"})
    public ResponseEntity<Payments> getTransaction(@RequestBody CardDetailsRequest cardDetailsRequest, @RequestParam("orderId") String orderId,
                                                   @RequestParam("amount") BigDecimal amount) {

        return new ResponseEntity<>(paymentsService.getPayment(cardDetailsRequest, orderId, amount),
                HttpStatus.OK
        );

    }


}
