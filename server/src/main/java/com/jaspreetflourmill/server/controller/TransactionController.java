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
    public ResponseEntity<List<Transaction>> list(){
        try{
           List<Transaction> transactions = transactionService.listAllTransactions().orElseThrow();
           return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/query")
    public ResponseEntity<List<Transaction>> listByCustomer(@RequestParam String customerId){
        try{
            Integer customerIdInt = Integer.parseInt(customerId);
            Customer customer = customerService.getCustomer(customerIdInt).orElseThrow();
            List<Transaction> transactions = transactionService.findByCustomer(customer).orElseThrow();
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable String id){
        try{
            Transaction transaction = transactionService.getTransaction(id).orElseThrow();
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<SalesToday> getSalesForToday(){
        try{
            SalesToday salesToday = transactionService.getSalesForToday();
            return new ResponseEntity<>(salesToday,HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("")
    public @ResponseBody ResponseEntity<Transaction> add(@RequestBody Transaction transaction){
        try{
            Transaction savedTransaction = transactionService.saveTransaction(transaction).orElseThrow();
            return new ResponseEntity<>(savedTransaction,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Transaction> update(
            @RequestBody Transaction transaction, @PathVariable String id
    )
    {
        try{
            Transaction existingTransaction = transactionService.getTransaction(id).orElseThrow();
            transaction.setTransactionId(id);
            Transaction updatedTransaction = transactionService.saveTransaction(transaction).orElseThrow();
            return new ResponseEntity<>(updatedTransaction,HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        transactionService.deleteTransaction(id);
    }
}
