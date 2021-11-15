package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    Optional<User> getUser(String id);

    Optional<User> findByResetToken(String resetToken);
}
