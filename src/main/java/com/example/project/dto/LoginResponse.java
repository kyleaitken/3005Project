package com.example.project.dto;

public class LoginResponse {
    private String userType;
    private Integer id;

    public String getType() { return userType;}
    public Integer getId() {return id;}

    public void setUserType(String type) {this.userType = type;}
    public void setId(Integer id) {this.id = id;}
}
