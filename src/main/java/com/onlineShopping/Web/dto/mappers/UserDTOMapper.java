package com.onlineShopping.Web.dto.mappers;

import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<UserRequest, Users> {


    @Override
    public Users apply(UserRequest userRequest) {
        return new Users(userRequest.getPassword(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getNumber());
    }
}
