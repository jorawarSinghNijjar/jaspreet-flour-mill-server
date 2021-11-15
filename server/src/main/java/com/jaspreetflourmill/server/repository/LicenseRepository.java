package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.License;
import com.jaspreetflourmill.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License,String> {
}
