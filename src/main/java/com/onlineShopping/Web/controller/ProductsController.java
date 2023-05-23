package com.onlineShopping.Web.controller;


import com.onlineShopping.Web.entities.Products;
import com.onlineShopping.Web.request.products.ProductsRequest;
import com.onlineShopping.Web.response.exceptions.DataNoFoundResponse;
import com.onlineShopping.Web.response.product.ProductsResponse;
import com.onlineShopping.Web.response.users.UsersResponse;
import com.onlineShopping.Web.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "get product info by id",description = "Retrieving product info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successful",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400",description = "No Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = DataNoFoundResponse.class))) })
    public ResponseEntity<ProductsResponse> getProductsById(@RequestBody String id) {
        return new ResponseEntity<>(productsService.getProductsById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProductsResponse> saveProduct(@RequestBody ProductsRequest productRequest) {
        return new ResponseEntity<>(productsService.saveProducts(productRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all/")
    public ResponseEntity<List<ProductsResponse>> getAllProducts() {
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
