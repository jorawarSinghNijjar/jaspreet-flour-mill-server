package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
