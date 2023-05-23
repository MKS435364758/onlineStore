package com.onlineShopping.Web.dto.response.mapper;

import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.response.product.ProductsResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductsResponseMapper implements ResponseDTOMapper<ProductsResponse, Products> {
    @Override
    public ProductsResponse toResponse(Products entity) {
        return modelMapper.map(entity, ProductsResponse.class);
    }

    @Override
    public Products toEntity(ProductsResponse response) {
        return modelMapper.map(response, Products.class);
    }
}
