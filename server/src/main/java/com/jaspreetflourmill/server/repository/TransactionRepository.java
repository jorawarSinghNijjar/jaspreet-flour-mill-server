package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

    List<Transaction> findByCustomer(Customer customer);
}
