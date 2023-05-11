package com.onlineShopping.Web.pojo;

import com.onlineShopping.Web.entities.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class OrderPojo extends Orders {

    private String userId;

    private List<String> productsIds;

}
