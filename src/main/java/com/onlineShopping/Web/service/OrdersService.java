package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Payments;
import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.enums.PaymentStatus;
import com.onlineShopping.Web.enums.Status;
import com.onlineShopping.Web.request.OrderRequest;
import com.onlineShopping.Web.repository.OrdersRepository;
import com.onlineShopping.Web.repository.PaymentsRepository;
import com.onlineShopping.Web.repository.ProductsRepository;
import com.onlineShopping.Web.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {

    OrdersRepository ordersRepository;

    ProductsRepository productsRepository;

    UsersService usersService;

    UsersRepository usersRepository;

    PaymentsRepository paymentsRepository;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }


    public Orders saveOrders(OrderRequest orderRequest) {

        Orders order = new Orders();

        order.setStatus(String.valueOf(Status.PENDING));

        order.setUser(usersService.getUserByUserId(orderRequest.getUserId()));
        List<Products> products = new ArrayList<>();

//        productsRepository.findAllById(orderPojo.getProductsIds());

        orderRequest.getProductsIds().forEach(i -> {
            if (productsRepository.findById(i).isPresent()) products.add(productsRepository.findById(i).get());
        });

        order.setProducts(products);
        System.out.println(products.size());
        Payments payment = new Payments();

        payment.setOrder(order);

        payment.setStatus(String.valueOf(PaymentStatus.PENDING));

        paymentsRepository.save(payment);

        order.setPayment(payment);

        Products p = new Products();

        p.setListedPrice(new BigDecimal(0));

        BigDecimal d = new BigDecimal(0);

        order.setFinalAmount(products.stream().map(Products::getListedPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        ordersRepository.save(order);

        usersService.updateOrdersList(orderRequest.getUserId(), order.getId());

        return ordersRepository.findById(order.getId()).get();
    }

    public Orders getOrderById(String orderId) {
        return ordersRepository.findById(orderId).get();
    }

    public List<Products> getProductsByOrderId(String orderId) {
        return ordersRepository.findById(orderId).get().getProducts();
    }

    public Orders getOrdersById(String id) {
        return ordersRepository.findById(id).get();
    }

    public void deleteOrders(String id) {
        ordersRepository.deleteById(id);
    }
}
