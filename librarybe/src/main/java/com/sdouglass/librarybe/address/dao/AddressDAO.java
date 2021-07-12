package com.sdouglass.librarybe.address.dao;

import com.sdouglass.librarybe.address.entity.Address;

import java.util.List;

public interface AddressDAO {
    public Address getAddress(Integer id);
    public List<Address> getAllAddresses();
    public void saveAddress(Address address);
    public void deleteAddress(Integer id);
}
