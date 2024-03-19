package com.example.project.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class TrainerMemberProfileView {
    private Integer memberId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String memberEmail;
    private String memberPhone;
    private Integer memberHeight;
    private Integer memberWeight;
    private Integer memberBMI;
    private Integer memberRestingHR;
    private Integer memberSysBP;
    private Integer memberDiaBP;
    private Integer memberWaist;

    // Already defined getters and setters
    public Integer getMemberId() {return memberId;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public void setMemberId(Integer memberId) {this.memberId = memberId;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    // Additional getters and setters
    public String getMemberEmail() {return memberEmail;}
    public void setMemberEmail(String memberEmail) {this.memberEmail = memberEmail;}

    public String getMemberPhone() {return memberPhone;}
    public void setMemberPhone(String memberPhone) {this.memberPhone = memberPhone;}

    public Integer getMemberHeight() {return memberHeight;}
    public void setMemberHeight(Integer memberHeight) {this.memberHeight = memberHeight;}

    public Integer getMemberWeight() {return memberWeight;}
    public void setMemberWeight(Integer memberWeight) {this.memberWeight = memberWeight;}

    public Integer getMemberBMI() {return memberBMI;}
    public void setMemberBMI(Integer memberBMI) {this.memberBMI = memberBMI;}

    public Integer getMemberRestingHR() {return memberRestingHR;}
    public void setMemberRestingHR(Integer memberRestingHR) {this.memberRestingHR = memberRestingHR;}

    public Integer getMemberSysBP() {return memberSysBP;}
    public void setMemberSysBP(Integer memberSysBP) {this.memberSysBP = memberSysBP;}

    public Integer getMemberDiaBP() {return memberDiaBP;}
    public void setMemberDiaBP(Integer memberDiaBP) {this.memberDiaBP = memberDiaBP;}

    public Integer getMemberWaist() {return memberWaist;}
    public void setMemberWaist(Integer memberWaist) {this.memberWaist = memberWaist;}

    public LocalDate getMemberBirthDate() {return birthDate;}
    public void setMemberBirthDate(LocalDate date) {this.birthDate = date;}
}
