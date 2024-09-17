package com.insurance.project.repository;

import com.insurance.project.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRespository extends JpaRepository<Address,Integer> {
}
