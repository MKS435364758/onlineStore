package com.onlineShopping.Web.request.address;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private String name;

    private String number;

    private String lineOne;

    private String lineTwo;

    private String city;

    private String zipcode;

    private String state;

    private String country;

}
