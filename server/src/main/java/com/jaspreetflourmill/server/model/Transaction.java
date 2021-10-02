package com.jaspreetflourmill.server.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(columnDefinition = "DATE")
    private String date;

    @NotNull
    @Column(columnDefinition = "TIME")
    private String time;

    @NotNull
    private double flourPickupQty;

    @NotNull
    private double grindingRate;

    @NotNull
    private double grindingCharges;
    @NotNull
    private double grindingChargesPaid;
    @NotNull
    private double customerBalanceGrindingCharges;
    @NotNull
    private double customerStoredFlourBalanceQty;
    @NotNull
    private String orderPickedBy;
    @NotNull
    private String cashierName;

    public Transaction(Customer customer, double flourPickupQty, double grindingRate, double grindingCharges,
                       double grindingChargesPaid,
                       String orderPickedBy,
                       String cashierName) {
        this.customer = customer;
        this.flourPickupQty = flourPickupQty;
        this.grindingRate = grindingRate;
        this.grindingCharges = grindingCharges;
        this.grindingChargesPaid = grindingChargesPaid;
        this.orderPickedBy = orderPickedBy;
        this.cashierName = cashierName;
        UUID uuid = UUID.randomUUID();
        this.transactionId = uuid.toString();

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.date = dateFormat.format(dateTime);
        this.time = timeFormat.format(dateTime);

        this.customerBalanceGrindingCharges = grindingCharges - grindingChargesPaid;

        System.out.println("Grinding Charges --> " + grindingCharges);

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

    public double getFlourPickupQty() {
        return flourPickupQty;
    }

    public void setFlourPickupQty(double flourPickupQty) {
        this.flourPickupQty = flourPickupQty;
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

    public double getCustomerStoredFlourBalanceQty() {
        return customerStoredFlourBalanceQty;
    }

    public void setCustomerStoredFlourBalanceQty(double customerStoredFlourBalanceQty) {
        this.customerStoredFlourBalanceQty = customerStoredFlourBalanceQty;
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

    public double getGrindingRate() {
        return grindingRate;
    }

    public void setGrindingRate(double grindingRate) {
        this.grindingRate = grindingRate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getGrindingCharges() {
        return grindingCharges;
    }

    public void setGrindingCharges(double grindingCharges) {
        this.grindingCharges = grindingCharges;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customer=" + customer +
                ", transactionId='" + transactionId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", flourPickupQty=" + flourPickupQty +
                ", grindingRate=" + grindingRate +
                ", grindingCharges=" + grindingCharges +
                ", grindingChargesPaid=" + grindingChargesPaid +
                ", customerBalanceGrindingCharges=" + customerBalanceGrindingCharges +
                ", customerStoredFlourBalanceQty=" + customerStoredFlourBalanceQty +
                ", orderPickedBy='" + orderPickedBy + '\'' +
                ", cashierName='" + cashierName + '\'' +
                '}';
    }
}
