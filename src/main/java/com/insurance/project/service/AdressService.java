package com.insurance.project.service;

import com.insurance.project.dao.AddressDao;
import com.insurance.project.dto.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AdressService {
    @Autowired
    private AddressDao addressDao;

    @Async
    public CompletableFuture<Address> getAddressByPolicyIdAsync(int addressId) {
        // Retrieve address details from the DAO by policy ID
        Optional<Address> addressOptional = addressDao.findById(addressId);
        return CompletableFuture.completedFuture(addressOptional.orElse(null));
    }
}
