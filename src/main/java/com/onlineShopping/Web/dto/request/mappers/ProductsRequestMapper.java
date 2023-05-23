package com.onlineShopping.Web.dto.request.mappers;

import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.products.ProductsPojo;
import org.springframework.stereotype.Component;

@Component
public class ProductsRequestMapper implements RequestDTOMapper<ProductsPojo, Products> {

    @Override
    public ProductsPojo toRequest(Products entity) {
        return modelMapper.map(entity, ProductsPojo.class);
    }

    @Override
    public Products toEntity(ProductsPojo request) {
        return null;
    }
}
