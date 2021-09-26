package com.jaspreetflourmill.server.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customerAccounts")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_account_id")
    private Integer customerAccountId;

    @OneToOne( cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "customerId",referencedColumnName = "customer_id")
    private Customer customer;

    @NotNull
    private double wheatDepositQty;

    @NotNull
    private double wheatProcessingDeductionQty;

    @NotNull
    private double initialWheatQty;

    private double currentWheatBalance;

    private double grindingChargesBalance;

    private double grindingRate;

    private int rowsPrinted;

    @NotNull
    private String startDate;

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
