package com.onlineShopping.Web.response.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductsResponse {

    @NonNull
    private String id;

    @NonNull
    private String images;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal listedPrice;

    @NonNull
    private Double discountPercentage;

    @NonNull
    private Long availability;

}
