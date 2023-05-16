package com.onlineShopping.Web.controller;


import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.OrderRequest;
import com.onlineShopping.Web.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    OrdersService ordersService;

    @GetMapping("/all/")
    public ResponseEntity<List<Orders>> getAllOrder() {
        return new ResponseEntity<>(ordersService.getAllOrders(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products/", params = {"orderId"})
    public ResponseEntity<List<Products>> getProductsByOrderId(@RequestParam("orderId") String orderId) {
        return new ResponseEntity<>(ordersService.getProductsByOrderId(orderId),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"orderId"})
    public ResponseEntity<Orders> getOrdersById(@RequestParam("orderId") String orderId) {
        return new ResponseEntity<>(ordersService.getOrdersById(orderId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Orders> saveOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(ordersService.saveOrders(orderRequest),
                HttpStatus.CREATED
        );
    }

}
