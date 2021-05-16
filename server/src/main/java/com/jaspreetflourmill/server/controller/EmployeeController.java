package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> list(){
        return employeeService.listAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable String id){
        try{
            Employee employee = employeeService.getEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable String id){
        try{
            Employee existEmployee = employeeService.getEmployee(id);
            employee.setEmployeeId(id);
            employeeService.saveEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> add(@RequestBody Employee employee){
        try{
            System.out.println(employee.toString());
            employeeService.saveEmployee(employee);
            return new ResponseEntity<String>("Employee Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        employeeService.deleteEmployee(id);
    }
}
