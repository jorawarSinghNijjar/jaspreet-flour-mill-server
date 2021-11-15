package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Optional<Employee> findByUser(User user);

    Optional<Employee> findByEmailId(String emailId);
}
