package com.onlineShopping.Web.pojo;

import com.onlineShopping.Web.request.CardDetailsRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
public class PaymentTransactionRequestAndResponse {

    private BigDecimal amount;

    private String CardHolder;

    private String cardNumber;

    private Date date;

    private Long secureCode;

    private String transactionId;

    private String status;

    public PaymentTransactionRequestAndResponse(CardDetailsRequest cardDetailsRequest, BigDecimal amount) {
        this.amount = amount;
        this.CardHolder = cardDetailsRequest.getCardHolder();
        this.cardNumber = cardDetailsRequest.getCardNumber();
        this.date = cardDetailsRequest.getDate();
        this.secureCode = cardDetailsRequest.getSecureCode();
    }

    public PaymentTransactionRequestAndResponse(BigDecimal amount, String cardHolder, String cardNumber, java.sql.Date date, long secureCode) {
        this.amount = amount;
        this.CardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.date = date;
        this.secureCode = secureCode;
    }

    private PaymentTransactionRequestAndResponse(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }
}
