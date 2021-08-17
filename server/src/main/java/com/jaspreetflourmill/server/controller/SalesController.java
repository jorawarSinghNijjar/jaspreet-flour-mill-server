package com.jaspreetflourmill.server.controller;


import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Sales;
import com.jaspreetflourmill.server.service.CustomerService;
import com.jaspreetflourmill.server.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;

    @GetMapping("")
    public List<Sales> list(){
        return salesService.listAllSales();
    }

    @GetMapping("/{date}")
    public ResponseEntity get(@PathVariable String date){
        try{
            System.out.println(date);
            Sales sales = salesService.getSales(date);
            return new ResponseEntity<>(sales, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/monthly/{month}/{year}")
    public List<Sales> getMonthlySales(@PathVariable int month,@PathVariable int year ) {
        return salesService.getSalesForMonth(month,year);
    }

    @GetMapping("/yearly/{year}")
    public List<Sales> getMonthlySales(@PathVariable int year ) {
        return salesService.getYearlySales(year);
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<String> add(@RequestBody Sales sales){
        try{
            System.out.println("Registering Sales --> " + sales.getDate());
            salesService.saveSales(sales);
            return new ResponseEntity<>("Sales Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Sales Registration Failed!!!!");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{date}")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody Sales sales, @PathVariable String date
    )
    {
        try{
            Sales existingSales = salesService.getSales(date);
            sales.setDate(date);
            salesService.saveSales(sales);
            return new ResponseEntity<>("Sales Updated Successfully",HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
