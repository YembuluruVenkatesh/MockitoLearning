package com.learning.ml.service;

import com.learning.ml.entity.User;
import com.learning.ml.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void saveUser(String name) {

        // Business logic: convert name to uppercase before saving
        User user = new User(name.toUpperCase());

        repo.save(user);
    }
}
