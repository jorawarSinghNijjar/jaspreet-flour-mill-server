package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}
