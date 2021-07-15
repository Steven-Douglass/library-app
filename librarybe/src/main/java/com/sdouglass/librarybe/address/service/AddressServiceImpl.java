package com.sdouglass.librarybe.address.service;

import com.sdouglass.librarybe.address.dao.AddressDAO;
import com.sdouglass.librarybe.address.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressDAO addressDAO;

    @Autowired
    public AddressServiceImpl(AddressDAO addressDAO){
        this.addressDAO = addressDAO;
    }

    @Override
    @Transactional
    public Address getAddress(Integer id) {
        Address address = addressDAO.getAddress(id);
        if (address == null) {
            throw new RuntimeException("Address ID not found: " + id);
        }
        return address;
    }

    @Override
    @Transactional
    public List<Address> getAllAddresses() {
        return addressDAO.getAllAddresses();
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        try {
            addressDAO.saveAddress(address);
        } catch (Exception e) {
            System.out.println("Exception caught when saving address: " + e);
        }
    }

    @Override
    @Transactional
    public String deleteAddress(Integer id) {
        Address address = getAddress(id);
        addressDAO.deleteAddress(address.getAddressID());
        return "Deleted address with ID: " + id;
    }
}
