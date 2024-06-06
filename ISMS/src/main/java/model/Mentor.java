/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author haidu
 */
public class Mentor {
 
    private int mentorId;
    private String fullname;
    private String email;
    private Date birthDate;
    private String phoneNumber;

    public Mentor() {
    }

    public Mentor(int mentorId, String fullname, String email, Date birthDate, String phoneNumber) {
        this.mentorId = mentorId;
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public Mentor(int mentorId, String fullname, String email) {
        this.mentorId = mentorId;
        this.fullname = fullname;
        this.email = email;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
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
        return "Mentor{" + "mentorId=" + mentorId + ", fullname=" + fullname + ", email=" + email + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
}
