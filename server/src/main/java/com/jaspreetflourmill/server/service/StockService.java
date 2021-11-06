package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Stock;
import com.jaspreetflourmill.server.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Optional<Stock> saveStock(Stock stock){
        return Optional.of(stockRepository.save(stock));
    }

    public Optional<Stock> getStock(int i) {
        return stockRepository.findById(1);
    }
}
