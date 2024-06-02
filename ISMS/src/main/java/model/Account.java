/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haidu
 */
public class Account {
    
    private int accountId;
    private String email;
    private int status;
    private int managerId;
    private int mentorId;
    private int internId;
    private int roleId;

    public Account() {
    }

    public Account(int accountId, String email, int status, int managerId, int mentorId, int internId, int roleId) {
        this.accountId = accountId;
        this.email = email;
        this.status = status;
        this.managerId = managerId;
        this.mentorId = mentorId;
        this.internId = internId;
        this.roleId = roleId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", email=" + email + ", status=" + status + ", managerId=" + managerId + ", mentorId=" + mentorId + ", internId=" + internId + ", roleId=" + roleId + '}';
    }

    

}
