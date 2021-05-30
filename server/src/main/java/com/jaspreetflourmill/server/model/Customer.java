package com.jaspreetflourmill.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @OneToOne(mappedBy = "customer")
    private CustomerAccount customerAccount;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;

    private String rationCardNo;

    @NotNull
    private String dob;

    @NotNull
    private String adhaarNo;

    @NotNull
    private String idProof;

    public Customer(String name, String address, String phoneNumber, String rationCardNo, LocalDate dob, String adhaarNo,
                    String idProof) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rationCardNo = rationCardNo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.dob = formatter.format(dob);
        this.adhaarNo = adhaarNo;
        this.idProof = idProof;
    }

    public Customer(){

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAdhaarNo() {
        return adhaarNo;
    }

    public void setAdhaarNo(String adhaarNo) {
        this.adhaarNo = adhaarNo;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
