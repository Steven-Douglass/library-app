package com.sdouglass.librarybe.address.service;

import com.sdouglass.librarybe.address.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public Address getAddress(Integer id);
    public List<Address> getAllAddresses();
    public void saveAddress(Address address);
    public String deleteAddress(Integer id);
}
