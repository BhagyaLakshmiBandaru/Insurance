package com.insurance.project.service;

import com.insurance.project.dto.UserEntity;
import com.insurance.project.repository.UserRepository1;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final UserRepository1 userRepository;

    public UserService(UserRepository1 userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
