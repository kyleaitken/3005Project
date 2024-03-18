package com.example.project.Models;

public class Admin {
    private Integer adminId;
    private String email;
    private String password;

    public Admin() {}

    public Admin(Integer adminId, String email, String password) {
        this.adminId = adminId;
        this.email = email;
        this.password = password;
    }

    public Integer getAdminId() {return adminId;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}

    public void setAdminId(Integer adminId) {this.adminId = adminId;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
}
