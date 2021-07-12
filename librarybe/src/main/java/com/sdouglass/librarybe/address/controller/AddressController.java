package com.sdouglass.librarybe.address.controller;

import com.sdouglass.librarybe.address.entity.Address;
import com.sdouglass.librarybe.address.service.AddressService;
import com.sdouglass.librarybe.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final LibraryService libraryService;
    private final AddressService addressService;

    @Autowired
    public AddressController(LibraryService libraryService, AddressService addressService) {
        this.libraryService = libraryService;
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id) {
        return addressService.getAddress(id);
    }

    @GetMapping("")
    public List<Address> getAddresses() {
        return addressService.getAllAddresses();
    }

    @PostMapping("")
    public Address addAddress(@RequestBody Address address) {
        addressService.saveAddress(address);
        return address;
    }

    @PutMapping("")
    public Address updateAddress(@RequestBody Address address) {
        addressService.saveAddress(address);
        return address;
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        return addressService.deleteAddress(id);
    }
}
