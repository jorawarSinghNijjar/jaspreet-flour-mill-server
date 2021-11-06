package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Integer> {
    @Query("SELECT c FROM CustomerAccount c WHERE c.customer.customerId = ?1")
    Optional<CustomerAccount> findCustomerAccountByCustomer(Integer customerId);

}
