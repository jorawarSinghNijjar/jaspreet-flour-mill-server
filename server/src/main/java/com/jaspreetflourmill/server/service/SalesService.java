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

@Service
@Transactional
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> listAllSales(){
        return salesRepository.findAll();
    }

    public void saveSales(Sales sales){
        salesRepository.save(sales);
    }

    public Sales getSales(String date){
        return salesRepository.findById(date).get();
    }

    public List<Sales> getSalesForMonth(int month, int year) {
        return salesRepository.findByMonthAndYear(month,year);
    }

    public  List<Sales> getYearlySales(int year){
        return salesRepository.findByYear(year);
    }
}
