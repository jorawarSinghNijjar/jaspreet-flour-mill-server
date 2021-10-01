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
        System.out.println("Getting stocks");
        try{
            Stock stock = stockService.getStock(1);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            stockService.saveStock(new Stock());
            Stock stock = stockService.getStock(1);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody Stock stock){
        try{
            System.out.println("Storing wheat --> " + stock.getWheatBalance());
            stockService.saveStock(stock);
            return new ResponseEntity<>("Sales Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Stock update Failed!!!!");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody Stock stock
    )
    {
        try{
            Stock existingStock = stockService.getStock(1);
            stock.setId(1);
            stockService.saveStock(stock);
            return new ResponseEntity<>("Stock Updated Successfully",HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
