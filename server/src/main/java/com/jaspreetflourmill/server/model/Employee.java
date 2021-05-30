package com.jaspreetflourmill.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    private String employeeId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String contactNumber;

    @NotNull
    private String address;

    private String jobDesignation;

    @NotNull
    private String dob;

    public Employee(){

    }

    public Employee(String name, String password, String contactNumber, String address, String jobDesignation, LocalDate dob) {
        this.name = name;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
        this.jobDesignation = jobDesignation;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.dob = formatter.format(dob);

        this.employeeId = name.substring(0,3) + "00" + dob.getMonthValue();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobDesignation() {
        return jobDesignation;
    }

    public void setJobDesignation(String jobDesignation) {
        this.jobDesignation = jobDesignation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}
