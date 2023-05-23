package com.onlineShopping.Web.dto.request.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public interface RequestDTOMapper<R,E> {

    ModelMapper modelMapper = new ModelMapper();

    R toRequest(E entity);

    E toEntity(R request);

}
