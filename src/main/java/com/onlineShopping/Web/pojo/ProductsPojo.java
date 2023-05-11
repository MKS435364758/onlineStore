package com.onlineShopping.Web.pojo;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsPojo {

    private Products product;

    private Address address;

    private String userId;



}
