package com.onlineShopping.Web.dto.mappers;

import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.products.ProductsRequest;

public class ProductsDTOMapper implements DTOMapper<ProductsRequest, Products> {

    @Override
    public ProductsRequest toRequestTo(Products entity) {
        return modelMapper.map(entity, ProductsRequest.class);
    }

    @Override
    public Products toEntity(ProductsRequest request) {
        return null;
    }
}
