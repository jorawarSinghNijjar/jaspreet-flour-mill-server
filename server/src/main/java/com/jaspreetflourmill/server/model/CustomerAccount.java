package com.jaspreetflourmill.server.model;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="customerAccounts")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_account_id")
    private Integer customerAccountId;

    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "customer_id")
    private Customer customer;

    @Column(precision = 10, scale = 2)
    private double wheatDepositQty;

    @Column(precision = 10, scale = 2)
    private double wheatProcessingDeductionQty;

    @Column(precision = 10, scale = 2)
    private double initialWheatQty;

    @Column(precision = 10, scale = 2)
    private double currentWheatBalance;

    @Column(precision = 10, scale = 2)
    private double grindingChargesBalance;

    @Column(precision = 10, scale = 2)
    private double grindingRate;

    private int rowsPrinted;

    @NotNull
    private String startDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",nullable = false, updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public CustomerAccount(Customer customer, double wheatDepositQty, double wheatProcessingDeductionQty) {
        this.customer = customer;
        this.wheatDepositQty = wheatDepositQty;
        this.wheatProcessingDeductionQty = wheatProcessingDeductionQty;
        this.initialWheatQty = wheatDepositQty - wheatProcessingDeductionQty;
        this.currentWheatBalance = initialWheatQty;
        this.grindingChargesBalance = 0;
        this.rowsPrinted = 0;
    }

    public int getRowsPrinted() {
        return rowsPrinted;
    }

    public void setRowsPrinted(int rowsPrinted) {
        this.rowsPrinted = rowsPrinted;
    }

    public void incrementRow(){
        this.rowsPrinted++;
    }

    public void printNextPage(){
        this.rowsPrinted = 0;
    }


    public double getGrindingRate() {
        return grindingRate;
    }

    public void setGrindingRate(double grindingRate) {
        this.grindingRate = grindingRate;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "customerAccountId=" + customerAccountId +
                ", customer=" + customer +
                ", wheatDepositQty=" + wheatDepositQty +
                ", wheatProcessingDeductionQty=" + wheatProcessingDeductionQty +
                ", initialWheatQty=" + initialWheatQty +
                ", currentWheatBalance=" + currentWheatBalance +
                ", grindingChargesBalance=" + grindingChargesBalance +
                ", grindingRate=" + grindingRate +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
