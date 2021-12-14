package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.repository.CustomerAccountRepository;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerAccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<List<CustomerAccount>> listAllCustomerAccounts(){
        return Optional.of(customerAccountRepository.findAll());
    }

    public Optional<CustomerAccount> saveCustomerAccount(CustomerAccount customerAccount){
        return Optional.of(customerAccountRepository.save(customerAccount));
    }

    public Optional<CustomerAccount> getCustomerAccount(Integer id){
        return customerAccountRepository.findCustomerAccountByCustomer(id);
    }
    public void deleteCustomerAccount(Integer id){
        Customer customer = customerRepository.getOne(id);
        customerAccountRepository.deleteByCustomerId(customer.getCustomerId());
    }

}
