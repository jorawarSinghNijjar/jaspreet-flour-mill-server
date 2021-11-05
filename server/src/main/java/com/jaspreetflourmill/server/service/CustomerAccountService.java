package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.repository.CustomerAccountRepository;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerAccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    public List<CustomerAccount> listAllCustomerAccounts(){
        return customerAccountRepository.findAll();
    }

    public void saveCustomerAccount(CustomerAccount customerAccount){
        customerAccountRepository.save(customerAccount);
    }

    public CustomerAccount getCustomerAccount(Integer id){
        return customerAccountRepository.findCustomerAccountByCustomer(id);
    }
    public void deleteCustomerAccount(Integer id){
        customerAccountRepository.deleteById(id);
    }

}
