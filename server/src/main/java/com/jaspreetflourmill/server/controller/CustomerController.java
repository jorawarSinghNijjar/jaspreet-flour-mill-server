package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<Customer> list(){
        return customerService.listAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Integer id){
        try{
            Customer customer = customerService.getCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<String> add(@RequestBody Customer customer){
        try{
            customerService.saveCustomer(customer);
            return new ResponseEntity<>("Customer Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<String> update(
            @RequestBody Customer customer, @PathVariable Integer id
    )
    {
        try{
            Customer existingCustomer = customerService.getCustomer(id);
                customer.setCustomerId(id);
                customerService.saveCustomer(customer);
                return new ResponseEntity<>("Customer Updated Successfully",HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
