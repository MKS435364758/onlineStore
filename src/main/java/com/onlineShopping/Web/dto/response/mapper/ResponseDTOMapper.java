package com.onlineShopping.Web.dto.response.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public interface ResponseDTOMapper<R,E> {

    ModelMapper modelMapper = new ModelMapper();

    R toResponse(E entity);

    E toEntity(R response);

}
