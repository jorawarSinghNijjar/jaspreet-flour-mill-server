package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Customer;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.Sales;
import com.jaspreetflourmill.server.repository.EmployeeRepository;
import com.jaspreetflourmill.server.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    public Optional<List<Sales>> listAllSales(){
        return Optional.of(salesRepository.findAll());
    }

    public Optional<Sales> saveSales(Sales sales){
        return Optional.of(salesRepository.save(sales));
    }

    public Optional<Sales> getSales(String date){
        return salesRepository.findById(date);
    }

    public Optional<List<Sales>> getSalesForMonth(int month, int year) {
        return Optional.of(salesRepository.findByMonthAndYear(month,year));
    }

    public  Optional<List<Sales>> getYearlySales(int year){
        return Optional.of(salesRepository.findByYear(year));
    }
}
