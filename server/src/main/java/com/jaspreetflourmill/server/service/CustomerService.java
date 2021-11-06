package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import com.jaspreetflourmill.server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<List<Customer>> listAllCustomers(){
        return Optional.of(customerRepository.findAll());
    }

    public Optional<Customer> saveCustomer(Customer customer){
        return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> getCustomer(Integer id){

        return customerRepository.findById(id);
    }
    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }
}
