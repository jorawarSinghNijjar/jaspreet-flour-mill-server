package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.Transaction;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import com.jaspreetflourmill.server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> listAllTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> findByCustomer(Customer customer){
        return transactionRepository.findByCustomer(customer);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public Transaction getTransaction(String id){
        return transactionRepository.findById(id).get();
    }

    public void deleteTransaction(String id){
        transactionRepository.deleteById(id);
    }

}
