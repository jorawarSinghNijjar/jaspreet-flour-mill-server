package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.repository.EmployeeRepository;
import com.jaspreetflourmill.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<List<Employee>> listAllEmployees(){
        return Optional.of(employeeRepository.findAll());
    }

    public Optional<Employee> saveEmployee(Employee employee){
        return Optional.of(employeeRepository.save(employee));
    }

    public Optional<Employee> getEmployee(String userId){
        Optional<User> user = userRepository.getUser(userId);
        return employeeRepository.findByUser(user.get());
    }

    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

}
