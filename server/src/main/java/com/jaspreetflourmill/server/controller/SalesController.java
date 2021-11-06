package com.jaspreetflourmill.server.controller;


import com.jaspreetflourmill.server.model.Sales;
import com.jaspreetflourmill.server.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;

    @GetMapping("")
    public ResponseEntity<List<Sales>> list(){
        try {
            List<Sales> salesList = salesService.listAllSales().orElseThrow();
            return new ResponseEntity<>(salesList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{date}")
    public ResponseEntity get(@PathVariable String date){
        try{
            Sales sales = salesService.getSales(date).orElseThrow();
            return new ResponseEntity<>(sales, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monthly/{month}/{year}")
    public ResponseEntity<List<Sales>> getMonthlySales(@PathVariable int month, @PathVariable int year ) {
        try {
            List<Sales> monthlySalesList = salesService.getSalesForMonth(month,year).orElseThrow();
            return new ResponseEntity<>(monthlySalesList,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/yearly/{year}")
    public ResponseEntity<List<Sales>> getYearlySales(@PathVariable int year ) {
       try {
           List<Sales> yearlySalesList = salesService.getYearlySales(year).orElseThrow();
           return new ResponseEntity<>(yearlySalesList, HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Sales> add(@RequestBody Sales sales){
        try{
            Sales savedSale = salesService.saveSales(sales).orElseThrow();
            return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{date}")
    public @ResponseBody ResponseEntity<Sales> update(
            @RequestBody Sales sales, @PathVariable String date
    )
    {
        try{
            Sales existingSales = salesService.getSales(date).orElseThrow();
            sales.setDate(date);
            Sales updatedSale = salesService.saveSales(sales).orElseThrow();
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
