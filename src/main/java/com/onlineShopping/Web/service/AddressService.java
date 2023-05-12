package com.onlineShopping.Web.service;

import com.onlineShopping.Web.entities.Address;
import com.onlineShopping.Web.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    Address saveOrders(Address address) {
        return addressRepository.save(address);
    }

    Address getOrders(String id) {
        return addressRepository.findById(id).get();
    }

    void deleteOrders(String id) {
        addressRepository.deleteById(id);
    }

}
