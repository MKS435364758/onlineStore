package com.onlineShopping.Web.request.products;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.ClientRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequest implements ClientRequest {

    //todo remove this products
    private Products product;

    private String images;

    private String description;

    private String listedPrice;

    private String discountPercentage;

    private String availability;



    //todo remove this address
    private Address address;



    private String userId;


}
