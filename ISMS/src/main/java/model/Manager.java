/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author haidu
 */
public class Manager {
    
    private int managerId;
    private String fullname;
    private String email;
    private Date birthDate;
    private String phoneNumber;

    public Manager() {
    }

    public Manager(int managerId, String fullname, String email, Date birthDate, String phoneNumber) {
        this.managerId = managerId;
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFullname() {
        return fullname;
    }


    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Manager{" + "managerId=" + managerId + ", fullname=" + fullname + ", email=" + email + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
}
