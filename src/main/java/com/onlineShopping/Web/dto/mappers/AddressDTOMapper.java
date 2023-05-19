package com.onlineShopping.Web.dto.mappers;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.request.address.AddressRequest;

public class AddressDTOMapper implements DTOMapper<AddressRequest, Address>{
    @Override
    public AddressRequest toRequestTo(Address entity) {
        return modelMapper.map(entity, AddressRequest.class);
    }

    @Override
    public Address toEntity(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }
}
