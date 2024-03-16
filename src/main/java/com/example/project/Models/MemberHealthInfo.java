package com.example.project.Models;

public class MemberHealthInfo {
    private Long memberId;
    private Integer height;
    private Integer weight;
    private Integer bmi;
    private Integer restingHeartRate;
    private Integer systolicBp;
    private Integer diastolicBp;
    private Integer waistGirth;

    // Constructors
    public MemberHealthInfo() {
    }

    public MemberHealthInfo(Long memberId, Integer height, Integer weight, Integer bmi, Integer restingHeartRate, Integer systolicBp, Integer diastolicBp, Integer waistGirth) {
        this.memberId = memberId;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.restingHeartRate = restingHeartRate;
        this.systolicBp = systolicBp;
        this.diastolicBp = diastolicBp;
        this.waistGirth = waistGirth;
    }

    // Getters
    public Long getMemberId() {
        return memberId;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getBmi() {
        return bmi;
    }

    public Integer getRestingHeartRate() {
        return restingHeartRate;
    }

    public Integer getSystolicBp() {
        return systolicBp;
    }

    public Integer getDiastolicBp() {
        return diastolicBp;
    }

    public Integer getWaistGirth() {
        return waistGirth;
    }

    // Setters
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setBmi(Integer bmi) {
        this.bmi = bmi;
    }

    public void setRestingHeartRate(Integer restingHeartRate) {
        this.restingHeartRate = restingHeartRate;
    }

    public void setSystolicBp(Integer systolicBp) {
        this.systolicBp = systolicBp;
    }

    public void setDiastolicBp(Integer diastolicBp) {
        this.diastolicBp = diastolicBp;
    }

    public void setWaistGirth(Integer waistGirth) {
        this.waistGirth = waistGirth;
    }
}