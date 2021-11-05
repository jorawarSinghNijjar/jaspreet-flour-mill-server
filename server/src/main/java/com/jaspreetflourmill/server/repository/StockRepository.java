package com.jaspreetflourmill.server.repository;

import com.jaspreetflourmill.server.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
