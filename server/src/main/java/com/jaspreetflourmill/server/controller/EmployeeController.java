package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Customer;
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
    public ResponseEntity<List<Employee>> list(){
        try {
            List<Employee> employees = employeeService.listAllEmployees().orElseThrow();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable String id){
        try{
            Employee employee = employeeService.getEmployee(id).orElseThrow();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable String id){
        try{
            Employee existEmployee = employeeService.getEmployee(id).orElseThrow();
            employee.setId(id);
            Employee updatedEmployee = employeeService.saveEmployee(employee).orElseThrow();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<Employee> add(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.saveEmployee(employee).orElseThrow();
            return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        employeeService.deleteEmployee(id);
    }
}
