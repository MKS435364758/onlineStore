package com.onlineShopping.Web.controller;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.request.RoleUpdateRequest;
import com.onlineShopping.Web.request.UserRequest;
import com.onlineShopping.Web.response.UsersResponse;
import com.onlineShopping.Web.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class UsersController {

    UsersService usersService;


    @GetMapping("/{email}")
    ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(usersService.getUserByEmail(email),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(usersService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/users/", params = {"userid"})
    ResponseEntity<UsersResponse> getUserById(@PathVariable("userid") String userId) {
        int i = 0;
        return new ResponseEntity<>(usersService.getUserResponseById(userId),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/update/role/")
    ResponseEntity<Object> updateUserRoleByAdmin(@RequestBody RoleUpdateRequest request){
        return new ResponseEntity<>(usersService.updateUserRoleByAdmin(request),
                HttpStatus.ACCEPTED
        );
    }

    //todo have to change the incoming user object to userRequest object
    @PostMapping("/sign-up/")
    ResponseEntity<Object> createNewUser(
            @Valid
            @RequestBody UserRequest userRequest) throws ValidationException {
        return new ResponseEntity<>(usersService.saveUser(userRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping(params = {"userId"})
    public ResponseEntity<List<Orders>> getOrderMadeByUserId(@RequestParam("userId") String id) {
        return new ResponseEntity<>(usersService.getOrdersMadeByUserId(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/account/{id}")
    ResponseEntity<Object> deletingUser(@PathVariable("id") String id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

}
