package com.insurance.project.repository;

import com.insurance.project.dto.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository1 extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
