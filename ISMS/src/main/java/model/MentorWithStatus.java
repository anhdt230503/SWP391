/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Mentor;

/**
 *
 * @author ACER
 */
public class MentorWithStatus {

    private Mentor mentor;
    private Account account;
    private int status;

    public MentorWithStatus() {
    }

    public MentorWithStatus(Mentor mentor, Account account, int status) {
        this.mentor = mentor;
        this.account = account;
        this.status = status;
    }

    public MentorWithStatus(Mentor mentor, int status) {
        this.mentor = mentor;
        this.status = status;
    }

    public MentorWithStatus(Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
