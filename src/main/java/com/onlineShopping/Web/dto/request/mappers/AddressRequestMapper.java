package com.onlineShopping.Web.dto.request.mappers;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.request.address.AddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestMapper implements RequestDTOMapper<AddressRequest, Address> {
    @Override
    public AddressRequest toRequest(Address entity) {
        return modelMapper.map(entity, AddressRequest.class);
    }

    @Override
    public Address toEntity(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }
}
