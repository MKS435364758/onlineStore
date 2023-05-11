package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Payments;
import com.onlineShopping.Web.enums.Status;
import com.onlineShopping.Web.object.Unwarranted;
import com.onlineShopping.Web.pojo.CardDetails;
import com.onlineShopping.Web.pojo.OrderInfo;
import com.onlineShopping.Web.pojo.PaymentTransaction;
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

    public Payments getPayment(CardDetails cardDetails, String orderId,
                               BigDecimal amount){
        RestTemplate restTemplate = new RestTemplate();
        Payments payment = Unwarranted.getObject(ordersRepository.findById(orderId)).getPayment();

        payment.setCardHolder(cardDetails.getCardHolder());
        payment.setCardNumber(Long.valueOf(cardDetails.getCardNumber()));
        payment.setDate(cardDetails.getDate().toString());
        payment.setSecureCode(cardDetails.getSecureCode());
        return payment;
    }

    public Orders savaPayments(CardDetails cardDetails, String orderId){
        RestTemplate restTemplate = new RestTemplate();

        //TODO: constructing required pojo for traction and creating http client.
        Orders order = Unwarranted.getObject(ordersRepository.findById(orderId));
        Payments payment = order.getPayment();

        payment.setCardHolder(cardDetails.getCardHolder());
        payment.setCardNumber(Long.valueOf(cardDetails.getCardNumber()));
        payment.setDate(cardDetails.getDate().toString());
        payment.setSecureCode(cardDetails.getSecureCode());

        PaymentTransaction transaction = new PaymentTransaction(cardDetails,order.getFinalAmount());
        PaymentTransaction responseTransaction =  restTemplate.postForObject(URL,transaction,PaymentTransaction.class);

        assert responseTransaction != null;
        payment.setStatus(responseTransaction.getStatus());
        payment.setTransaction_id(responseTransaction.getTransactionId());

        paymentRepository.save(payment);
        if(payment.getStatus()!=null){
            order.setStatus(String.valueOf(Status.PAYMENT_APPROVED));
            kafkaService.sendMessage("order",new OrderInfo(order));
        }
        else order.setStatus(String.valueOf(Status.PAYMENT_FAILED));
        return Unwarranted.getObject(ordersRepository.findById(orderId));
    }

    Payments getPayments(String id){
        return paymentRepository.findById(id).get();
    }

    void deletePayments(String id){
        paymentRepository.deleteById(id);
    }
}
