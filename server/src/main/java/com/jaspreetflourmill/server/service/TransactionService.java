package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.SalesToday;
import com.jaspreetflourmill.server.model.Transaction;
import com.jaspreetflourmill.server.repository.CustomerRepository;
import com.jaspreetflourmill.server.repository.SalesRepository;
import com.jaspreetflourmill.server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<List<Transaction>> listAllTransactions(){
        return Optional.of(transactionRepository.findAll());
    }

    public Optional<List<Transaction>> findByCustomer(Customer customer){
        return  Optional.of(transactionRepository.findByCustomer(
                customer,
                Sort.by("date").descending().and(Sort.by("time").descending())
        ));
    }


    public Optional<Transaction> saveTransaction(Transaction transaction){
        return Optional.of(transactionRepository.save(transaction));
    }

    public Optional<Transaction> getTransaction(String id){
        return transactionRepository.findById(id);
    }

    public void deleteTransaction(String id){
        transactionRepository.deleteById(id);
    }

    public SalesToday getSalesForToday() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateFormat.format(dateTime);
        Double wheatSold = transactionRepository.getWheatSalesToday(date);
        Double grindingChargesPaid = transactionRepository.getGrindingChargesPaidToday(date);
        Double grindingCharges = transactionRepository.getGrindingChargesToday(date);
        return new SalesToday(wheatSold, grindingChargesPaid, grindingCharges, date);
    }
}
