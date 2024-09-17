package com.insurance.project.dao;

import com.insurance.project.dto.Address;
import com.insurance.project.repository.AddressRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDao {
    private AddressRespository addressRespository;
    @Autowired
    public AddressDao(AddressRespository addressRespository) {
        this.addressRespository = addressRespository;
    }
    public Optional<Address> findById(int addressId) {
        return addressRespository.findById(addressId);
    }

}
