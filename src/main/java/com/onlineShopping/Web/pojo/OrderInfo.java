package com.onlineShopping.Web.pojo;

import com.onlineShopping.Web.entities.Orders;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter

public class OrderInfo {

    private String id;

    private String username;

    private String email;

    private String number;

    private BigDecimal finalAmount;

    private String status;

    private Timestamp madeOn;

    private Timestamp delivered_on;

    public OrderInfo(Orders orders) {
        this.id = orders.getId();
        this.username = orders.getUser().getUsername();
        this.email = orders.getUser().getEmail();
        this.number = orders.getUser().getNumber();
        this.finalAmount = orders.getFinalAmount();
        this.status = orders.getStatus();
        this.madeOn = orders.getMadeOn();
        this.delivered_on = orders.getDelivered_on();
    }

}
