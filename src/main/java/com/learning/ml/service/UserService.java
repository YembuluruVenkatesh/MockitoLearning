package com.learning.ml.service;

import com.learning.ml.entity.User;
import com.learning.ml.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.learning.ml.exception.UserNotFoundException;


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

    public void register(String name) {
        repo.save(new User(name.toUpperCase()));
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public void registerTwice(String name) {
        repo.save(new User(name.toUpperCase()));
        repo.save(new User(name.toUpperCase()));
    }

    public User getUser(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }

    public void deleteUser(long id) {
        repo.deleteById(id);  // this is a void method
    }
}
