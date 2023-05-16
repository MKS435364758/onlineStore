package com.onlineShopping.Web.request;

import com.onlineShopping.Web.entities.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class OrderRequest extends Orders {

    private String userId;

    private List<String> productsIds;

}
