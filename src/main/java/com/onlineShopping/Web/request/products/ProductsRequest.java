package com.onlineShopping.Web.request.products;

import com.onlineShopping.Web.request.ClientRequest;
import com.onlineShopping.Web.request.address.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequest implements ClientRequest {

    private ProductsPojo productsPojo;

    private AddressRequest addressRequest;

    private String userId;


}
