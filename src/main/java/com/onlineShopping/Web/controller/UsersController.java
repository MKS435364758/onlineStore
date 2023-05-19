package com.onlineShopping.Web.controller;

import com.onlineShopping.Web.entities.Orders;
import com.onlineShopping.Web.request.RoleUpdateRequest;
import com.onlineShopping.Web.request.UserRequest;
import com.onlineShopping.Web.response.exceptions.DataNoFoundResponse;
import com.onlineShopping.Web.response.users.UserSignUpErrorResponse;
import com.onlineShopping.Web.response.users.UsersResponse;
import com.onlineShopping.Web.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "get user info by email",description = "Retrieving user info by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successful",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400",description = "No Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = DataNoFoundResponse.class))) })
    ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(usersService.getUserByEmail(email),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "get all user",description = "to retrieve all user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successful",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400",description = "No Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = DataNoFoundResponse.class))) })
    ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(usersService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/users/", params = {"userid"})
    @Operation(summary = "get user info endpoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User created",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "404",description = "No Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = DataNoFoundResponse.class))) })
    ResponseEntity<UsersResponse> getUserById(@PathVariable("userid") String userId) {
        int i = 0;
        return new ResponseEntity<>(usersService.getUserResponseById(userId),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/update/role/")
    @Operation(summary = "user role update end point")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Accepted",content = @Content(mediaType = "application/json",schema = @Schema(implementation = RoleUpdateRequest.class))),
            @ApiResponse(responseCode = "400",description = "Something when wrong")})
    ResponseEntity<Object> updateUserRoleByAdmin(@RequestBody RoleUpdateRequest request){
        return new ResponseEntity<>(usersService.updateUserRoleByAdmin(request),
                HttpStatus.ACCEPTED
        );
    }

    //todo have to change the incoming user object to userRequest object
    @PostMapping("/sign-up/")
    @Operation(summary = "user sign up end point")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User created",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserSignUpErrorResponse.class))) })
    ResponseEntity<Object> createNewUser(@Valid @RequestBody UserRequest userRequest) throws ValidationException {
        return new ResponseEntity<>(usersService.saveUser(userRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping(params = {"userId"})
    @Operation(summary = "get user info by user id",description = "to retrieve user by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successful",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400",description = "No Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = DataNoFoundResponse.class))) })
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
