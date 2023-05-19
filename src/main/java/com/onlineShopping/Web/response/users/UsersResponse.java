package com.onlineShopping.Web.response.users;


import com.onlineShopping.Web.entities.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UsersResponse {


    private String username;


    private String firstName;


    private String lastName;


    private String email;

    private String number;

    public UsersResponse(Users users){
        this.username = users.getUsername();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
        this.number = users.getNumber();
    }


}
