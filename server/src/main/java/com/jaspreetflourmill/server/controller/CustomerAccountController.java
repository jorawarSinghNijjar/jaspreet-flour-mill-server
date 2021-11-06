package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.service.CustomerAccountService;
import com.jaspreetflourmill.server.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/customer-accounts")
public class CustomerAccountController {

    @Autowired
    CustomerAccountService customerAccountService;

    @GetMapping("")
    public ResponseEntity<List<CustomerAccount>> list(){
        try{
            List<CustomerAccount> customerAccounts = customerAccountService.listAllCustomerAccounts().orElseThrow();
            return new ResponseEntity<>(customerAccounts, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAccount> get(@PathVariable Integer id){
        try{
            CustomerAccount customerAccount = customerAccountService.getCustomerAccount(id).orElseThrow();
            return new ResponseEntity<>(customerAccount, HttpStatus.OK);
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

    @PostMapping("")
    public @ResponseBody ResponseEntity<CustomerAccount> add(@RequestBody CustomerAccount customerAccount){
        try{
            CustomerAccount savedCustomerAccount = customerAccountService.saveCustomerAccount(customerAccount).orElseThrow();
            return new ResponseEntity<>( savedCustomerAccount, HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{customerId}")
    public @ResponseBody ResponseEntity<CustomerAccount> update(
            @RequestBody CustomerAccount customerAccount, @PathVariable Integer customerId
    )
    {
        try {
                CustomerAccount existingCustomerAccount = customerAccountService.getCustomerAccount(customerId).orElseThrow();
                customerAccount.setCustomer(existingCustomerAccount.getCustomer());
                CustomerAccount updatedCustomerAccount = customerAccountService.saveCustomerAccount(customerAccount).orElseThrow();

                return new ResponseEntity<>(updatedCustomerAccount,HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        customerAccountService.deleteCustomerAccount(id);
    }
}
