package com.onlineShopping.Web.dto.mappers;

import com.onlineShopping.Web.entities.Users;
import com.onlineShopping.Web.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<UserRequest, Users>,DTOMapper<UserRequest,Users> {


    @Override
    public Users apply(UserRequest userRequest) {
        return new Users(userRequest.getPassword(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getNumber());
    }

    @Override
    public UserRequest toRequestTo(Users entity) {
        return modelMapper.map(entity,UserRequest.class);
    }

    @Override
    public Users toEntity(UserRequest request) {
        return null;
    }
}
