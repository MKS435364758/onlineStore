package com.onlineShopping.Web.response;

import com.onlineShopping.Web.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateResponse {

    private String message;

    private String username;

    private String firstName;


    private String lastName;


    private String email;

    private String number;

    private String role;

    public RoleUpdateResponse(String message,Users users){
        this.message = message;
        this.username = users.getUsername();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
        this.number = users.getNumber();
        this.role = users.getRole();
    }

    public RoleUpdateResponse(String message){
        this.message = message;
    }
}
