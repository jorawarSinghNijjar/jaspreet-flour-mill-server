package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Integer> {

}
