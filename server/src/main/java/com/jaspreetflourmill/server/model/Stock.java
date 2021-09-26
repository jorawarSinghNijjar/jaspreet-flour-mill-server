package com.jaspreetflourmill.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="stock")
public class Stock {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Double wheatBalance;

    public Stock() {
        this.wheatBalance = 0.00;
    }

    public void addWheat(double wheatQty){
        this.wheatBalance += wheatQty;
    }

    public void deductWheat(double wheatQty){
        this.wheatBalance -= wheatQty;
    }

    public Double getWheatBalance() {
        return wheatBalance;
    }

    public void setWheatBalance(Double wheatBalance) {
        this.wheatBalance = wheatBalance;
    }
}
