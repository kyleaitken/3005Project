package com.example.project.Models;

public class Invoice {
    private Integer paymentId;
    private Integer memberId;
    private String type;
    private Integer cost;
    private String status;

        // Default constructor
    public Invoice() {
    }

    // Parameterized constructor
    public Invoice(Integer paymentId, Integer memberId, String type, Integer cost, String status) {
        this.paymentId = paymentId;
        this.memberId = memberId;
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

}
