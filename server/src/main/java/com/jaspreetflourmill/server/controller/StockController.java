package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.Sales;
import com.jaspreetflourmill.server.model.Stock;
import com.jaspreetflourmill.server.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/get")
    public ResponseEntity<Stock> get(){
        try{
            Stock stock = stockService.getStock(1).orElseThrow();
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            stockService.saveStock(new Stock());
            Stock stock = stockService.getStock(1).orElseThrow();
            return new ResponseEntity<>(stock, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public @ResponseBody
    ResponseEntity<Stock> save(@RequestBody Stock stock){
        try{
            Stock savedStock = stockService.saveStock(stock).orElseThrow();
            return new ResponseEntity<>(savedStock,HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody Stock stock
    )
    {
        try{
            Stock existingStock = stockService.getStock(1).orElseThrow();
            stock.setId(1);
            Stock updatedStock = stockService.saveStock(stock).orElseThrow();
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
