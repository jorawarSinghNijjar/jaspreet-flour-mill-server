package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
