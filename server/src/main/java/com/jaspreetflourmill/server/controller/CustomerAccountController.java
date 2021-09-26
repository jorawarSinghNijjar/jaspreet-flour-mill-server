package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.service.CustomerAccountService;
import com.jaspreetflourmill.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer-accounts")
public class CustomerAccountController {
    @Autowired
    CustomerAccountService customerAccountService;

    @GetMapping("")
    public List<CustomerAccount> list(){
        return customerAccountService.listAllCustomerAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAccount> get(@PathVariable Integer id){
        try{
            CustomerAccount customerAccount = customerAccountService.getCustomerAccount(id);
            return new ResponseEntity<>(customerAccount, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            System.out.println("Customer account not found");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<String> add(@RequestBody CustomerAccount customerAccount){
        try{
            System.out.println("Registering cusomer account --> "+ customerAccount.getCustomer().getName());
            customerAccountService.saveCustomerAccount(customerAccount);
            return new ResponseEntity<>("Customer Account Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Customer account registration failed !");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody CustomerAccount customerAccount, @PathVariable Integer id
    )
    {
        try {
                CustomerAccount existingCustomerAccount = customerAccountService.getCustomerAccount(id);
                customerAccount.setCustomerAccountId(id);
                customerAccountService.saveCustomerAccount(customerAccount);
                return new ResponseEntity<>("Customer Account Updated Successfully",HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Customer account update failed !");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        customerAccountService.deleteCustomerAccount(id);
    }
}
