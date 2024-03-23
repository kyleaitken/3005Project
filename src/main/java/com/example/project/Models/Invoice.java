package com.example.project.Models;

import java.time.LocalDate;

public class Invoice {
    private Integer paymentId;
    private Integer memberId;
    private LocalDate date;
    private String type;
    private Integer cost;
    private String status;

        // Default constructor
    public Invoice() {
    }

    // Parameterized constructor
    public Invoice(Integer paymentId, Integer memberId, LocalDate date, String type, Integer cost, String status) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.status = status;
    }

    // Getters
    public Integer getPaymentId() {
        return paymentId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public Integer getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(LocalDate d) {
        this.date = d;
    }

}
