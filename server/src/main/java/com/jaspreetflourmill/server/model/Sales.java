package com.jaspreetflourmill.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales")
public class Sales {

    @Id
    @Column(columnDefinition = "DATE",nullable = false)
    private String date;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int day;

    private int month;

    private int year;

    private Double totalStoredWheatBalance;

    private Double totalWheatDeposited;

    private Double totalWheatSold;

    private Double totalGrindingCharges;

    private Double totalGrindingChargesPaid;

    public Sales(){}

    public Sales(String date, Double totalWheatSold, Double totalGrindingCharges,
                 Double totalGrindingChargesPaid) {
        this.date = date;
        String[] dateStrArr = date.split("-");
        int[] dateIntArr = new int[3];
        for(int i=0; i<dateStrArr.length; i++){
            dateIntArr[i] = Integer.parseInt(dateStrArr[i]);
        }
        this.day = dateIntArr[2];
        this.month = dateIntArr[1];
        this.year = dateIntArr[0];
        this.totalWheatSold = totalWheatSold;
        this.totalGrindingCharges = totalGrindingCharges;
        this.totalGrindingChargesPaid = totalGrindingChargesPaid;
//        this.totalStoredWheatBalance = 0.00;
        this.totalWheatDeposited = 0.00;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalWheatSold() {
        return totalWheatSold;
    }

    public void setTotalWheatSold(Double totalWheatSold) {
        this.totalWheatSold = totalWheatSold;
    }

    public Double getTotalGrindingCharges() {
        return totalGrindingCharges;
    }

    public void setTotalGrindingCharges(Double totalGrindingCharges) {
        this.totalGrindingCharges = totalGrindingCharges;
    }

    public Double getTotalGrindingChargesPaid() {
        return totalGrindingChargesPaid;
    }

    public void setTotalGrindingChargesPaid(Double totalGrindingChargesPaid) {
        this.totalGrindingChargesPaid = totalGrindingChargesPaid;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getTotalStoredWheatBalance() {
        return totalStoredWheatBalance;
    }

    public void setTotalStoredWheatBalance(Double totalStoredWheatBalance) {
        this.totalStoredWheatBalance = totalStoredWheatBalance;
    }

    public Double getTotalWheatDeposited() {
        return totalWheatDeposited;
    }

    public void setTotalWheatDeposited(Double totalWheatDeposited) {
        this.totalWheatDeposited = totalWheatDeposited;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "date='" + date + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", totalStoredWheatBalance=" + totalStoredWheatBalance +
                ", totalWheatDeposited=" + totalWheatDeposited +
                ", totalWheatSold=" + totalWheatSold +
                ", totalGrindingCharges=" + totalGrindingCharges +
                ", totalGrindingChargesPaid=" + totalGrindingChargesPaid +
                '}';
    }
}
