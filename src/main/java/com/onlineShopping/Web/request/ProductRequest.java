package com.onlineShopping.Web.request;

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
public class ProductRequest {

    private Products product;

    private Address address;

    private String userId;


}
