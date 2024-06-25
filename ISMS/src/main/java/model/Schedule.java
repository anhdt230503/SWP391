/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;


/**
 *
 * @author haidu
 */
public class Schedule {
    
    private int misid;
    private String misName;
    private int internid;
    private float rate;
    private Timestamp startdate;
    private Timestamp enddate;
    private int hours;
    private String status;

    public Schedule() {
    }

    public Schedule(int misid, String misName, int internid, float rate, Timestamp startdate, Timestamp enddate, int hours, String status) {
        this.misid = misid;
        this.misName = misName;
        this.internid = internid;
        this.rate = rate;
        this.startdate = startdate;
        this.enddate = enddate;
        this.hours = hours;
        this.status = status;
    }

    public int getMisid() {
        return misid;
    }

    public void setMisid(int misid) {
        this.misid = misid;
    }

    public String getMisName() {
        return misName;
    }

    public void setMisName(String misName) {
        this.misName = misName;
    }

    public int getInternid() {
        return internid;
    }

    public void setInternid(int internid) {
        this.internid = internid;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Schedule{" + "misid=" + misid + ", misName=" + misName + ", internid=" + internid + ", rate=" + rate + ", startdate=" + startdate + ", enddate=" + enddate + ", hours=" + hours + ", status=" + status + '}';
    }

    
    
}
