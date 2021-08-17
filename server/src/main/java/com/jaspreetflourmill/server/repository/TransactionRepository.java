package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

    List<Transaction> findByCustomer(Customer customer, Sort sort);

    @Query(value = "SELECT SUM(attaPickupQty) FROM transactions WHERE date = ?1", nativeQuery = true)
    Double getWheatSalesToday(String date);

    @Query(value = "SELECT SUM(grindingChargesPaid) FROM transactions WHERE date = ?1", nativeQuery = true)
    Double getGrindingChargesPaidToday(String date);

    @Query(value = "SELECT SUM(grindingCharges) FROM transactions WHERE date = ?1", nativeQuery = true)
    Double getGrindingChargesToday(String date);

}
