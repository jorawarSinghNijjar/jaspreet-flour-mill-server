package com.jaspreetflourmill.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @ManyToOne
    @JoinColumn(name = "customer_id_fk",referencedColumnName = "customer_id")
    private Customer customer;

    @Id
    private String transactionId;
    private String date;
    private double attaPickupQty;
    private double grindingCharges;
    private double grindingChargesPaid;
    private double customerBalanceGrindingCharges;
    private double customerStoredAttaBalanceQty;
    private String orderPickedBy;
    private String cashierName;

    public Transaction(Customer customer,double attaPickupQty,double grindingCharges, double grindingChargesPaid,
                       String orderPickedBy,
                       String cashierName) {
        this.customer = customer;
        this.attaPickupQty = attaPickupQty;
        this.grindingCharges = grindingCharges;
        this.grindingChargesPaid = grindingChargesPaid;
        this.orderPickedBy = orderPickedBy;
        this.cashierName = cashierName;
        UUID uuid = UUID.randomUUID();
        this.transactionId = uuid.toString();

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        this.date = formatter.format(dateTime);

        this.customerBalanceGrindingCharges = grindingCharges - grindingChargesPaid;


    }

    public Transaction(){

    }



    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAttaPickupQty() {
        return attaPickupQty;
    }

    public void setAttaPickupQty(double attaPickupQty) {
        this.attaPickupQty = attaPickupQty;
    }

    public double getGrindingChargesPaid() {
        return grindingChargesPaid;
    }

    public void setGrindingChargesPaid(double grindingChargesPaid) {
        this.grindingChargesPaid = grindingChargesPaid;
    }

    public double getCustomerBalanceGrindingCharges() {
        return customerBalanceGrindingCharges;
    }

    public void setCustomerBalanceGrindingCharges(double customerBalanceGrindingCharges) {
        this.customerBalanceGrindingCharges = customerBalanceGrindingCharges;
    }

    public double getCustomerStoredAttaBalanceQty() {
        return customerStoredAttaBalanceQty;
    }

    public void setCustomerStoredAttaBalanceQty(double customerStoredAttaBalanceQty) {
        this.customerStoredAttaBalanceQty = customerStoredAttaBalanceQty;
    }

    public String getOrderPickedBy() {
        return orderPickedBy;
    }

    public void setOrderPickedBy(String orderPickedBy) {
        this.orderPickedBy = orderPickedBy;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
