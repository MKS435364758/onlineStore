package com.onlineShopping.Web.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "first name can't be null")
    @Size(min = 3,message = "first name can't be that short")
    @Size(max = 20,message = "first name can't be that long")
    private String firstName;

    @NotNull(message = "last name can't be null")
    @Size(min = 3,message = "last name can't be that short")
    @Size(max = 20,message = "last name can't be that long")
    private String lastName;

    @NotNull(message = "email cant be null")
    @Email(message = "invalid email")
    private String email;

    @NotNull(message = "phone number cant be null")
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String number;

    @NotNull(message = "password cant be null")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;

}
