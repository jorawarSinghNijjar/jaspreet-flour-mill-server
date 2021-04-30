package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import com.jaspreetflourmill.server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAllCustomers(){
        return customerRepository.findAll();
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getCustomer(Integer id){
        return customerRepository.findById(id).get();
    }
    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }
}
