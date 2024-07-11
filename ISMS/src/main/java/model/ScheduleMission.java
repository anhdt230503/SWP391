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
public class ScheduleMission {
    
    private int scheduleId;
    private int internId;
    private int misId;
    private String misName;
    private double rate;
    private Timestamp date;
    private int hour;

    public ScheduleMission() {
    }

    public ScheduleMission(int scheduleId, int internId, int misId, String misName, double rate, Timestamp date, int hour) {
        this.scheduleId = scheduleId;
        this.internId = internId;
        this.misId = misId;
        this.misName = misName;
        this.rate = rate;
        this.date = date;
        this.hour = hour;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getInternId() {
        return internId;
    }

    public int getMisId() {
        return misId;
    }

    public String getMisName() {
        return misName;
    }

    public double getRate() {
        return rate;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public void setMisId(int misId) {
        this.misId = misId;
    }

    public void setMisName(String misName) {
        this.misName = misName;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "ScheduleMission{" + "scheduleId=" + scheduleId + ", internId=" + internId + ", misId=" + misId + ", misName=" + misName + ", rate=" + rate + ", date=" + date + ", hour=" + hour + '}';
    }
    
    
    
}
