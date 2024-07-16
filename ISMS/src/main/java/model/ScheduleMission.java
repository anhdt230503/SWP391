/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
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
    private Timestamp createdDate;
    private Date startDate;
    private int hour;
    private String note;

    public ScheduleMission() {
    }

    public ScheduleMission(int scheduleId, int internId, int misId, String misName, double rate, Timestamp createdDate, Date startDate, int hour, String note) {
        this.scheduleId = scheduleId;
        this.internId = internId;
        this.misId = misId;
        this.misName = misName;
        this.rate = rate;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.hour = hour;
        this.note = note;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getHour() {
        return hour;
    }

    public String getNote() {
        return note;
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

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ScheduleMission{" + "scheduleId=" + scheduleId + ", internId=" + internId + ", misId=" + misId + ", misName=" + misName + ", rate=" + rate + ", createdDate=" + createdDate + ", startDate=" + startDate + ", hour=" + hour + ", note=" + note + '}';
    }

    
}
