package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales,String> {
    List<Sales> findByMonthAndYear(int month,int year);
    List<Sales> findByYear(int year);
}
