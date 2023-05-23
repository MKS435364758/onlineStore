package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Payments;
import com.onlineShopping.Web.enums.Status;
import com.onlineShopping.Web.tools.UnPackOptional;
import com.onlineShopping.Web.request.CardDetailsRequest;
import com.onlineShopping.Web.kafka.message.request.OrderInfoMessageRequest;
import com.onlineShopping.Web.pojo.PaymentTransactionRequestAndResponse;
import com.onlineShopping.Web.repository.OrdersRepository;
import com.onlineShopping.Web.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PaymentsService {

    public static final String URL = "http://localhost:9090/transaction/start";

    KafkaService kafkaService;

    PaymentsRepository paymentRepository;

    OrdersRepository ordersRepository;

    public Payments getPayment(CardDetailsRequest cardDetailsRequest, String orderId,
                               BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        Payments payment = UnPackOptional.getObject(ordersRepository.findById(orderId)).getPayment();

        payment.setCardHolder(cardDetailsRequest.getCardHolder());
        payment.setCardNumber(Long.valueOf(cardDetailsRequest.getCardNumber()));
        payment.setDate(cardDetailsRequest.getDate().toString());
        payment.setSecureCode(cardDetailsRequest.getSecureCode());
        return payment;
    }

    public Orders savaPayments(CardDetailsRequest cardDetailsRequest, String orderId) {
        RestTemplate restTemplate = new RestTemplate();

        //TODO: constructing required pojo for traction and creating http client.
        Orders order = UnPackOptional.getObject(ordersRepository.findById(orderId));
        Payments payment = order.getPayment();

        payment.setCardHolder(cardDetailsRequest.getCardHolder());
        payment.setCardNumber(Long.valueOf(cardDetailsRequest.getCardNumber()));
        payment.setDate(cardDetailsRequest.getDate().toString());
        payment.setSecureCode(cardDetailsRequest.getSecureCode());

        PaymentTransactionRequestAndResponse transaction = new PaymentTransactionRequestAndResponse(cardDetailsRequest, order.getFinalAmount());
        PaymentTransactionRequestAndResponse responseTransaction = restTemplate.postForObject(URL, transaction, PaymentTransactionRequestAndResponse.class);

        assert responseTransaction != null;
        payment.setStatus(responseTransaction.getStatus());
        payment.setTransaction_id(responseTransaction.getTransactionId());

        paymentRepository.save(payment);
        if (payment.getStatus() != null) {
            order.setStatus(String.valueOf(Status.PAYMENT_APPROVED));
            kafkaService.sendMessage("order", new OrderInfoMessageRequest(order));
        } else order.setStatus(String.valueOf(Status.PAYMENT_FAILED));
        return UnPackOptional.getObject(ordersRepository.findById(orderId));
    }

    Payments getPayments(String id) {
        return paymentRepository.findById(id).get();
    }

    void deletePayments(String id) {
        paymentRepository.deleteById(id);
    }
}
