package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Stock;
import com.jaspreetflourmill.server.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void saveStock(Stock stock){
        stockRepository.save(stock);
    }

    public Stock getStock(int i) {
        return stockRepository.findById(1).get();
    }
}
