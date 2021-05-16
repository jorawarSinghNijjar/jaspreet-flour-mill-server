package com.jaspreetflourmill.server.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="customerAccounts")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerAccountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id_fk",referencedColumnName = "customer_id")
    @NotNull
    private Customer customer;

    @NotNull
    private double wheatDepositQty;

    @NotNull
    private double wheatProcessingDeductionQty;

    @NotNull
    private double initialWheatQty;

    private double currentWheatBalance;

    private double grindingChargesBalance;

    @NotNull
    private String startDate;

    public CustomerAccount(Customer customer, double wheatDepositQty, double wheatProcessingDeductionQty) {
        this.customer = customer;
        this.wheatDepositQty = wheatDepositQty;
        this.wheatProcessingDeductionQty = wheatProcessingDeductionQty;
        this.initialWheatQty = wheatDepositQty - wheatProcessingDeductionQty;
        this.currentWheatBalance = initialWheatQty;
        this.grindingChargesBalance = 0;

    }

    public void setWheatProcessingDeductionQty(double wheatProcessingDeductionQty) {
        this.wheatProcessingDeductionQty = wheatProcessingDeductionQty;
    }

    public void setGrindingChargesBalance(double grindingChargesBalance) {
        this.grindingChargesBalance = grindingChargesBalance;
    }

    public CustomerAccount(){

    }

    public Customer getCustomerId() {
        return customer;
    }

    public void setCustomerId(Customer customer) {
        this.customer = customer;
    }

    public double getWheatDepositQty() {
        return wheatDepositQty;
    }

    public void setWheatDepositQty(double wheatDepositQty) {
        this.wheatDepositQty = wheatDepositQty;
    }

    public double getWheatProcessingDeductionQty() {
        return wheatProcessingDeductionQty;
    }

    public double getInitialWheatQty() {
        return initialWheatQty;
    }

    public void setInitialWheatQty(double initialWheatQty) {
        this.initialWheatQty = initialWheatQty;
    }

    public double getCurrentWheatBalance() {
        return currentWheatBalance;
    }

    public void setCurrentWheatBalance(double currentWheatBalance) {
        this.currentWheatBalance = currentWheatBalance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Integer customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public double getGrindingChargesBalance() {
        return grindingChargesBalance;
    }
}
