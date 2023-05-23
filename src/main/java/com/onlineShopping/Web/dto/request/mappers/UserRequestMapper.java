package com.onlineShopping.Web.dto.request.mappers;

import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRequestMapper implements Function<UserRequest, Users>, RequestDTOMapper<UserRequest,Users> {


    @Override
    public Users apply(UserRequest userRequest) {
        return new Users(userRequest.getPassword(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getNumber());
    }

    @Override
    public UserRequest toRequest(Users entity) {
        return modelMapper.map(entity,UserRequest.class);
    }

    @Override
    public Users toEntity(UserRequest request) {
        return null;
    }
}
