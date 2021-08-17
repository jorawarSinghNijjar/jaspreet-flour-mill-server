package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.SalesToday;
import com.jaspreetflourmill.server.model.Transaction;
import com.jaspreetflourmill.server.service.CustomerService;
import com.jaspreetflourmill.server.service.SalesService;
import com.jaspreetflourmill.server.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<Transaction> list(){
        return transactionService.listAllTransactions();
    }

    @GetMapping("/query")
    public List<Transaction> listByCustomer(@RequestParam String customerId){
        Integer customerIdInt = Integer.parseInt(customerId);
        Customer customer = customerService.getCustomer(customerIdInt);
        return transactionService.findByCustomer(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable String id){
        try{
            Transaction transaction = transactionService.getTransaction(id);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sales")
    public ResponseEntity getSalesForToday(){
        try{
            SalesToday salesToday = transactionService.getSalesForToday();
            return new ResponseEntity<>(salesToday,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("")
    public @ResponseBody ResponseEntity<String> add(@RequestBody Transaction transaction){
        try{
            transactionService.saveTransaction(transaction);
            return new ResponseEntity<>("Transaction Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody Transaction transaction, @PathVariable String id
    )
    {
        try{
            Transaction existingTransaction = transactionService.getTransaction(id);
            transaction.setTransactionId(id);
            transactionService.saveTransaction(transaction);
            return new ResponseEntity<>("Transaction Updated Successfully",HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        transactionService.deleteTransaction(id);
    }
}
