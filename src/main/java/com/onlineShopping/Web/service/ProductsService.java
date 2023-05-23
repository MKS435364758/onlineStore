package com.onlineShopping.Web.service;

import com.onlineShopping.Web.dto.request.mappers.AddressRequestMapper;
import com.onlineShopping.Web.dto.request.mappers.ProductsRequestMapper;
import com.onlineShopping.Web.dto.response.mapper.ProductsResponseMapper;
import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.exception.DataNotFound;
import com.onlineShopping.Web.exception.InternalServerException;
import com.onlineShopping.Web.repository.AddressRepository;
import com.onlineShopping.Web.repository.ProductsRepository;
import com.onlineShopping.Web.repository.UsersRepository;
import com.onlineShopping.Web.request.products.ProductsRequest;
import com.onlineShopping.Web.response.product.ProductsResponse;
import com.onlineShopping.Web.tools.UnPackOptional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsService {

    ProductsRepository productsRepository;

    AddressRepository addressRepository;

    UsersRepository usersRepository;

    UsersService usersService;

    ProductsRequestMapper productsRequestMapper;

    ProductsResponseMapper productsResponseMapper;

    AddressRequestMapper addressRequestMapper;


    public ProductsResponse saveProducts(ProductsRequest products) {
//        Products product = products.getProduct();
        Products product = productsRequestMapper.toEntity(products.getProductsPojo());
        //product.setAddress(new Address());
        productsRepository.save(product);
        Address address = addressRequestMapper.toEntity(products.getAddressRequest());
        address.setUser(usersService.getUserById(products.getUserId()));
        addressRepository.save(address);
        product.setAddress(address);
        productsRepository.save(product);
        return productsResponseMapper.toResponse(product);
    }

    public ProductsResponse getProductsById(String id) {
        try {
            return productsResponseMapper.toResponse(UnPackOptional.getObject(productsRepository.findById(id)));
        }catch (RuntimeException ex){
            throw new DataNotFound("No product found");
        }
    }


    public List<ProductsResponse> getAllProducts() {
        try {
            return productsRepository.findAll().stream().map(i->productsResponseMapper.toResponse(i)).collect(Collectors.toList());
        }catch (RuntimeException ex){
            throw new InternalServerException();
        }
    }

    public List<Products> getProductsByPrices(BigDecimal price, int page, int size) {
        List<Products> products = productsRepository.findAll().stream().filter(i -> {
            if (i.getListedPrice().compareTo(price) != 1) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return products.subList(page * size, Math.min((page * size) + size, products.size()));
    }

    public void deleteProducts(String id) {
        productsRepository.delete(UnPackOptional.getObject(productsRepository.findById(id)));
    }

    public List<Products> getProductsByDiscount(int discount, int page, int size) {
        double d = discount / (double) 100;
        return productsRepository.findAll().stream().filter(i -> {
            if (i.getDiscountPercentage().compareTo(d) != 1) {
                return true;
            }
            return false;
        }).collect(Collectors.toList()).subList(page * size, Math.min((page * size) + size, productsRepository.findAll().size()));
    }
}
