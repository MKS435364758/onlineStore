package com.onlineShopping.Web.dto.mappers;

import org.modelmapper.ModelMapper;

public interface DTOMapper<R,E> {

    ModelMapper modelMapper = new ModelMapper();

    R toRequestTo(E entity);

    E toEntity(R request);

}
