package com.onlineShopping.Web.controller;


import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.pojo.ProductsPojo;
import com.onlineShopping.Web.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductsController {

    ProductsService productsService;


    @GetMapping("/id/")
    public ResponseEntity<Products> getProductsById(@RequestBody String id) {
        return new ResponseEntity<>(productsService.getProductsById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Products> saveProduct(@RequestBody ProductsPojo productsPojo) {
        return new ResponseEntity<>(productsService.saveProducts(productsPojo),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all/")
    public ResponseEntity<List<Products>> getAllProducts() {
        return new ResponseEntity<>(productsService.getAllProducts(),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"price", "page", "size"})
    public ResponseEntity<List<Products>> getProductsByprices(@RequestParam("price") BigDecimal price, @RequestParam("page") int page,
                                                              @RequestParam("size") int size) {
        return new ResponseEntity<>(productsService.getProductsByPrices(price, page, size),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"discount", "page", "size"})
    public ResponseEntity<List<Products>> getProductsByDiscount(@RequestParam("discount") int discount, @RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        return new ResponseEntity<>(productsService.getProductsByDiscount(discount, page, size),
                HttpStatus.OK
        );
    }


//    @GetMapping(params = {"id","image","description","listed_price","discount","availability","id","name","number","line_one"
//            "line_two",})

//    @GetMapping("/saveAddress")
//    public ResponseEntity<Object> saveAddress(){
//
//        List<Users> users = usersRepository.findAll();//.stream().filter(x->{if(x.getRole().equals("admin")) return true;return false;}).collect(Collectors.toList());
//        addressRepository.findAll().stream().forEach(i->{
//            i.setUser(users.get(new Random().nextInt(0,users.size())));
//            addressRepository.save(i);
//        });
//
//        return new ResponseEntity<>( addressRepository.findAll(),
//                HttpStatus.OK);
//    }

}
