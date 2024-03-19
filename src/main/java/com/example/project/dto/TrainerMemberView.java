package com.example.project.dto;

public class TrainerMemberView {
    private Integer memberId;
    private String memberFirstName;
    private String memberLastName;

    // Getters
    public Integer getMemberId() {
        return memberId;
    }

    public String getMemberFirstName() {
        return memberFirstName;
    }

    public String getMemberLastName() {
        return memberLastName;
    }

    // Setters
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }
}
