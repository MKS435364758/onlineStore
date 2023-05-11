package com.onlineShopping.Web.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
public class PaymentTransaction {

    private BigDecimal amount;

    private String CardHolder;

    private String cardNumber;

    private Date date;

    private Long secureCode;

    private String transactionId;

    private String status;

    public PaymentTransaction(CardDetails cardDetails,BigDecimal amount){
        this.amount=amount;
        this.CardHolder = cardDetails.getCardHolder();
        this.cardNumber=cardDetails.getCardNumber();
        this.date= cardDetails.getDate();
        this.secureCode = cardDetails.getSecureCode();
    }

    public PaymentTransaction(BigDecimal amount, String cardHolder, String cardNumber, java.sql.Date date, long secureCode) {
        this.amount=amount;
        this.CardHolder = cardHolder;
        this.cardNumber=cardNumber;
        this.date= date;
        this.secureCode = secureCode;
    }
    private PaymentTransaction(String transactionId, String status){
        this.transactionId = transactionId;
        this.status = status;
    }
}
