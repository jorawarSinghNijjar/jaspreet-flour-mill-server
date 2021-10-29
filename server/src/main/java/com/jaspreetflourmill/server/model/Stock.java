package com.jaspreetflourmill.server.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private Integer id;

    private Double wheatBalance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",nullable = false, updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Stock() {
        this.id = 1;
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
