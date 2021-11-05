package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.service.CustomerService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
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
    public ResponseEntity<List<Customer>> list(){
        try {
            List<Customer> customers = customerService.listAllCustomers().orElseThrow();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Integer id){
        try{
            Customer customer = customerService.getCustomer(id).orElseThrow();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            System.out.println("Customer not found !");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Customer> add(@RequestBody Customer customer){
        try{
            Customer savedCustomer = customerService.saveCustomer(customer).orElseThrow();
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Customer> update(
            @RequestBody Customer customer, @PathVariable Integer id
    )
    {
        try{
                Customer existingCustomer = customerService.getCustomer(id).orElseThrow();
                customer.setCustomerId(id);
                Customer updatedCustomer = customerService.saveCustomer(customer).orElseThrow();
                return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        customerService.deleteCustomer(id);
    }
}
