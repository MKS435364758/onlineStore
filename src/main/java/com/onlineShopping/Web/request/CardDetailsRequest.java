package com.onlineShopping.Web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsRequest {

    private String CardHolder;

    private String cardNumber;

    private Date date;

    private Long secureCode;

}
