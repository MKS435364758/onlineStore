package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.ProductRequest;
import com.onlineShopping.Web.repository.AddressRepository;
import com.onlineShopping.Web.repository.ProductsRepository;
import com.onlineShopping.Web.repository.UsersRepository;
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

    public Products saveProducts(ProductRequest products) {
        Products product = products.getProduct();
        product.setAddress(new Address());
        productsRepository.save(product);
        Address address = products.getAddress();
        address.setUser(usersService.getUserById(products.getUserId()));
        addressRepository.save(address);
        product.setAddress(address);
        productsRepository.save(product);
        return product;
    }

    public Products getProductsById(String id) {
        return productsRepository.findById(id).get();
    }


    public List<Products> getAllProducts() {
        return productsRepository.findAll();
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
        productsRepository.delete(productsRepository.findById(id).get());
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
